package com.glw.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : glw
 * @date : 2020/3/16
 * @time : 0:03
 * @Description : 创意与推广单元关联导出表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeUnitTable {

    private Long adId;

    private Long unitId;
}
