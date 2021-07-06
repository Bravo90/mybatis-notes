package sun.study.note;

//import com.mysql.cj.jdbc.MysqlDataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-07-02
 */
public class DataSourceMainApp {
    private static final String URL = "jdbc:mysql://localhost:3306/demo?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        test2();
    }

    public static void test2() throws Exception {
       MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(URL);
        Connection conn = dataSource.getConnection(USERNAME, PASSWORD);

        String sql = "select * from ts_student where id > ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 3);
        ResultSet resultSet = ps.executeQuery();
        // 遍历结果集
        while (resultSet.next()) {
            System.out.println(String.format("| %4d | %-10s | %3d | %-20s |",
                    resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getInt("age"), resultSet.getString("email")));
        }
    }
}
