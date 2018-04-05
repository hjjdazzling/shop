package hjj.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hjj.entity.Admin;

@Repository(value = "adminDao")
public class AdminDao {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate; 
	
	//登录
		public boolean login(Admin admin) {
			String sql = "from Admin where username = ? and password = ?";
			List<Admin> list = (List<Admin>)hibernateTemplate.find(sql, admin.getUsername(), admin.getPassword());
			
			if(list.size() > 0) {
				admin = list.get(0);
				return true;
			} else {
				return false;
			}
		}
		//注册
		public boolean insert(Admin admin) throws Exception {
			int number = 0;
			try {
				number = (Integer)hibernateTemplate.save(admin);
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
