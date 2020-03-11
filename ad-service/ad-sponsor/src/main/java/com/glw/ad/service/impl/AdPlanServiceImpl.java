package com.glw.ad.service.impl;

import com.glw.ad.constant.Constants;
import com.glw.ad.constant.enums.CommonStatusEnum;
import com.glw.ad.dao.AdPlanRepository;
import com.glw.ad.dao.AdUserRepository;
import com.glw.ad.entity.AdPlan;
import com.glw.ad.entity.AdUser;
import com.glw.ad.exception.AdException;
import com.glw.ad.service.IAdPlanService;
import com.glw.ad.service.vo.request.AdPlanGetRequest;
import com.glw.ad.service.vo.request.AdPlanRequest;
import com.glw.ad.service.vo.response.AdPlanResponse;
import com.glw.ad.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:49
 * @Description : 推广计划业务接口实现
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository adUserRepository;
    private final AdPlanRepository adPlanRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository adUserRepository, AdPlanRepository adPlanRepository) {
        this.adUserRepository = adUserRepository;
        this.adPlanRepository = adPlanRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorCode.REQUEST_PARAM_ERROR);
        }

        // 确保关联的User是存在的
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorCode.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = adPlanRepository.findByUserIdAndPlanName(
                request.getUserId(), request.getPlanName()
        );
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorCode.SAME_NAME_PLAN_ERROR);
        }

        AdPlan newAdPlan = adPlanRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );
        return new AdPlanResponse(newAdPlan.getId(),
                newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorCode.REQUEST_PARAM_ERROR);
        }

        return adPlanRepository.findAllByIdInAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {

        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorCode.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorCode.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }

        plan.setUpdateTime(new Date());
        plan = adPlanRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorCode.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorCode.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatusEnum.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
    }
}
