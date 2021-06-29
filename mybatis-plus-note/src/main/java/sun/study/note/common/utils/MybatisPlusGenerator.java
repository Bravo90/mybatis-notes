package sun.study.note.common.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */
public class MybatisPlusGenerator {

    /**
     * RUN THIS
     * 参考配置
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String projectPath = System.getProperty("user.dir");
        mpg.setGlobalConfig(new GlobalConfig()
                .setOutputDir(projectPath + "/gene/src/main/java")
                .setOpen(false)
                .setAuthor("sunzhen03<sunzhen03@kuaishou.com>")
                .setSwagger2(true)
                .setBaseResultMap(true)
                .setIdType(IdType.AUTO)
                .setServiceName("%sService")
        );

        // 数据源配置
        mpg.setDataSource(new DataSourceConfig()
                .setUrl("jdbc:mysql://127.0.0.1:3306/demo")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456")
        );

        // 包配置
        mpg.setPackageInfo(new PackageConfig()
                        .setParent("sun.study.note.spring")
                        .setMapper("mapper")
                        .setEntity("model")
//                .setController("controller")
//                .setService("service")
//                .setServiceImpl("serviceImpl")

        );

        // 策略配置
        mpg.setStrategy(new StrategyConfig()//
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setInclude(scanner("表名"))
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix("ts_")
        );

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {

            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/gene/src/main/resources/dao/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + ": ");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
