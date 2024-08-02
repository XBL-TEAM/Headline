package com.xblteam.util;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 */

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    FAIL(500,"fail"),
    USERNAME_ERROR(501,"用户名有误"),
    PASSWORD_ERROR(503,"密码有误"),
    NOT_LOGIN(504,"notLogin"),
    USERNAME_USED(505,"用户名占用"),
    USERNAME_NULL(506, "用户名为空");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}