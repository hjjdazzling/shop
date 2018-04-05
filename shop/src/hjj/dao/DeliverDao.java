package hjj.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hjj.entity.Deliver;
import hjj.entity.DeliverRecord;

@Repository(value = "deliverDao")
public class DeliverDao {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate; 
	
	//更新送货记录
	public void updateDeliverRecord(DeliverRecord record) {
		hibernateTemplate.update(record);
	}
	//查看订单
	public List<DeliverRecord> selectDeliverRecord(Deliver deliver) {
		String sql = "from DeliverRecord where d_id = ?";
		
		List<DeliverRecord> list = (List<DeliverRecord>) hibernateTemplate.find(sql, deliver.getId());
		return list;
	}
	
	//更新个人信息
	public void update(Deliver deliver) {
		hibernateTemplate.update(deliver);
		
	}
	//登录
	public boolean login(Deliver deliver) {
		String sql = "from Deliver where username = ? and password = ?";
		List<Deliver> list = (List<Deliver>)hibernateTemplate.find(sql, deliver.getUsername(), deliver.getPassword());
		
		if(list.size() > 0) {
			Deliver deliver2 = list.get(0);
			deliver.setId(deliver2.getId());
			deliver.setUsername(deliver2.getUsername());
			deliver.setPassword(deliver2.getPassword());
			deliver.setPhone(deliver2.getPhone());
			deliver.setMoney(deliver2.getMoney());
			return true;
		} else {
			return false;
		}
	}
	//注册
	public boolean insert(Deliver deliver) throws Exception {
		int number = 0;
		try {
			number = (Integer)hibernateTemplate.save(deliver);
			System.out.println(number);
		} catch(Exception e) {
			throw new Exception("用户名已存在");
		}
		if(number > 0) {
			 return true;
		} else {
			return false;
		}
	}
}
