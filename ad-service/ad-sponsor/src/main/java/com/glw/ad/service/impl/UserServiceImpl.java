package com.glw.ad.service.impl;

import com.glw.ad.constant.Constants;
import com.glw.ad.dao.AdUserRepository;
import com.glw.ad.entity.AdUser;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.IUserService;
import com.glw.ad.service.vo.request.CreateUserRequest;
import com.glw.ad.service.vo.response.CreateUserResponse;
import com.glw.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:47
 * @Description : 用户信息业务层接口实现
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository adUserRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository adUserRepository) {
        this.adUserRepository = adUserRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorCode.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = adUserRepository.findByUsername(request.getUsername());
        if (null != oldUser) {
            throw new AdException(Constants.ErrorCode.SAME_NAME_ERROR);
        }

        AdUser newUser = adUserRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
        ));

        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }
}
