package com.example.rentalHome.AppConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HandlerInterceptorConfig extends HandlerInterceptorAdapter
{
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception
	{
		 String split = request.getRequestURI();
		 String[] hope = split.split("/");
		 	 
		 for ( int i = 0; i < hope.length; i++)
		    {
		           if(hope[i].equals("v1.0"))
		           {
		        	   log.info(" HandlerInterceptor : Older Version : v1.0");
		        	   //response.sendRedirect("");
		        	   return true;
		           }
		           else if(hope[i].equals("v1.1"))
		           {
					   log.info(" HandlerInterceptor : Current Version : v1.1");
		        	   return true;
		           }
		           else if(hope[i].equals("v1.2"))
		           {
					   log.info(" HandlerInterceptor :  New Version can you use it : v1.2");
		        	   return true;
		           }
		    }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		}

	@Override
	public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
