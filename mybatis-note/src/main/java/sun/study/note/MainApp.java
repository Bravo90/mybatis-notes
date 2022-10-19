package sun.study.note;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.dao.UserMapper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @date 2022/10/18
 */
@SpringBootApplication
@MapperScan("sun.study.note.dao")
@RestController
public class MainApp {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @GetMapping("list")
    public void test(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        System.out.println(sqlSessionFactory);
        System.out.println(userMapper.list(name, age));
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "测试2");

        List<Map<String, Object>> list = sqlSessionFactory.openSession().selectList("select.list", map);
        System.out.println(list);
    }

    @PostConstruct
    private void test() {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        String sql = "<select>\n" + "select * from form_cfg \n" + "<where>\n" + "<if test=\"id!=null and id!=''\">\n" + "and form_cfg_id=#{id}\n" + "</if>\n" + "<if test=\"name != null and name != ''\">\n" + "and form_name = #{name}\n" + "</if>\n" + "</where>\n" + "</select>";
        XPathParser parser = new XPathParser(sql);
        XNode xNode = parser.evalNode("select");
        XMLScriptBuilder scriptBuilder = new XMLScriptBuilder(configuration, xNode);
        SqlSource sqlSource = scriptBuilder.parseScriptNode();


        // ParameterMap paramMap = new ParameterMap.Builder(configuration, "select.list", Map.class, new ArrayList<>()).build();
        // result解析
        List<ResultMap> resultMapList = new ArrayList<>();
        ResultMap resultMap = new ResultMap
                .Builder(configuration, "select.list", Map.class, new ArrayList<>())
                .build();
        resultMapList.add(resultMap);

        MappedStatement ms = new MappedStatement
                .Builder(configuration, "select.list", sqlSource, SqlCommandType.SELECT)
                .resultMaps(resultMapList)
                // .parameterMap(paramMap)
                .build();

        configuration.addMappedStatement(ms);
    }


    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
