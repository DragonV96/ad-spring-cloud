package com.glw.ad.dao.condition;

import com.glw.ad.entity.condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:01
 * @Description : 推广单元关键词DAO
 */
public interface AdUnitKeywordRepository extends JpaRepository<AdUnitKeyword, Long> {
}
