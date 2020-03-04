package com.glw.ad.dao.condition;

import com.glw.ad.entity.condition.CreativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : glw
 * @date : 2020/3/5
 * @time : 0:03
 * @Description : 创意与推广单元的关联表DAO
 */
public interface CreativeUnitRepository extends JpaRepository<CreativeUnit, Long> {
}
