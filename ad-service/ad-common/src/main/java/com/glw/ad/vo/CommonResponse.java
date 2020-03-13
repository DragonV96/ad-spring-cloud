package com.glw.ad.vo;

import com.glw.ad.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/3/1
 * @time : 13:07
 * @Description : 统一响应实体
 */
@Data
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    private static final String SUCCESS_DESC = "执行成功";
    private static final int SUCCESS_CODE = 0;

    public CommonResponse() {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_DESC;
    }

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(T data) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_DESC;
        this.data = data;
    }

    public CommonResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    /**
     * 返回成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(data);
    }

    /**
     * 返回成功(无参数)
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success() {
        return new CommonResponse<T>();
    }

    /**
     * 返回失败
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> error(ErrorCode errorCode) {
        return new CommonResponse<T>(errorCode);
    }
}
