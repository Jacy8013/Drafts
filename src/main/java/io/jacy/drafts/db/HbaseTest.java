package io.jacy.drafts.db;

import com.sun.beans.editors.ByteEditor;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Jacy
 */
public class HbaseTest {
    private Configuration configuration;
    private Connection connection;
    private Admin admin;

    @Before
    public void init() throws IOException {
        // 连接配置
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "zookeeper2");

        // 工厂类创建连接
        connection = ConnectionFactory.createConnection(configuration);



        // 操作api
        admin = connection.getAdmin();
    }

    @Test
    public void get() throws IOException {
        Table v2 = connection.getTable(TableName.valueOf("user_profile_v2"));
        Get get = new Get(Bytes.toBytes(StringUtils.reverse("244380")));
        get.addFamily(Bytes.toBytes("info"));

        Result result = v2.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println(Bytes.toString(cell.getQualifierArray()));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    @After
    public void destroy() {
        try {
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
