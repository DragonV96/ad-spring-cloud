package com.glw.ad.service;

import com.glw.ad.exception.AdException;
import com.glw.ad.service.vo.request.CreateUserRequest;
import com.glw.ad.service.vo.response.CreateUserResponse;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:12
 * @Description : 用户信息业务层接口
 */
public interface IUserService {

    /**
     * 创建用户
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
