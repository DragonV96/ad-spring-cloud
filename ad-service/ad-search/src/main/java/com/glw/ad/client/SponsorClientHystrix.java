package com.glw.ad.client;

import com.glw.ad.client.vo.AdPlan;
import com.glw.ad.client.vo.AdPlanGetRequest;
import com.glw.ad.constants.ErrorCode;
import com.glw.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/13
 * @time : 23:39
 * @Description : 广告投放系统断路器
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(ErrorCode.SPONSOR_ERROR);
    }
}
