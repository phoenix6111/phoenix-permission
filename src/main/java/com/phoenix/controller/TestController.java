package com.phoenix.controller;

import com.phoenix.common.JsonData;
import com.phoenix.dao.SysAclModuleMapper;
import com.phoenix.model.SysAclModule;
import com.phoenix.param.TestVO;
import com.phoenix.utils.ApplicationContextHelper;
import com.phoenix.utils.BeanValidator;
import com.phoenix.utils.JsonMapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * User: sheng
 * Date: 2018-04-22 15:50
 * Description:
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public JsonData hello() {
        log.info("Test hello world");
//        throw new PermissionException("Test Exception");
        return JsonData.success("Hello Test");
    }

    @RequestMapping("/validate")
    @ResponseBody
    public JsonData validate(TestVO testVO) {
        log.info("Test validate");

        /*try {
            Map<String,String> errors = BeanValidator.validateObject(testVO);
            if(MapUtils.isNotEmpty(errors)) {
                for(Map.Entry<String,String> entry : errors.entrySet()) {
                    log.info("{} --- {}",entry.getKey(),entry.getValue());
                }
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        */
        BeanValidator.check(testVO);
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule aclModule = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(aclModule));
        return JsonData.success("Test Validate");
    }

}
