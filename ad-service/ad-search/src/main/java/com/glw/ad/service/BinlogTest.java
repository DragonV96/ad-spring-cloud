package com.glw.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

/**
 * @author : glw
 * @date : 2020/3/18
 * @time : 23:21
 * @Description : binlog 测试类
 */
public class BinlogTest {

    public static void main(String[] args) throws Exception {

        BinaryLogClient client = new BinaryLogClient(
                "192.168.1.158",
                3306,
                "root",
                "root"
        );
//        client.setBinlogFilename("binlog.000037");
//        client.setBinlogPosition();

        client.registerEventListener(event -> {

            EventData data = event.getData();

            if (data instanceof UpdateRowsEventData) {
                System.out.println("Update--------------");
                System.out.println(data.toString());
            } else if (data instanceof WriteRowsEventData) {
                System.out.println("Write---------------");
                System.out.println(data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("Delete--------------");
                System.out.println(data.toString());
            }
        });

        client.connect();
    }
}
