package com.glw.ad.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author : glw
 * @date : 2020/3/14
 * @time : 10:24
 * @Description : 通用工具类
 */
@Slf4j
public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }

    /**
     * 拼接字符串
     * @param args
     * @return
     */
    public static String stringConcat(String... args) {
        StringBuilder result = new StringBuilder();
        for (String arg : args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * 日期转换工具
     * 格式：Tue Jan 01 08:00:00 CST 2020
     * @param dateString
     * @return
     */
    public static Date parseStringDate(String dateString) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(
                    "EEE MM dd HH:mm:ss zzz yyyy",
                    Locale.US
            );
            return DateUtils.addHours(
                    dateFormat.parse(dateString),
                    -8
            );
        } catch (ParseException e) {
            log.error("parseStringDate error: {}", dateString);
            return null;
        }
    }
}
