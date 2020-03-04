package com.glw.ad.dao;

import com.glw.ad.entity.AdCreative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : glw
 * @date : 2020/3/3
 * @time : 23:39
 * @Description : 创意表DAO
 */
public interface AdCreativeRepository  extends JpaRepository<AdCreative, Long> {
}
