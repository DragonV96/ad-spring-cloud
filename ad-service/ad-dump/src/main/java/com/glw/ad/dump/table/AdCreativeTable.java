package com.glw.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : glw
 * @date : 2020/3/16
 * @time : 0:03
 * @Description : 创意导出表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeTable {

    private Long adId;

    private String name;

    private Byte type;

    private Byte materialType;

    private Integer height;

    private Integer width;

    private Byte auditStatus;

    private String adUrl;
}
