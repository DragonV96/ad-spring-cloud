package com.glw.ad.advice;

import com.glw.ad.exception.AdException;
import com.glw.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : glw
 * @date : 2020/3/1
 * @time : 14:29
 * @Description : 全局异常拦截处理
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException e) {
        CommonResponse<String> response = new CommonResponse<>(-1, "system error");
        response.setData(e.getMessage());
        return response;
    }
}
