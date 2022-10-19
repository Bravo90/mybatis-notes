package sun.study.note.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @date 2022/10/18
 */
public class XMLApp {
    private static String url = "jdbc:mysql://localhost:3306/mybatis_demo?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=Asia/Shanghai";
    private static String username = "root";
    private static String password = "123456";

    public static void main(String[] args) {
        /** 数据源 */
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        /** 事务 */
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        /** 环境 */
        Environment environment = new Environment("development", transactionFactory, dataSource);
        /** 配置 */
        Configuration configuration = new Configuration(environment);
        String sql = "<select>\n" +
                "select * from form_cfg \n" +
                "<where>\n" +
                "    <if test=\"name != null\">\n" +
                "         name = #{name}\n" +
                "    </if>\n" +
                "</where>\n" +
                "</select>";
        XPathParser parser = new XPathParser(sql);
        XNode xNode = parser.evalNode("select");


        XMLScriptBuilder scriptBuilder = new XMLScriptBuilder(configuration,xNode);
        SqlSource sqlSource = scriptBuilder.parseScriptNode();
        System.out.println(sqlSource);
    }
}
