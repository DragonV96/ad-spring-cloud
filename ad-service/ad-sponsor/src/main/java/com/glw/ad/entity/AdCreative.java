package com.glw.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : glw
 * @date : 2020/3/3
 * @time : 23:29
 * @Description : 表名：ad_creative
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_creative")
public class AdCreative {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 创意名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 物料类型(图片, 视频)
     */
    @Column(name = "type", nullable = false)
    private Byte type;

    /**
     * 物料子类型(图片: bmp, jpg 等等)
     */
    @Column(name = "material_type", nullable = false)
    private Byte materialType;

    /**
     * 高度
     */
    @Column(name = "height", nullable = false)
    private Integer height;

    /**
     * 宽度
     */
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     * 物料大小, 单位是 KB
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * 持续时长, 只有视频才不为 0
     */
    @Column(name = "duration", nullable = false)
    private Integer duration;

    /**
     * 审核状态
     */
    @Column(name = "audit_status", nullable = false)
    private Byte auditStatus;

    /**
     * 标记当前记录所属用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 物料地址
     */
    @Column(name = "url", nullable = false)
    private String url;

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
}