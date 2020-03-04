package com.glw.ad.service.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:35
 * @Description : 推广计划响应实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanResponse {

    private Long id;

    private String planName;
}
