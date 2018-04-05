package hjj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ShopLoginIntecerptor extends MethodFilterInterceptor {
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("shop") != null) {
			return invocation.invoke();
		} else {
			request.setAttribute("error", "请先登录");
			return "login";
		}
		
	}
	
}
