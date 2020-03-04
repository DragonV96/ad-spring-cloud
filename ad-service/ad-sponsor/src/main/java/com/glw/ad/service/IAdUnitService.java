package com.glw.ad.service;

import com.glw.ad.exception.AdException;
import com.glw.ad.service.vo.request.*;
import com.glw.ad.service.vo.response.*;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:32
 * @Description : 推广单元业务层接口
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
