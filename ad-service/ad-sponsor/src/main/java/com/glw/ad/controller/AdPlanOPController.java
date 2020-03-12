package com.glw.ad.controller;

import com.alibaba.fastjson.JSON;
import com.glw.ad.entity.AdPlan;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.IAdPlanService;
import com.glw.ad.service.vo.request.AdPlanGetRequest;
import com.glw.ad.service.vo.request.AdPlanRequest;
import com.glw.ad.service.vo.response.AdPlanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/12
 * @time : 22:47
 * @Description : 推广计划控制层
 */
@Slf4j
@RestController
@Api(tags = "推广计划接口")
public class AdPlanOPController {

    private final IAdPlanService adPlanService;

    @Autowired
    public AdPlanOPController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    @ApiOperation(value = "创建推广计划")
    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: createAdPlan -> {}", JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }

    @ApiOperation(value = "获取推广计划")
    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException {
        log.info("ad-sponsor: getAdPlanByIds -> {}", JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }

    @ApiOperation(value = "更新推广计划")
    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: updateAdPlan -> {}", JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }

    @ApiOperation(value = "删除推广计划")
    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: deleteAdPlan -> {}", JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }
}
