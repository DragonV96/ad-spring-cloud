package com.glw.ad.controller;

import com.alibaba.fastjson.JSON;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.IAdUnitService;
import com.glw.ad.service.vo.request.*;
import com.glw.ad.service.vo.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : glw
 * @date : 2020/3/12
 * @time : 22:41
 * @Description : 推广单元控制层
 */
@Slf4j
@RestController
@Api(tags = "推广单元接口")
public class AdUnitOPController {

    private final IAdUnitService adUnitService;

    @Autowired
    public AdUnitOPController(IAdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }

    @ApiOperation(value = "创建推广单元")
    @PostMapping("/create/adUnit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor: createUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createUnit(request);
    }

    @ApiOperation(value = "创建推广单元关键词")
    @PostMapping("/create/unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException {
        log.info("ad-sponsor: createUnitKeyword -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitKeyword(request);
    }

    @ApiOperation(value = "创建推广单元兴趣")
    @PostMapping("/create/unitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request) throws AdException {
        log.info("ad-sponsor: createUnitIt -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    @ApiOperation(value = "创建推广单元地域")
    @PostMapping("/create/unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor: createUnitDistrict -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitDistrict(request);
    }

    @ApiOperation(value = "创建创意与推广单元的关联")
    @PostMapping("/create/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(@RequestBody CreativeUnitRequest request) throws AdException {
        log.info("ad-sponsor: createCreativeUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createCreativeUnit(request);
    }
}
