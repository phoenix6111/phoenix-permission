package com.phoenix.common;

import com.phoenix.exception.ParamException;
import com.phoenix.exception.PermissionException;
import com.phoenix.utils.RequestUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * User: sheng
 * Date: 2018-04-26 10:15
 * Description: 全局的异常处理类
 */
@Slf4j
@Component
public class SpringExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("ExceptionResolver");
        String requestUrl = request.getRequestURI();
        String defaultMsg = "System error";
        RuntimeException exception = null;

        //如果是PermissionException或者是ParamException，则直接返回对应的message信息，否则打印堆栈，返回defaultMsg
        if(ex instanceof PermissionException) {
            exception = (PermissionException) ex;
        } else if(ex instanceof ParamException) {
            exception = (ParamException)ex;
        } else {
            log.error("Unknown Exception, url : "+requestUrl,ex);
            exception = new PermissionException(defaultMsg);
        }

        //判断是ajax请求还是页面请求，如果是ajax请求，则返回json格式的数据，如果是页面请求，则返回到指定的异常页面
        //如果捕捉到的是Permission，则直接返回exception包含的信息
        String viewName = RequestUtils.isAjax(request)? "jsonView" : "exception";
        JsonData jsonData = JsonData.fail(exception.getMessage());

        return new ModelAndView(viewName,jsonData.toMap());
    }
}
