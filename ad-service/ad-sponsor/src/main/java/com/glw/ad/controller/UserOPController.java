package com.glw.ad.controller;

import com.alibaba.fastjson.JSON;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.IUserService;
import com.glw.ad.service.vo.request.CreateUserRequest;
import com.glw.ad.service.vo.response.CreateUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : glw
 * @date : 2020/3/12
 * @time : 22:38
 * @Description : 用户控制层
 */
@Slf4j
@RestController
@Api(tags = "用户接口")
public class UserOPController {

    private final IUserService userService;

    @Autowired
    public UserOPController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "创建用户")
    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor : createUser -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }

}
