package hjj.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import hjj.entity.DeliverRecord;
import hjj.entity.Good;
import hjj.entity.PurchaseRecord;
import hjj.entity.ShopCarRecord;
import hjj.entity.User;
import hjj.service.UserService;
@Controller(value = "userAction")
@Scope(value = "prototype")
public class UserAction {
	@Resource(name = "userService")
	private UserService userService;
	private User user;
	
	//查看商品
	public String selectGoods() {
		List<Good> list = userService.selectGoods();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("good", list);
		
		return "selectGoods";
	}
	
	//添加购物车
	public String insertShopCar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String idR = request.getParameter("id");
		String numberR = request.getParameter("number");
		int id = 0;
		int number = 0;
		
		try {
			id = Integer.parseInt(idR);
			number = Integer.parseInt(numberR);
		} catch(NumberFormatException e) {
			request.setAttribute("error", "请输入数字");
			return "insertShopCar";
		}
		
		boolean flag = false;
		List<Good> list = userService.selectGoods();
		Good buyGood = null;
		for(Good good : list) {
			if(good.getId() == id) {
				buyGood = good;
				flag = true;
				break;
			}
		}
		
		if(flag) {
			userService.insertShopCar(buyGood, user, number);
			
			request.setAttribute("message", "添加购物车成功");
			return "success";
		} else {
			request.setAttribute("error", "商品不存在");
			return "insertShopCar";
		}
		
	}
	
	//确认收货
	public String confirmGood() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String sid = request.getParameter("id");
		int id = 0;
		
		try {
			id = Integer.parseInt(sid);
		} catch(NumberFormatException e) {
			request.setAttribute("error", "请输入数字");
			return "selectDeliverRecord";
		}
		List<PurchaseRecord> list = userService.selectPurchaseRecord(user);
		
		boolean flag = false;
		PurchaseRecord record = null;
		for(PurchaseRecord r : list) {
			if(r.getId() == id) {
				record = r;
				flag = true;
				break;
			}
		}
		
		if(flag) {
			userService.confirmGood(record);
			return "success";
		} else {
			request.setAttribute("error", "此货物不存在");
			return "selectDeliverRecord";
		}
	}
	
	//退货
	public String backGood() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		User user = (User)session.getAttribute("user");
		List<PurchaseRecord> acceptRecord = userService.selectAcceptRecord(user);
		String sid = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(sid);
		} catch(NumberFormatException e) {
			request.setAttribute("error", "请输入数字");
			return "selectAcceptRecord";
		}
		
		boolean flag = false;
		PurchaseRecord returnGood = null;
		for(PurchaseRecord p : acceptRecord) {
			if(id == p.getId()) {
				flag = true; 
				returnGood = p;
				break;
			}
		}
		
		if(flag) {
			request.setAttribute("message", "退货申请成功");
			userService.backGood(returnGood,user);
			return "success";
		} else {
			request.setAttribute("error", "此货单号不存在");
			return "selectAcceptRecord";
		}
	}
	//查看确认收货记录
	public String selectAcceptRecord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		User user = (User)session.getAttribute("user");
		List<PurchaseRecord> acceptRecord = userService.selectAcceptRecord(user);
		if(session.getAttribute("acceptRecord") == null) {
			session.setAttribute("acceptRecord", acceptRecord);
		}
		return "selectAcceptRecord";
	}
	//查看送货记录
	public String selectDeliverRecord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		User user = (User)session.getAttribute("user");
		List<PurchaseRecord> list = userService.selectPurchaseRecord(user);
		List<DeliverRecord> record = userService.selectDeliverRecord(user, list);
		
		request.setAttribute("deliverRecord", record);
		return "selectDeliverRecord";
	}
	//查看购买记录
	public String selectPurchaseRecord(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		User user = (User)session.getAttribute("user");
		List<PurchaseRecord> list = userService.selectPurchaseRecord(user);
		session.setAttribute("purchaseRecord", list);
		return "selectPurchaseRecord";
	}
	
	//购物车购买
	public String buyShopCar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("message", "购买成功");
		
		String[] id = request.getParameterValues("buyGood");
		int[] ids = new int[id.length];
		for(int i=0; i<id.length; i++) {
			ids[i] = Integer.parseInt(id[i]);
		}
		
		HttpSession session = request.getSession();
		Map<Good, Integer> shopCar = (Map<Good, Integer>)session.getAttribute("shopCar");
		List<ShopCarRecord> shopCarRecord = (List<ShopCarRecord>) session.getAttribute("shopCarRecord");
		Map<Good, Integer>  buyGood = new HashMap<>();
		Set<Good> set = shopCar.keySet();

		for(int i=0; i<ids.length; i++) {
			for(Good good : set) {
				if(ids[i] == good.getId()) {
					buyGood.put(good, shopCar.get(good));
				}
			}
		}
		
		User user = (User) session.getAttribute("user");
		userService.buyShopCar(buyGood, user, shopCarRecord);
		return "success";
	}
	//查看购物车
	public String selectShopCar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<ShopCarRecord> listRecord = userService.selectShopCar(user);
		List<Good> listGood = new ArrayList<>();
		List<Integer> number = new ArrayList<>(); 
		Map<Good, Integer> map = new HashMap<>();
		
		for(ShopCarRecord record : listRecord) {
			map.put(record.getGood(), record.getNumber());
		}		
		session.setAttribute("shopCar", map);
		session.setAttribute("shopCarRecord", listRecord);
		return "shopCar";
	}
	//修改个人信息
	public String updateData() {
		try {
			if(userService.updateData(user)) {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("message", "修改成功");
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("error", "修改失败");
			return "personData";
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("error", "修改失败");
		return "personData";
	}
	//查看个人信息
	public String personData() {
		return "personData";
	}
	//退出
	public String quit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return "quit";
	}
	//登录
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if(userService.select(user)) {
			ServletContext application = request.getServletContext();
			Map<String, HttpSession> loginMap = (Map<String, HttpSession>) application.getAttribute("userLoginMap");
			
			if(loginMap.containsKey(user.getUsername())) {
				HttpSession s = loginMap.get(user.getUsername());
				s.invalidate();
				//从列表中删除
				loginMap.remove(user.getUsername());
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//添加
			loginMap.put(user.getUsername(), session);
			return "success";
		} else {
			request.setAttribute("error", "登录名或者密码错误");
			return "login";
		}
	}
	//注册
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("user.username");
		String password = request.getParameter("user.password");
		String password2 = request.getParameter("password2");
		String money = request.getParameter("user.money");
		String phone = request.getParameter("user.phone");
		String address = request.getParameter("user.address");
		
		if(username.trim() == null || username.trim() == "") {
			request.setAttribute("error", "用户名不能为空");
			return "register";
		}
		if(password.trim() == null || password.trim() == "") {
			request.setAttribute("error", "密码不能为空");
			return "register";
		}
		if(password2.trim() == null || password2.trim() == "") {
			request.setAttribute("error", "确认密码不能为空");
			return "register";
		}
		if(money.trim() == null || money.trim() == "") {
			user.setMoney(0);
		}
		if(phone.trim() == null || phone.trim() == "") {
			request.setAttribute("error", "手机号不能为空");
			return "register"; 
		}
		if(address.trim() == null || address.trim() == "") {
			request.setAttribute("error", "地址不能为空");
			return "register"; 
		}
		if(!password.equals(password2)) {
			request.setAttribute("error", "两次输入的密码不一致");
			return "register";
		} 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = df.format(new Date());
		user.setDate(date);
		//受影响行数
		int number;
		try {
			number = userService.insert(user);
			
			if(number > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
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
	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
}
