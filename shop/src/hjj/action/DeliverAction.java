package hjj.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import hjj.entity.Deliver;
import hjj.entity.DeliverRecord;
import hjj.service.DeliverService;

@Controller(value = "deliverAction")
@Scope(value = "prototype")
public class DeliverAction {
	@Resource(name = "deliverService")
	private DeliverService deliverService;
	private Deliver deliver;

	//查看个人信息并修改
	public String select() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Deliver deliver = (Deliver) session.getAttribute("deliver");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		
		if(password != null && phone != null && !password.trim().equals("") && !phone.trim().equals("")) {
			if(!(password.equals(deliver.getPassword()) && phone.equals(deliver.getPhone()))) {
				deliver.setPassword(password);
				deliver.setPhone(phone);
				session.setAttribute("deliver", deliver);
				deliverService.update(deliver);
				System.out.println("更新");
			} 
			request.setAttribute("message", "修改成功");
			return "success";
		} else {
			request.setAttribute("error", "密码和手机号不能为空");
			return "deliver";
		}

	}
	
	//查看订单
	public String selectDeliverRecord() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Deliver deliver = (Deliver)session.getAttribute("deliver");
		List<DeliverRecord> deliverRecord = deliverService.selectDeliverRecord(deliver);
		
		session.setAttribute("deliverRecord", deliverRecord);
		return "deliverRecord";
	}
	//更新记录
	public String updateDeliverRecord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		String address = request.getParameter("address");
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch(NumberFormatException e) {
			request.setAttribute("error", "请输入数字id");
			return "deliverRecord";
		}
		//检查type
		if(type.equals("确认收货") || type.equals("等待确认收货") || type.equals("送货")
				|| type.equals("退货") || type.equals("等到确认退货") || type.equals("确认退货")) {
			
		} else {
			request.setAttribute("error", "type不正确");
			return "deliverRecord";
		}
		
		HttpSession session = request.getSession();
		List<DeliverRecord> deliverRecord = (List<DeliverRecord>) session.getAttribute("deliverRecord");
 		DeliverRecord record = null;
		
 		for(DeliverRecord r : deliverRecord) {
 			if(r.getId() == id) {
 				record = r;
 			}
 		}
 		
 		if(record == null) {
 			request.setAttribute("error", "id不存在");
			return "deliverRecord";
 		} else {
 			record.setType(type);
 			record.setAddress(address);
 			deliverService.updateDeliverRecord(record);
 			request.setAttribute("message", "更新成功");
 			return "success";
 		}
		
	}
	//登录
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();

		if (deliverService.select(deliver)) {
			HttpSession session = request.getSession();
			session.setAttribute("deliver", deliver);
			return "success";
		} else {
			request.setAttribute("error", "登录名或者密码错误");
			return "login";
		}
	}
	//注册
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("deliver.username");
		String password = request.getParameter("deliver.password");
		String password2 = request.getParameter("password2");
		String money = request.getParameter("deliver.money");
		String phone = request.getParameter("deliver.phone");

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
			deliver.setMoney(0);
		}
		if (phone.trim() == null || phone.trim() == "") {
			request.setAttribute("error", "手机号不能为空");
			return "register";
		}
		if (!password.equals(password2)) {
			request.setAttribute("error", "两次输入的密码不一致");
			return "register";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = df.format(new Date());
		deliver.setDate(date);
		try {
			if (deliverService.insert(deliver)) {
				HttpSession session = request.getSession();
				session.setAttribute("deliver", deliver);

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

	public DeliverService getDeliverService() {
		return deliverService;
	}

	public void setDeliverService(DeliverService deliverService) {
		this.deliverService = deliverService;
	}

	public Deliver getDeliver() {
		return deliver;
	}

	public void setDeliver(Deliver deliver) {
		this.deliver = deliver;
	}

}
