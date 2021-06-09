package com.bjpowernode.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EcodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤字符编码的过滤器");

        //过滤post请求中文参数乱码的问题
        req.setCharacterEncoding("UTF-8");
        //过滤响应流响应中文乱码
         resp.setContentType("text/html;charset=UTF-8");

         //请求放行
        chain.doFilter(req,resp);
    }
}
