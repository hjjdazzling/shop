package hjj.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjj.dao.AdminDao;
import hjj.entity.Admin;
import hjj.entity.Deliver;

@Transactional
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
	@Resource(name = "adminDao")
	private AdminDao adminDao;
	
	//登录
	public synchronized boolean select(Admin admin) {
		return adminDao.login(admin);
	}
	
	//注册
	public synchronized boolean insert(Admin admin) throws Exception {
		return adminDao.insert(admin);
	}

}
