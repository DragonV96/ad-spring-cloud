package com.glw.ad.index;

import com.alibaba.fastjson.JSON;
import com.glw.ad.dump.constants.Constant;
import com.glw.ad.dump.table.*;
import com.glw.ad.handler.AdLevelDataHandler;
import com.glw.ad.mysql.constant.OpTypeEnum;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : glw
 * @date : 2020/3/17
 * @time : 23:16
 * @Description : 加载binlog索引文件
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    /**
     * 按层级依赖顺序初始化索引
     */
    @PostConstruct
    public void init() {
        List<String> adPlanStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_PLAN)
        );
        adPlanStrings.forEach(p -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(p, AdPlanTable.class),
                OpTypeEnum.ADD
        ));

        List<String> adCreativeStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_CREATIVE)
        );
        adCreativeStrings.forEach(c -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(c, AdCreativeTable.class),
                OpTypeEnum.ADD
        ));

        List<String> adUnitStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_UNIT)
        );
        adUnitStrings.forEach(u -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(u, AdUnitTable.class),
                OpTypeEnum.ADD
        ));

        List<String> adCreativeUnitStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_CREATIVE_UNIT)
        );
        adCreativeUnitStrings.forEach(cu -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(cu, AdCreativeUnitTable.class),
                OpTypeEnum.ADD
        ));

        List<String> adUnitDistrictStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_UNIT_DISTRICT)
        );
        adUnitDistrictStrings.forEach(d -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(d, AdUnitDistrictTable.class),
                OpTypeEnum.ADD
        ));

        List<String> adUnitItStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_UNIT_IT)
        );
        adUnitItStrings.forEach(i -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(i, AdUnitItTable.class),
                OpTypeEnum.ADD
        ));

        List<String> adUnitKeywordStrings = loadDumpData(
                String.format("%s%s",
                        Constant.DATA_ROOT_DIR,
                        Constant.AD_UNIT_KEYWORD)
        );
        adUnitKeywordStrings.forEach(k -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(k, AdUnitKeywordTable.class),
                OpTypeEnum.ADD
        ));
    }

    /**
     * 加载导出索引数据文件
     * @param fileName
     * @return
     */
    private List<String> loadDumpData(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
