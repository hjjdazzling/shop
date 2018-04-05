package hjj.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hjj.entity.Admin;
import hjj.entity.Deliver;
import hjj.entity.DeliverRecord;
import hjj.entity.Good;
import hjj.entity.PurchaseRecord;
import hjj.entity.Shop;
import hjj.entity.ShopCarRecord;
import hjj.entity.Situation;
import hjj.entity.User;

@Repository(value = "userDao")
public class UserDao {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	//查看商品
	public List<Good> selectGoods() {
		List<Good> list = (List<Good>) hibernateTemplate.find("from Good");
		return list;
	}
	//添加购物车
	public boolean insertShopCar(Good buyGood, User user, int number) {
		ShopCarRecord record = new ShopCarRecord();
		record.setUser(user);
		record.setGood(buyGood);
		record.setNumber(number);
		
		hibernateTemplate.save(record);
		return false;
	}
	//确认收货
	public boolean confirmGood(PurchaseRecord record) {
		record.setSituation("确认收货");
		hibernateTemplate.update(record);
		
		//从第三方把钱打给商家
		Good good = record.getGood();
		double money = good.getPrice() * record.getNumber();
		Shop shop = good.getShop();
		List<Admin> listAdmin = (List<Admin>) hibernateTemplate.find("from Admin");
		Admin admin = listAdmin.get(0);
		//转钱
		admin.setMoney(admin.getMoney() - money);
		shop.setMoney(shop.getMoney() + money);
		
		hibernateTemplate.update(admin);
		hibernateTemplate.update(shop);
		
		//删除送货记录
		String sql = "from DeliverRecord where p_id = ?";
		List<DeliverRecord> list = (List<DeliverRecord>) hibernateTemplate.find(sql, record.getId()); 
		DeliverRecord deliverRecord = list.get(0);
		
		hibernateTemplate.delete(deliverRecord);
		return true;
	}
	
	//退货
	public boolean backGood(PurchaseRecord returnGood, User user) {
		returnGood.setSituation(Situation.RETURN_YES);
		hibernateTemplate.update(returnGood);

		// 随机选择一个退货提供商
		List<Deliver> listDeliver = (List<Deliver>) hibernateTemplate.find("from Deliver");
		int number = (int) (Math.random() * listDeliver.size() + 1);
		if (number >= listDeliver.size()) {
			number = 1;
		}
		
		Deliver deliver = listDeliver.get(number);
		
		DeliverRecord deliverRecord = new DeliverRecord();
		deliverRecord.setDeliver(deliver);
		String address = returnGood.getGood().getShop().getAddress();
		deliverRecord.setAddress("发货中");
		deliverRecord.setType("退货");
		deliverRecord.setPurchaseRecord(returnGood);
		deliverRecord.setTargetAddress(address);
		hibernateTemplate.save(deliverRecord);
		
		//10元钱邮费
		user.setMoney(user.getMoney() - 10);
		hibernateTemplate.update(user);
		
		return true;
	}	
	//查看确认收货记录
	public List<PurchaseRecord> selectAcceptRecord(User user) {
		String sql = "from PurchaseRecord where u_id = ? and situation = '确认收货'";
		List<PurchaseRecord> purchaseRecord = (List<PurchaseRecord>) hibernateTemplate.find(sql, user.getId());
		return purchaseRecord;
	}
	
