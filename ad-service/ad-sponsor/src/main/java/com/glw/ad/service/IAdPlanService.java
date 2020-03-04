package com.glw.ad.service;

import com.glw.ad.entity.AdPlan;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.vo.request.AdPlanGetRequest;
import com.glw.ad.service.vo.request.AdPlanRequest;
import com.glw.ad.service.vo.response.AdPlanResponse;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:45
 * @Description : 推广计划业务接口
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
