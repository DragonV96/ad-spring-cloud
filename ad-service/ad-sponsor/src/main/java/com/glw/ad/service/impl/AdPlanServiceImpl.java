package com.glw.ad.service.impl;

import com.glw.ad.entity.AdPlan;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.IAdPlanService;
import com.glw.ad.service.vo.request.AdPlanGetRequest;
import com.glw.ad.service.vo.request.AdPlanRequest;
import com.glw.ad.service.vo.response.AdPlanResponse;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:49
 * @Description : 推广计划业务接口实现
 */
public class AdPlanServiceImpl implements IAdPlanService {

    @Override
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        return null;
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        return null;
    }

    @Override
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        return null;
    }

    @Override
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

    }
}
