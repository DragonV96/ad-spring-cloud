package com.glw.ad.service.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:40
 * @Description : 推广单元兴趣响应实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItResponse {

    private List<Long> ids;
}
