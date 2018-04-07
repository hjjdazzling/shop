package hjj.service;

import hjj.entity.Admin;

public interface AdminService {
	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	boolean select(Admin admin);
	/**
	 * 注册
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	boolean insert(Admin admin) throws Exception;
}
