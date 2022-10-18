package sun.study.note.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;


import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @date 2022/9/20
 */
public class CodeGen {

    private static String url = "jdbc:mysql://localhost:3306/mybatis_demo?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=Asia/Shanghai";
    private static String username = "root";
    private static String password = "123456";
    private static String auth = "sunzhen03 <sunzhen03@inspur.com>";
    private static String outputDir = "D://code-gen//" + Objects.toString(System.currentTimeMillis());

    public static void main(String[] args) {

        // 需要构建一个 代码自动生成器 对象
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(auth)
                            .enableSwagger()
                            .fileOverride()
                            .outputDir(outputDir)
                            .dateType(DateType.ONLY_DATE)
                            .commentDate("yyyy-MM-dd");
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("sun.study.note")
                            .entity("model")
                            .mapper("dao")
                            .xml("dao");
                           // .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputDir));
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(scanner("表名，多个英文逗号分割").split(","))
                            .addTablePrefix("cfg_", "sys_", "msg_", "ams_","t_") // 增加过滤表前缀
                            .entityBuilder() /** entity 配置 */
                            .enableLombok() // 开启 lombok 模型
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            .idType(IdType.ASSIGN_ID) // id自动生成
                            .addSuperEntityColumns("created_user", "created_time", "updated_user", "updated_time") // 添加公共字段
                            .addTableFills(new Column("create_time", FieldFill.INSERT)) // 添加表字段填充
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE)) // 添加表字段填充
                            .build()
                            .serviceBuilder() /** service配置*/
                            .formatServiceFileName("%sService") // 配置Service名称
                            .build()
                            .controllerBuilder() /** controller 配置 */
                            .enableRestStyle() // 开启生成@RestController 控制器
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .build();
                })
                //.templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
