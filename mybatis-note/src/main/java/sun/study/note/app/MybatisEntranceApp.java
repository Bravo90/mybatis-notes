package sun.study.note.app;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.xml.XMLStatementBuilder;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.scripting.xmltags.*;
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
import java.util.*;

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
        Configuration configuration = getConfiguration();
        String sql = "<select id=\"test123\" resultType=\"map\">\n" + "select * from form_cfg \n" + "<where>\n" + "<if test=\"id!=null and id!=''\">\n" + "and form_cfg_id=#{id}\n" + "</if>\n" + "<if test=\"name != null and name != ''\">\n" + "and form_name = #{name}\n" + "</if>\n" + "</where>\n" + "</select>";
        XPathParser parser = new XPathParser(sql);
        XNode context = parser.evalNode("select");
        MapperBuilderAssistant assistant = new MapperBuilderAssistant(configuration, "");
        assistant.setCurrentNamespace("test");
        XMLStatementBuilder builder = new XMLStatementBuilder(
                configuration, assistant, context
        );
        builder.parseStatementNode();


        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "测试2");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        List<Map<String, Object>> list = sqlSessionFactory.openSession().selectList("test.test123", map);
        System.out.println(list);


      /*  // 驼峰命名映射
        configuration.setMapUnderscoreToCamelCase(true);
        // 注册mapper
        configuration.addMapper(FormCfgMapper.class);
        configuration.addMapper(UserMapper.class);
        *//** SqlSessionFactory *//*
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        selectByDesign(sqlSessionFactory);*/

    }


    private static Configuration getConfiguration() {
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
        return configuration;
    }

    private static void selectByDesign(SqlSessionFactory sqlSessionFactory) {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        String sql = "<select>\n" +
                "select * from form_cfg \n" +
                "<where>\n" +
                "<if test=\"id!=null and id!=''\">\n" +
                "and form_cfg_id=#{id}\n" +
                "</if>\n" +
                "<if test=\"name != null and name != ''\">\n" +
                "and form_name = #{name}\n" +
                "</if>\n" +
                "</where>\n" +
                "</select>";
        XPathParser parser = new XPathParser(sql);
        XNode xNode = parser.evalNode("select");
        XMLScriptBuilder scriptBuilder = new XMLScriptBuilder(configuration, xNode);
        SqlSource sqlSource = scriptBuilder.parseScriptNode();


        // ParameterMap paramMap = new ParameterMap.Builder(configuration, "select.list", Map.class, new ArrayList<>()).build();
        // result解析
        List<ResultMap> resultMapList = new ArrayList<>();
        ResultMap resultMap = new ResultMap.Builder(configuration, "select.list", Map.class, new ArrayList<>()).build();
        resultMapList.add(resultMap);

        MappedStatement ms = new MappedStatement.
                Builder(configuration, "select.list", sqlSource, SqlCommandType.SELECT)
                .resultMaps(resultMapList)
                //   .parameterMap(paramMap)
                .build();

        configuration.addMappedStatement(ms);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "测试2");
        List<Map<String, Object>> list = sqlSession.selectList("select.list", map);
        System.out.println(list);
    }

    private static void select(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FormCfgMapper mapper = sqlSession.getMapper(FormCfgMapper.class);
        List<FormCfg> list = mapper.list();
        System.out.println(list);
        sqlSession.close();
    }
}
