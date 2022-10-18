package sun.study.note.app;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import sun.study.note.dao.FormCfgMapper;
import sun.study.note.dao.UserMapper;
import sun.study.note.model.FormCfg;

import javax.sql.DataSource;
import java.util.List;

/**
 * mybatis入口
 *
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @date 2022/10/18
 */
public class MybatisEntranceApp {
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
        // 驼峰命名映射
        configuration.setMapUnderscoreToCamelCase(true);
        // 注册mapper
        configuration.addMapper(FormCfgMapper.class);
        configuration.addMapper(UserMapper.class);
        /** SqlSessionFactory */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        FormCfgMapper mapper = sqlSession.getMapper(FormCfgMapper.class);


        List<FormCfg> list = mapper.list();
        System.out.println(list);
    }
}
