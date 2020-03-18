package com.glw.ad.mysql.dto;

import com.glw.ad.mysql.constant.OpTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : glw
 * @date : 2020/3/18
 * @time : 23:32
 * @Description : mysql binlog记录行的实体对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySqlRowData {

    private String tableName;

    private String level;

    private OpTypeEnum opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
