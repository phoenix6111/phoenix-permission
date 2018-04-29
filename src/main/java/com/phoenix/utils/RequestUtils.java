package com.phoenix.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * User: sheng
 * Date: 2018-04-26 10:18
 * Description:
 */
public class RequestUtils {

    public static boolean isAjax(HttpServletRequest request) {
        String xReq = request.getHeader("x-requested-with");
        return StringUtils.isNotBlank(xReq) && "XMLHttpRequest".equalsIgnoreCase(xReq);
    }

}
