package cn.edu.bdu.book;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(((HttpServletRequest) req).getRequestURI().contains(".css") || ((HttpServletRequest) req).getRequestURI().contains(".js")){
            chain.doFilter(req, resp);
        }else{
            response.setDateHeader("Expires", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html;chartset=" + "utf-8");
            request.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setContentType("text/html;charset=utf-8");
//        System.out.println("Filter");
            chain.doFilter(req, resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
