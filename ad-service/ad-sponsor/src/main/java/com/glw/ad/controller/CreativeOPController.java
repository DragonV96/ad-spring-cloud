package com.glw.ad.controller;

import com.alibaba.fastjson.JSON;
import com.glw.ad.service.IAdCreativeService;
import com.glw.ad.service.vo.request.CreativeRequest;
import com.glw.ad.service.vo.response.CreativeResponse;
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
 * @time : 22:45
 * @Description : 创意控制层
 */
@Slf4j
@RestController
@Api(tags = "创意接口")
public class CreativeOPController {

    private final IAdCreativeService creativeService;

    @Autowired
    public CreativeOPController(IAdCreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @ApiOperation(value = "创建创意")
    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) {
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
