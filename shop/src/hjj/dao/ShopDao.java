package hjj.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hjj.entity.Good;
import hjj.entity.Shop;

@Repository(value = "shopDao")
public class ShopDao {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate; 
	
	//更新货品信息
	public void updateGood(Good good) {
		hibernateTemplate.update(good);
	}
	//上架自己的商品
	public void insertGood(Good good) {
		hibernateTemplate.save(good);
	}
	//查看自己上架的商品
	public List<Good> selectGood(Shop shop) {
		String sql = "from Good where s_id = ?";
		return (List<Good>) hibernateTemplate.find(sql, shop.getId());
	}
	//更新个人信息
	public void updateShop(Shop shop) {
		hibernateTemplate.update(shop);
	}
	//登录
	public boolean login(Shop shop) {
		String sql = "from Shop where username = ? and password = ?";
		
		List<Shop> list = (List<Shop>) hibernateTemplate.find(sql, shop.getUsername(), shop.getPassword());
		if(list.size() > 0) {
			shop.setId(list.get(0).getId());
			shop.setMoney(list.get(0).getMoney());
			shop.setPhone(list.get(0).getPhone());
			shop.setDate(list.get(0).getDate());
			shop.setAddress(list.get(0).getAddress());
			return true;
		} else {
			return false;
		}
		
	}
	//注册
	public boolean register(Shop shop) throws Exception {
		try {
			hibernateTemplate.save(shop);
		} catch(Exception e) {
			throw new Exception("用户名已存在");
		}
		return true;
	}
	
	
}
