package hjj.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import hjj.entity.Admin;
import hjj.service.AdminService;

@Controller(value = "adminAction")
@Scope(value = "prototype")
public class AdminAction {
	@Resource(name = "adminService")
	private AdminService adminService;
	private Admin admin;
	
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();

		if (adminService.select(admin)) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			return "success";
		} else {
			request.setAttribute("error", "登录名或者密码错误");
			return "login";
		}
	}
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("admin.username");
		String password = request.getParameter("admin.password");
		String password2 = request.getParameter("password2");
		String money = request.getParameter("admin.money");
		String phone = request.getParameter("admin.phone");

		if (username.trim() == null || username.trim() == "") {
			request.setAttribute("error", "用户名不能为空");
			return "register";
		}
		if (password.trim() == null || password.trim() == "") {
			request.setAttribute("error", "密码不能为空");
			return "register";
		}
		if (password2.trim() == null || password2.trim() == "") {
			request.setAttribute("error", "确认密码不能为空");
			return "register";
		}
		if (money.trim() == null || money.trim() == "") {
			admin.setMoney(0);
		}
		if (phone.trim() == null || phone.trim() == "") {
			request.setAttribute("error", "手机号不能为空");
			return "register";
		}
		if (!password.equals(password2)) {
			request.setAttribute("error", "两次输入的密码不一致");
			return "register";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mmss");
		String date = df.format(new Date());
		admin.setDate(admin.getDate());
		try {
			if (adminService.insert(admin)) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);

				return "success";
			} else {
				request.setAttribute("error", "用户名已存在");
				return "register";
			}
		} catch (Exception e) {
			request.setAttribute("error", "用户名已存在");
			return "register";
		}
	}
	
	public AdminService getService() {
		return adminService;
	}
	public void setService(AdminService adminService) {
		this.adminService = adminService;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
}
