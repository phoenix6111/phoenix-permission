package com.phoenix.common;

import com.phoenix.utils.JsonMapper;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * User: sheng
 * Date: 2018-04-29 9:19
 * Description: Http前后监听工具
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "requestStartTime";

    /**
     * 任何一请求，在请求处理之前都会被preHandle方法处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //记录请求信息
        String requestUrl = request.getRequestURI();
        Map parameters = request.getParameterMap();
        log.info("request start. url:{}, params:{}",requestUrl,JsonMapper.obj2String(parameters));

        //记录请求开始时间
        request.setAttribute(START_TIME,System.currentTimeMillis());

        return true;
    }

    /**
     * 任何一请求，在请求正常处理之后都会被postHandle方法处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {



    }

    /**
     * 任何一个请求，在请求处理完成之后都会触发afterCompletion()方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //记录请求信息
        String requestUrl = request.getRequestURI();
        Map parameters = request.getParameterMap();
        //计算整个请求耗费的时间
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();

        log.info("request complete. url:{}, cost: {}",requestUrl,end-start);
    }
}
