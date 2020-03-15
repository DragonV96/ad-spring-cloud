package com.glw.ad.dao;

import com.glw.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/3
 * @time : 23:39
 * @Description : 推广单元表DAO
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {

    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAllByUnitStatus(Byte unitStatus);
}
