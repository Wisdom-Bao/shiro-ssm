package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author WisdomBao
 * @Date 2020/5/27 18:30
 * @Version 1.0
 */
public class WebConfig implements Filter{
    private boolean isCross=false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        isCross = true;
        System.out.println("begin cross");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        System.out.println("拦截请求lan jie qing qiu:" + req.getServletPath());
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-with, If-Modified-Since, Pragma, Last-Modified, Cache-Control,Expires, Content-Type, X-E4M-With");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("XDomainRequestAllowed", "1");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
//    private boolean isCross = false;
//
//    @Override
//    public void destroy() {
//        isCross = false;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//        if (isCross) {
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//            System.out.println("拦截请求lan jie qing qiu: " + httpServletRequest.getServletPath());
//
//            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//            httpServletResponse.setHeader("Access-Control-Max-Age", "0");
//            httpServletResponse.setHeader("Access-Control-Allow-Headers",
//                    "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//            httpServletResponse.setHeader("XDomainRequestAllowed", "1");
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        String isCrossStr = filterConfig.getInitParameter("IsCross");
//        isCross = isCrossStr.equals("true") ? true : false;
//        System.out.println("isCrossStr"+isCrossStr);
//    }

}
