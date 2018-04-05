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

import hjj.entity.Good;
import hjj.entity.Shop;
import hjj.service.ShopService;

@Controller(value = "shopAction")
@Scope(value = "prototype")
public class ShopAction {
	@Resource(name = "shopService")
	private ShopService shopService;
	private Shop shop;
	private Good good;
	
	//更新商品信息
	public String updateGood() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Shop shop = (Shop) session.getAttribute("shop");
		
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String function = request.getParameter("function");
		String priceStr = request.getParameter("price");
		
		if(idStr == null || idStr.trim().equals("")) {
			request.setAttribute("error", "id不能为空");
			return "selectGood";
		}
		
		if(name == null || name.trim().equals("")) {
			request.setAttribute("error", "商品名字不能为空");
			return "selectGood";
		}
		
		if(function == null || function.trim().equals("")) {
			request.setAttribute("error", "商品描述不能为空");
			return "selectGood";
		}

		if(priceStr == null || priceStr.trim().equals("")) {
			request.setAttribute("error", "商品价格不能为空");
			return "selectGood";
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			request.setAttribute("error", "id只能是数字");
			return "selectGood";
		}
		
		double price = 0.0;
		try {
			price = Double.parseDouble(request.getParameter("price"));
		} catch(NumberFormatException e) {
			request.setAttribute("error", "价格只能是数字");
			return "selectGood";
		}
		
		if(price < 0) {
			request.setAttribute("error", "价格不能为负数");
			return "selectGood";
		}
		
		List<Good> list = shopService.selectGood(shop);
		boolean flag = false;
		for(Good g : list) {
			if(g.getId() == id) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			Good good = new Good();
			good.setId(id);
			good.setName(name);
			good.setPrice(price);
			good.setFunction(function);
			good.setShop(shop);
			shopService.updateGood(good);
			return "success";
		} else {
			request.setAttribute("error", "商品id不存在");
			return "selectGood";
		}
	}
	//上架新的商品
	public String insertGood() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("message", "上架商品成功");
		HttpSession session = request.getSession();
		Shop shop = (Shop) session.getAttribute("shop");
		good.setShop(shop);
		shopService.insertGood(good);
		return "success";
	}
	//查看自己上架商品left
	public String selectGood() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Shop shop = (Shop)session.getAttribute("shop");
		List<Good> list = shopService.selectGood(shop);
		
		session.setAttribute("good", list);
		return "selectGood";
	}
	
	//更新个人信息
	public String updateShop() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Shop shop = (Shop)session.getAttribute("shop");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String moneyStr = request.getParameter("money");
		String address = request.getParameter("address");
		double money = 0;
		try {
			money = Double.parseDouble(moneyStr);
		} catch(NumberFormatException e) {
			request.setAttribute("error", "请输入数字金额");
			return "shop";
		}
		
		if(money < 0) {
			request.setAttribute("error", "请输入正数数字金额");
			return "shop";
		}
		if(password.trim().equals("") || phone.trim().equals("") || address.trim().equals("")) {
			request.setAttribute("error", "不能为空");
			return "shop";
		}
		shop.setMoney(money);
		shop.setPassword(password);
		shop.setAddress(address);
		shop.setPhone(phone);
		shopService.updateShop(shop);
		request.setAttribute("message", "更新个人信息成功");
		return "success";
	}
	
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if(shopService.login(shop)) {
			HttpSession session = request.getSession();
			session.setAttribute("shop", shop);
			return "success";
		} else {
			
			request.setAttribute("error", "登录名或者密码错误");
			return "login";
		}
		
		
	}
	
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String username = request.getParameter("shop.username");
		String password = request.getParameter("shop.password");
		String password2 = request.getParameter("password2");
		String money = request.getParameter("shop.money");
		String phone = request.getParameter("shop.phone");
		String address = request.getParameter("shop.address");
		
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
			shop.setMoney(0);
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
		shop.setDate(date);
		
		try {
			shopService.register(shop);
		} catch (Exception e) {
			request.setAttribute("error", "用户名已存在");
			return "register";
		}
		session.setAttribute("shop", shop);
		return "success";
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}
	
	
}
