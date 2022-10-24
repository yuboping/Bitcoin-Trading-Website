package com.gsww.qyws.gzbd.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class RequestFilter implements Filter {
	private String[] excludedUris;  
    public static final String REQUEST_HEADER_NAME = "X-REQUEST-UUID";

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException,
            ServletException {
    	HttpServletRequest request = (HttpServletRequest) srequest;  
         HttpServletResponse response = (HttpServletResponse) sresponse;  
         String uri = request.getServletPath(); 
         chain.doFilter(srequest, sresponse);  
         if(isExcludedUri(uri)){  
        	 chain.doFilter(srequest, sresponse);  
         }else if(request.getSession().getAttribute("sysUserSession")!=null){  
        	 chain.doFilter(srequest, sresponse);  
         }else{  
             response.sendRedirect(request.getContextPath() + "/time_out.html");  
         }  
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	 excludedUris = filterConfig.getInitParameter("excludedUri").split(","); 
    }

    @Override
    public void destroy() {
    }
    private boolean isExcludedUri(String uri) {  
        if (excludedUris == null || excludedUris.length <= 0) {  
            return false;  
        }  
        for (String ex : excludedUris) {  
            uri = uri.trim();  
            ex = ex.trim();  
            if (uri.toLowerCase().matches(ex.toLowerCase().replace("*",".*")))  
                return true;  
        }  
        return false;  
    }  
}

