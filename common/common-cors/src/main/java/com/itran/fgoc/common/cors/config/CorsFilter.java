package com.itran.fgoc.common.cors.config;

import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 */
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String method = req.getMethod();
        // this origin value could just as easily have come from a database
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type,Last-Modified,device,token");
        if ("OPTIONS".equals(method)) {//检测是options方法则直接返回200
            res.setStatus(HttpStatus.OK.value());
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 从web.xml中加载初始化参数
     *
     * @param fConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig fConfig) {
    }
}
