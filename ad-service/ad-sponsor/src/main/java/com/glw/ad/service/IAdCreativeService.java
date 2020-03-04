package com.glw.ad.service;

import com.glw.ad.service.vo.request.CreativeRequest;
import com.glw.ad.service.vo.response.CreativeResponse;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:25
 * @Description : 创意业务层接口
 */
public interface IAdCreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
