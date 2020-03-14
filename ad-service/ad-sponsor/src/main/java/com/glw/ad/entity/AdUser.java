package com.glw.ad.entity;

import com.glw.ad.constant.enums.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : glw
 * @date : 2020/3/3
 * @time : 23:39
 * @Description : 用户表 表名：ad_user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_user")
public class AdUser {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 给用户生成的 token
     */
    @Column(name = "token", nullable = false)
    private String token;

    /**
     * 用户状态
     */
    @Column(name = "user_status", nullable = false)
    private Byte userStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUser(String username, String token) {

        this.username = username;
        this.token = token;
        this.userStatus = CommonStatusEnum.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}