	//查看送货记录
	public List<DeliverRecord> selectDeliverRecord(User user, List<PurchaseRecord> purchaseRecord) {
		String sql = "from DeliverRecord where p_id = ?";
		List<DeliverRecord> deliverRecord = new ArrayList<>();
		for(PurchaseRecord record : purchaseRecord) {
			List<DeliverRecord>  tempRecord = (List<DeliverRecord>) hibernateTemplate.find(sql, record.getId());
			if(!tempRecord.isEmpty()) {
				deliverRecord.add(tempRecord.get(0));
			}
		}
		
		return deliverRecord;
	}
	//查看购物记录
	public List<PurchaseRecord> selectPurchaseRecord(User user) {
		String sql = "from PurchaseRecord where u_id = ?";
		List<PurchaseRecord> purchaseRecord = (List<PurchaseRecord>) hibernateTemplate.find(sql, user.getId());
		return purchaseRecord;
	}
	//购物车购买
	public boolean buyShopCar(Map<Good, Integer> buyGood,User user,List<ShopCarRecord> shopCarRecord) {
		PurchaseRecord pr = null;
		Set<Good> good = buyGood.keySet(); 
		
		//随机选择一个送货提供商
		List<Deliver> listDeliver =  (List<Deliver>) hibernateTemplate.find("from Deliver");
		int number = (int)(Math.random() * listDeliver.size() + 1); 
		if(number >= listDeliver.size()) {
			number = 1;
		} 
		
		Deliver deliver = listDeliver.get(number);
		System.out.println(deliver);
		for(Good g : good) {
			pr = new PurchaseRecord();
			pr.setGood(g);
			pr.setNumber(buyGood.get(g));
			pr.setUser(user);
			pr.setSituation(Situation.DELIVER);
			
			for(ShopCarRecord record : shopCarRecord) {
				if(g.equals(record.getGood())) {
					System.out.println("hello");
					pr.setNumber(buyGood.get(g));
					DeliverRecord deliverRecord = new DeliverRecord();
					deliverRecord.setDeliver(deliver);
					deliverRecord.setPurchaseRecord(pr);
					deliverRecord.setAddress("发货中");
					deliverRecord.setType("送货");
					deliverRecord.setTargetAddress(user.getAddress());
					
					//增加购买记录
					hibernateTemplate.save(pr);
					//删除购物记录
			 	    hibernateTemplate.delete(record);
			 	    //开始送货了
			 	    hibernateTemplate.save(deliverRecord);
			 	    //减少钱
			 	    double money = g.getPrice() * record.getNumber();
			 	    double allMoney = money + 10 + 10; //10邮费 10中介费
			 	    user.setMoney(user.getMoney() - allMoney);
			 	    hibernateTemplate.update(user);
			 	    //给第三方平台
			 	    String sql = "from Admin";
			 	    List<Admin> listAdmin = (List<Admin>)hibernateTemplate.find(sql);
			 	    Admin admin = listAdmin.get(0);
			 	    admin.setMoney(admin.getMoney() + money);
			 	    hibernateTemplate.update(admin);
			 	    
			 	    //转邮费
			 	    deliver.setMoney(deliver.getMoney() + 10);
			 	   hibernateTemplate.update(deliver);
			 	}
			}
		}
		
		return true;
	}
	// 查看购物车
	public List<ShopCarRecord> selectShopCar(User user) {
		String sql = "from ShopCarRecord where u_id = ?";
		List<ShopCarRecord> listRecord =  (List<ShopCarRecord>) hibernateTemplate.find(sql, user.getId());
		return listRecord;
	}
	
	// 修改个人资料
	public boolean updateData(User user) throws Exception {
		try {
			hibernateTemplate.update(user);
		} catch (Exception e) {
			throw new Exception("更新失败");
		}
		return true;
	}

	// 登录
	public boolean select(User user) {
		String sql = "from User where username = ? and password = ?";
		List<User> list = (List<User>) hibernateTemplate.find(sql, user.getUsername(), user.getPassword());

		if (list.size() > 0) {
			User user2 = list.get(0);
			valueUser(user, user2);
			return true;
		} else {
			return false;
		}

	}

	// 注册
	public int insert(User user) throws Exception {
		Integer number = 0;
		try {
			number = (Integer) hibernateTemplate.save(user);
			System.out.println(number);
		} catch (Exception e) {
			throw new Exception("用户名已存在");
		}
		return number;
	}
	
	public void valueUser(User user, User user2) {
		user.setId(user2.getId());
		user.setUsername(user2.getUsername());
		user.setPhone(user2.getPhone());
		user.setAddress(user2.getAddress());
		user.setMoney(user2.getMoney());
		user.setDate(user2.getDate());
	}

}
