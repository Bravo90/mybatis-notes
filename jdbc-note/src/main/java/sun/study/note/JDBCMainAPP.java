package sun.study.note;

import com.mysql.cj.jdbc.MysqlDataSource;

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
        // 加载驱动程序
        Class.forName(DRIVER_CLASS_NAME);
        // 获取数据库连接
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        // 关闭自动提交
        connection.setAutoCommit(false);
        // 操作数据库，执行sql
        String sql = "update ts_student set age = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 23);
        ps.setInt(2, 1);

        int result = ps.executeUpdate();
        System.out.println(result);

        if (true) {
            try {
                throw new RuntimeException("手动异常！");
            } catch (Exception e) {
                connection.rollback();
            }
        }
        connection.commit();


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

    public static void test1() throws Exception {
        // 加载驱动程序
        Class.forName(DRIVER_CLASS_NAME);
        // 获取数据库连接
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
        connection.close();
    }
}
