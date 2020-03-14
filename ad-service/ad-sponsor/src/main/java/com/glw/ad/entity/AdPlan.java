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
 * @Description : 推广计划表 表名：ad_plan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_plan")
public class AdPlan {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 标记当前记录所属用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 推广计划名称
     */
    @Column(name = "plan_name", nullable = false)
    private String planName;

    /**
     * 推广计划状态
     */
    @Column(name = "plan_status", nullable = false)
    private Byte planStatus;

    /**
     * 推广计划开始时间；
     */
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    /**
     * 推广计划结束时间；
     */
    @Column(name = "end_date", nullable = false)
    private Date endDate;

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

    public AdPlan(Long userId, String planName,
                  Date startDate, Date endDate) {

        this.userId = userId;
        this.planName = planName;
        this.planStatus = CommonStatusEnum.VALID.getStatus();
        this.startDate = startDate;
        this.endDate = endDate;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}