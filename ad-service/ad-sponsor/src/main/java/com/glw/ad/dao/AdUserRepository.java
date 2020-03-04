package com.glw.ad.dao;

import com.glw.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : glw
 * @date : 2020/3/3
 * @time : 23:39
 * @Description : 用户信息表DAO
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查找用户记录
     * @param username
     * @return
     */
    AdUser findByUsername(String username);
}
