package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserInfoFilter implements Filter {
    private FilterConfig filterConfig = null;
    public void init(FilterConfig config) throws ServletException{
        this.filterConfig = config;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException , IOException {
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        if(session == null){
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers","Content-Type");
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Method","*");
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin","*");
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Credentials","true");
            ((HttpServletResponse)response).setHeader("content-type","text/html;charset=UTF-8");
            ((HttpServletResponse)response).setCharacterEncoding("GB2312");
            ((HttpServletResponse)response).setContentType("text/plain");
            ((HttpServletResponse)response).sendRedirect("http://www.brandonxcc.top:8080/MyNetEase/login/checkLogin");
        }else{
            chain.doFilter(request,response);
        }
    }
    public void destory(){
    }
}
