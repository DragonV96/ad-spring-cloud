package com.glw.ad.dump.service;

import com.alibaba.fastjson.JSON;
import com.glw.ad.constant.enums.CommonStatusEnum;
import com.glw.ad.dao.AdCreativeRepository;
import com.glw.ad.dao.AdPlanRepository;
import com.glw.ad.dao.AdUnitRepository;
import com.glw.ad.dao.condition.AdUnitDistrictRepository;
import com.glw.ad.dao.condition.AdUnitItRepository;
import com.glw.ad.dao.condition.AdUnitKeywordRepository;
import com.glw.ad.dao.condition.CreativeUnitRepository;
import com.glw.ad.dump.constants.Constant;
import com.glw.ad.dump.table.*;
import com.glw.ad.entity.AdCreative;
import com.glw.ad.entity.AdPlan;
import com.glw.ad.entity.AdUnit;
import com.glw.ad.entity.condition.AdUnitDistrict;
import com.glw.ad.entity.condition.AdUnitIt;
import com.glw.ad.entity.condition.AdUnitKeyword;
import com.glw.ad.entity.condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : glw
 * @date : 2020/3/16
 * @time : 0:28
 * @Description : 导出服务业务层
 */
@Slf4j
@Service
public class DumpDataService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitRepository unitRepository;

    @Autowired
    private AdCreativeRepository adCreativeRepository;

    @Autowired
    private CreativeUnitRepository creativeUnitRepository;

    @Autowired
    private AdUnitDistrictRepository districtRepository;

    @Autowired
    private AdUnitItRepository itRepository;

    @Autowired
    private AdUnitKeywordRepository keywordRepository;

    public void dumpAdTableData() {

        dumpAdPlanTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_PLAN));
        dumpAdUnitTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_UNIT));
        dumpAdCreativeTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_CREATIVE));
        dumpAdCreativeUnitTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_CREATIVE_UNIT));
        dumpAdUnitDistrictTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_UNIT_DISTRICT));
        dumpAdUnitItTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_UNIT_IT));
        dumpAdUnitKeywordTable(String.format("%s%s", Constant.DATA_ROOT_DIR, Constant.AD_UNIT_KEYWORD));
    }

    private void dumpAdPlanTable(String fileName) {

        List<AdPlan> adPlans = planRepository.findAllByPlanStatus(CommonStatusEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlans)) {
            return;
        }

        List<AdPlanTable> planTables = new ArrayList<>();
        adPlans.forEach(p -> planTables.add(
                new AdPlanTable(
                        p.getId(),
                        p.getUserId(),
                        p.getPlanStatus(),
                        p.getStartDate(),
                        p.getEndDate()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdPlanTable planTable : planTables) {
                writer.write(JSON.toJSONString(planTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdPlanTable error");
        }
    }

    private void dumpAdUnitTable(String fileName) {

        List<AdUnit> adUnits = unitRepository.findAllByUnitStatus(
                CommonStatusEnum.VALID.getStatus()
        );
        if (CollectionUtils.isEmpty(adUnits)) {
            return;
        }

        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(u -> unitTables.add(
                new AdUnitTable(
                        u.getId(),
                        u.getUnitStatus(),
                        u.getPositionType(),
                        u.getPlanId()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitTable unitTable : unitTables) {
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitTable error");
        }
    }

    private void dumpAdCreativeTable(String fileName) {

        List<AdCreative> creatives = adCreativeRepository.findAll();
        if (CollectionUtils.isEmpty(creatives)) {
            return;
        }

        List<AdCreativeTable> creativeTables = new ArrayList<>();
        creatives.forEach(c -> creativeTables.add(
                new AdCreativeTable(
                        c.getId(),
                        c.getName(),
                        c.getType(),
                        c.getMaterialType(),
                        c.getHeight(),
                        c.getWidth(),
                        c.getAuditStatus(),
                        c.getUrl()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeTable creativeTable : creativeTables) {
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdCreativeTable error");
        }
    }

    private void dumpAdCreativeUnitTable(String fileName) {

        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(creativeUnits)) {
            return;
        }

        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(c -> creativeUnitTables.add(
                new AdCreativeUnitTable(
                        c.getCreativeId(),
                        c.getUnitId()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeUnitTable creativeUnitTable : creativeUnitTables) {
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdCreativeUnit error");
        }
    }

    private void dumpAdUnitDistrictTable(String fileName) {

        List<AdUnitDistrict> unitDistricts = districtRepository.findAll();
        if (CollectionUtils.isEmpty(unitDistricts)) {
            return;
        }

        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(d -> unitDistrictTables.add(
                new AdUnitDistrictTable(
                        d.getUnitId(),
                        d.getProvince(),
                        d.getCity()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitDistrictTable unitDistrictTable : unitDistrictTables) {
                writer.write(JSON.toJSONString(unitDistrictTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitDistrictTable error");
        }
    }

    private void dumpAdUnitItTable(String fileName) {

        List<AdUnitIt> unitIts = itRepository.findAll();
        if (CollectionUtils.isEmpty(unitIts)) {
            return;
        }

        List<AdUnitItTable> unitItTables = new ArrayList<>();
        unitIts.forEach(i -> unitItTables.add(
                new AdUnitItTable(
                        i.getUnitId(),
                        i.getItTag()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitItTable unitItTable : unitItTables) {
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitItTable error");
        }
    }

    private void dumpAdUnitKeywordTable(String fileName) {

        List<AdUnitKeyword> unitKeywords = keywordRepository.findAll();
        if (CollectionUtils.isEmpty(unitKeywords)) {
            return;
        }

        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();
        unitKeywords.forEach(k -> unitKeywordTables.add(
                new AdUnitKeywordTable(
                        k.getUnitId(),
                        k.getKeyword()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitKeywordTable unitKeywordTable : unitKeywordTables) {
                writer.write(JSON.toJSONString(unitKeywordTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitItTable error");
        }
    }
}
