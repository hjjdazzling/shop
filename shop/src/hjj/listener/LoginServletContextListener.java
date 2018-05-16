package hjj.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

/**
 * Application Lifecycle Listener implementation class LoginServletContextListener
 *
 */
@WebListener
public class LoginServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
        ServletContext application = sce.getServletContext();
        Map<HttpSession, String> usermap = new ConcurrentHashMap<>();
        Map<HttpSession, String> delivermap = new ConcurrentHashMap<>();
        Map<HttpSession, String> shopmap = new ConcurrentHashMap<>();
        
        application.setAttribute("userLoginMap", usermap);
        application.setAttribute("deliverLoginMap", delivermap);
        application.setAttribute("shopLoginMap", shopmap);
    }
	
}
