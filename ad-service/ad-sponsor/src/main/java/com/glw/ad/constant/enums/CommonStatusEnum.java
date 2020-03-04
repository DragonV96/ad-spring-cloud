package com.glw.ad.constant.enums;

import lombok.Getter;

/**
 * @author : glw
 * @date : 2020/3/4
 * @time : 16:35
 * @Description : 有效状态枚举
 */
@Getter
public enum CommonStatusEnum {

    INVALID((byte) 0, "无效状态"),
    VALID((byte) 1, "有效状态");

    private Byte status;
    private String desc;

    CommonStatusEnum(Byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static String getDescByStatus(Byte status) {
        for (CommonStatusEnum commonStatusEnum : CommonStatusEnum.values()){
            if(commonStatusEnum.status == status){
                return commonStatusEnum.desc;
            }
        }
        return null;
    }
}
