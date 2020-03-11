package com.glw.ad.service.impl;

import com.glw.ad.dao.AdCreativeRepository;
import com.glw.ad.entity.AdCreative;
import com.glw.ad.service.IAdCreativeService;
import com.glw.ad.service.vo.request.CreativeRequest;
import com.glw.ad.service.vo.response.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:47
 * @Description : 创意业务层接口实现
 */
@Service
public class CreativeServiceImpl implements IAdCreativeService {

    private final AdCreativeRepository adCreativeRepository;

    @Autowired
    public CreativeServiceImpl(AdCreativeRepository adCreativeRepository) {
        this.adCreativeRepository = adCreativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        AdCreative adCreative = adCreativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(adCreative.getId(), adCreative.getName());
    }
}
