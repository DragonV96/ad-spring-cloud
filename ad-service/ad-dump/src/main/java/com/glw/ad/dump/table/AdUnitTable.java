package com.glw.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : glw
 * @date : 2020/3/16
 * @time : 0:00
 * @Description : 推广单元导出表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitTable {

    private Long unitId;

    private Byte unitStatus;

    private Byte positionType;

    private Long planId;
}
