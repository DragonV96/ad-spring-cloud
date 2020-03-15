package com.glw.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : glw
 * @date : 2020/3/15
 * @time : 23:54
 * @Description : 推广计划导出表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanTable {

    private Long id;

    private Long userId;

    private Byte planStatus;

    private Date startDate;

    private Date endDate;
}
