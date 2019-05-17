package com.qc.information.config;

import com.qc.information.utils.JwtUtil;
import com.qc.information.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: czt
 * @Date: 18-11-30 下午4:17
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        //登录验证  如果有该注解则免校验
        HandlerMethod h = null;
        try {//避开swagger
            h = (HandlerMethod) handler;
        } catch (Exception e) {
            logger.error("cast Exception{}", e.getMessage());
            return false;
        }
        AuthPassport authPassport = h.getMethodAnnotation(AuthPassport.class);
        if (authPassport != null && authPassport.value() == false) {
            return true;
        } else {
            if (excludes(url)) {
                return true;
            } else {
                String token = request.getHeader("Token");
                if (StringUtil.isNullOrEmpty(token)) {
                    token = request.getParameter("token");
                }
                boolean valid = JwtUtil.verifyToken(token);
                if (valid) {
                    return true;
                } else {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("{\"status\":401,\"message\":\"没有授权或token过期，请重新登录。\"}");
                    return false;
                }
            }
        }
    }

    /**
     * 校验url是否是单独过滤开的如果有做配置则可以不需要登录则可以访问
     *
     * @param url
     * @return
     */
    private boolean excludes(String url) {
        String[] paths = {"/swagger-resources/**","/static/images/**", "/index/toLogin"};
        PathMatcher matcher = new AntPathMatcher();
        for (String path : paths) {
            if (matcher.match(path, url)) {
                return true;
            }
        }
        return false;
    }
}