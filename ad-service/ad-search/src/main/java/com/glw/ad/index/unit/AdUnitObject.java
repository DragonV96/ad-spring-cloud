package com.glw.ad.index.unit;

import com.glw.ad.index.plan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : glw
 * @date : 2020/3/14
 * @time : 0:06
 * @Description : 推广单元实体对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;

    private Byte unitStatus;

    private Byte positionType;

    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject newObject) {
        if (null != newObject.getUnitId()) {
            this.unitId = newObject.getUnitId();
        }
        if (null != newObject.getUnitStatus()) {
            this.unitStatus = newObject.getUnitStatus();
        }
        if (null != newObject.getPositionType()) {
            this.positionType = newObject.getPositionType();
        }
        if (null != planId) {
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getAdPlanObject()) {
            this.adPlanObject = newObject.getAdPlanObject();
        }
    }

}
