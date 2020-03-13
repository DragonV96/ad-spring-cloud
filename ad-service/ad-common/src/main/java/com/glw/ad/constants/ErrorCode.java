package com.glw.ad.constants;

import lombok.Getter;

/**
 * @author : glw
 * @date : 2020/3/13
 * @time : 23:40
 * @Description : 系统错误码
 */
@Getter
public enum ErrorCode {

    SPONSOR_ERROR(100001, "投放系统出现错误");

    /** 错误码 */
    private int code;

    /** 错误描述 */
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
