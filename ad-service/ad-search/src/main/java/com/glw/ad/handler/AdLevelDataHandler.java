package com.glw.ad.handler;

import com.alibaba.fastjson.JSON;
import com.glw.ad.dump.table.*;
import com.glw.ad.index.DataTable;
import com.glw.ad.index.IndexAware;
import com.glw.ad.index.creative.CreativeIndex;
import com.glw.ad.index.creative.CreativeObject;
import com.glw.ad.index.creativeunit.CreativeUnitIndex;
import com.glw.ad.index.creativeunit.CreativeUnitObject;
import com.glw.ad.index.district.UnitDistrictIndex;
import com.glw.ad.index.interest.UnitItIndex;
import com.glw.ad.index.keyword.UnitKeywordIndex;
import com.glw.ad.index.plan.AdPlanIndex;
import com.glw.ad.index.plan.AdPlanObject;
import com.glw.ad.index.unit.AdUnitIndex;
import com.glw.ad.index.unit.AdUnitObject;
import com.glw.ad.mysql.constant.OpTypeEnum;
import com.glw.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : glw
 * @date : 2020/3/17
 * @time : 22:45
 * @Description : 索引之间根据层级划分，即依赖关系；加载全量索引就是增量索引“添加”的一种特殊实现
 */
@Slf4j
public class AdLevelDataHandler {

    /**
     * 处理第二层索引——推广计划
     * @param adPlanTable
     * @param type
     */
    public static void handleLevel2(AdPlanTable adPlanTable, OpTypeEnum type) {
        AdPlanObject adPlanObject = new AdPlanObject(
                adPlanTable.getId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate()
        );
        handleBinlogEvent(DataTable.of(AdPlanIndex.class), adPlanObject.getPlanId(), adPlanObject, type);
    }

    /**
     * 处理第二层索引——推广创意
     * @param creativeTable
     * @param type
     */
    public static void handleLevel2(AdCreativeTable creativeTable, OpTypeEnum type) {
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(DataTable.of(CreativeIndex.class), creativeObject.getAdId(), creativeObject, type);
    }

    /**
     * 处理第三层索引——推广单元
     * @param unitTable
     * @param type
     */
    public static void handleLevel3(AdUnitTable unitTable, OpTypeEnum type) {
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
        if (null == adPlanObject) {
            log.error("handleLevel3 found AdPlanObject error: {}", unitTable.getPlanId());
            return;
        }

        AdUnitObject unitObject = new AdUnitObject(
                unitTable.getUnitId(),
                unitTable.getUnitStatus(),
                unitTable.getPositionType(),
                unitTable.getPlanId(),
                adPlanObject
        );

        handleBinlogEvent(DataTable.of(AdUnitIndex.class), unitTable.getUnitId(), unitObject, type);
    }

    /**
     * 处理第三层索引——创意与推广单元关联
     * @param creativeUnitTable
     * @param type
     */
    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpTypeEnum type) {
        if (type == OpTypeEnum.UPDATE) {
            log.error("CreativeUnitIndex not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());

        if (null == unitObject || null == creativeObject) {
            log.error("AdCreativeUnitTable index error: {}", JSON.toJSONString(creativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(creativeUnitTable.getAdId(), creativeUnitTable.getUnitId());
        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(), creativeUnitObject.getUnitId().toString()), creativeUnitObject, type
        );
    }

    /**
     * 处理第四层索引——推广单元地域维度
     * @param unitDistrictTable
     * @param type
     */
    public static void handleLevel4(AdUnitDistrictTable unitDistrictTable, OpTypeEnum type) {
        if (type == OpTypeEnum.UPDATE) {
            log.error("district index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitDistrictTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitDistrictTable index error: {}", unitDistrictTable.getUnitId());
            return;
        }

        String key = CommonUtils.stringConcat(unitDistrictTable.getProvince(), unitDistrictTable.getCity());
        Set<Long> value = new HashSet<>(
                Collections.singleton(unitDistrictTable.getUnitId())
        );
        handleBinlogEvent(DataTable.of(UnitDistrictIndex.class), key, value, type);
    }

    /**
     * 处理第四层索引——推广兴趣维度
     * @param unitItTable
     * @param type
     */
    public static void handleLevel4(AdUnitItTable unitItTable, OpTypeEnum type) {
        if (type == OpTypeEnum.UPDATE) {
            log.error("it index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitItTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitItTable index error: {}", unitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(unitItTable.getUnitId()));
        handleBinlogEvent(DataTable.of(UnitItIndex.class), unitItTable.getItTag(), value, type);
    }

    /**
     * 处理第四层索引——推广关键词维度
     * @param keywordTable
     * @param type
     */
    public static void handleLevel4(AdUnitKeywordTable keywordTable, OpTypeEnum type) {
        if (type == OpTypeEnum.UPDATE) {
            log.error("keyword index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(keywordTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitKeywordTable index error: {}", keywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(keywordTable.getUnitId()));
        handleBinlogEvent(DataTable.of(UnitKeywordIndex.class), keywordTable.getKeyword(), value, type);
    }

    /**
     * 处理binlog索引
     * @param index
     * @param key
     * @param value
     * @param type
     * @param <K>
     * @param <V>
     */
    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index, K key, V value, OpTypeEnum type) {
        switch (type) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }
}
