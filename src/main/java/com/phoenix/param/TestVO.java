package com.phoenix.param;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * User: sheng
 * Date: 2018-04-28 21:46
 * Description:
 */
@Getter
@Setter
public class TestVO {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "msg不能为空")
    private String msg;

}
