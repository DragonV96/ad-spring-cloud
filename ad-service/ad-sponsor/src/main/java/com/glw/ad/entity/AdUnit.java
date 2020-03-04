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
 * @Description : 表名：ad_unit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit")
public class AdUnit {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 关联推广计划 id
     */
    @Column(name = "plan_id", nullable = false)
    private Long planId;

    /**
     * 推广单元名称
     */
    @Column(name = "unit_name", nullable = false)
    private String unitName;

    /**
     * 推广单元状态
     */
    @Column(name = "unit_status", nullable = false)
    private Byte unitStatus;

    /**
     * 广告位类型(开屏, 贴片, 中贴, 暂停帖, 后贴)
     */
    @Column(name = "position_type", nullable = false)
    private Byte positionType;

    /**
     * 预算
     */
    @Column(name = "budget", nullable = false)
    private Long budget;

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

    public AdUnit(Long planId, String unitName,
                  Byte positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatusEnum.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}