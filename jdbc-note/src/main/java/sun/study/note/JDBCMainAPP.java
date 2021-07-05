package sun.study.note;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */

public class JDBCMainAPP {

    private static final String URL = "jdbc:mysql://localhost:3306/demo?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        test1();
    }

    private static void register1() throws Exception {
        Class.forName(DRIVER_CLASS_NAME);
    }

    private static void register2() throws Exception {
        Class.forName("java.sql.DriverManager");
    }

    private static void register3() throws Exception {
        DriverManager.registerDriver(new Driver());
    }

    private static void register4() throws Exception {
        DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void test1() throws Exception {

        register3();
        // DriverManager.getConnection(URL, USERNAME, PASSWORD);
        // 加载驱动程序
        // Class.forName(DRIVER_CLASS_NAME);
        //Driver driver = new com.mysql.cj.jdbc.Driver();
        // 获取数据库连接
       // Class.forName("java.sql.DriverManager");
        // Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       /*  System.out.println(connection.getClass().getName());
        // 操作数据库，执行sql
        Statement statement = connection.createStatement();
        String sql = "select * from ts_student";
        ResultSet resultSet = statement.executeQuery(sql);
        // 遍历结果集
        while (resultSet.next()) {
            System.out.println(String.format("| %4d | %-10s | %3d | %-20s |",
                    resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getInt("age"), resultSet.getString("email")));
        }
        // 关闭连接
        statement.close();
        resultSet.close();
        connection.close();*/
    }
}
