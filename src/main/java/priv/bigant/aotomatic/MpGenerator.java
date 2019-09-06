package priv.bigant.aotomatic;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.*;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {

    private static String projectPath = "/Users/admin/Desktop/framework/automatic/";


    public static boolean controller = true;
    public static boolean service = true;
    public static boolean serviceImpl = true;
    public static boolean dao = true;
    public static boolean xml = true;
    public static boolean bean = true;

    /**
     * 是否为API类型   不是则为页面类型
     */
    private static boolean isApi = true;
    /**
     * 逻辑删除字段
     */
    private static String logicDeleteFieldName = "delete_status";

    /**
     * responseInfo 路径
     */
    private static String responseInfo = "priv.bigant.aotomatic.common.ResponseInfo";
    private static String responseName = "ResponseInfo";
    private static String responseParamName = "responseInfo";


    /**
     * queryParam 路径
     */
    private static String query = "priv.bigant.aotomatic.common.QueryParam";
    private static String queryName = "QueryParam";
    private static String queryParamName = "queryParam";


    private static boolean timeBetween = true;
    private static String timeBetweenLambdaColumn = "getCreatedTime";

    private static boolean orderBy = true;
    private static boolean orderIsAsc = false;
    private static String orderByLambdaColumn = "getCreatedTime";

    /**
     * 生成类的继承类
     */
    private static String baseController = "priv.bigant.aotomatic.common.BaseController";
    private static String baseService = "priv.bigant.aotomatic.common.BaseService";
    private static String baseServiceImpl = "priv.bigant.aotomatic.common.BaseServiceImpl";
    private static String baseEntity = "priv.bigant.aotomatic.common.BaseBean";
    private static String baseDao = "";

    private static String modelPath = "";
    /**
     * 包路径
     */
    private static String parentPackage = "priv.bigant";
    private static String defaultModule = "autoCode";
    private static String controllerPackage = "controller";
    private static String servicePackage = "service";
    private static String serviceImplPackage = "serviceImpl";
    private static String mapperPackage = "dao";
    private static String entityPackage = "bean";
    private static String xmlPackage = "mapper";


    private static String templatePath = "/javaTemplate/";

    private static String beanJavaPath = "src/main/java/";
    private static String serviceJavaPath = "src/main/java/";
    private static String serviceImplJavaPath = "src/main/java/";
    private static String mapperJavaPath = "src/main/java/";
    private static String xmlJavaPath = "src/main/resources/";
    private static String webJavaPath = "src/main/java/";


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip, String defVal) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入 " + tip + "(d 默认)");
        while (scanner.hasNext()) {
            String ipt = scanner.next();

            if ("d".equals(ipt)) {
                if (StringUtils.isEmpty(defVal))
                    System.out.println("没有默认值请继续输出");
                else
                    return defVal;
            } else if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        return tip;
    }

    private static String scannerRequired(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入 " + tip + "(必填)");
        while (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt))
                return ipt;
        }
        return tip;
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入 " + tip + "(b 跳过)");
        while (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt))
                return ipt;
        }
        return tip;
    }

    /**
     * 配置数据库
     */
    public static DataSourceConfig getDataScource() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://www.bigant.ltd:3306/autocode?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Qq27689480");
        return dsc;
    }

    /**
     * 获取全局配置
     */
    public static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath);

        gc.setAuthor(scannerRequired("姓名"));

        gc.setOpen(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);

        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");

        return gc;
    }

    /**
     * 包配置
     */
    public static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        //defaultModule = scanner(" 模块名称 (默认" + defaultModule + ")", defaultModule);
        pc.setModuleName(defaultModule);
        pc.setParent(parentPackage);
        pc.setController(controllerPackage);
        pc.setMapper(mapperPackage);
        pc.setEntity(entityPackage);
        pc.setXml(xmlPackage);
        pc.setService(servicePackage);
        pc.setServiceImpl(serviceImplPackage);

        HashMap<String, String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.ENTITY_PATH, projectPath + beanJavaPath + (parentPackage + "." + defaultModule + "." + entityPackage).replaceAll("\\.", "/"));
        pathInfo.put(ConstVal.SERVICE_PATH, projectPath + serviceJavaPath + (parentPackage + "." + defaultModule + "." + servicePackage).replaceAll("\\.", "/"));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, projectPath + serviceImplJavaPath + (parentPackage + "." + defaultModule + "." + serviceImplPackage).replaceAll("\\.", "/"));
        pathInfo.put(ConstVal.MAPPER_PATH, projectPath + mapperJavaPath + (parentPackage + "." + defaultModule + "." + mapperPackage).replaceAll("\\.", "/"));
        pathInfo.put(ConstVal.XML_PATH, projectPath + xmlJavaPath + xmlPackage.replaceAll("\\.", "/"));
        pathInfo.put(ConstVal.CONTROLLER_PATH, projectPath + webJavaPath + (parentPackage + "." + defaultModule + "." + controllerPackage).replaceAll("\\.", "/"));
        pc.setPathInfo(pathInfo);

        return pc;
    }

    /**
     * 自定义配置
     */
    public static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("responseInfo", responseInfo);
                map.put("responseName", responseName);
                map.put("responseParamName", responseParamName);
                map.put("query", query);
                map.put("queryName", queryName);
                map.put("queryParamName", queryParamName);
                if (timeBetween) {
                    map.put("timeBetween", true);
                    map.put("timeBetweenLambdaColumn", timeBetweenLambdaColumn);
                }
                if (orderBy) {
                    map.put("orderBy", true);
                    map.put("orderIsAsc", orderIsAsc);
                    map.put("orderByLambdaColumn", orderByLambdaColumn);
                }
                setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
       /* focList.add(new FileOutConfig(templatePath + "update.html.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/html/" + defaultModule
                        + "/" + tableInfo.getEntityName() + "html.html";
            }
        });*/

        ic.setFileOutConfigList(focList);
        return ic;
    }

    /**
     * 模板配置
     */
    public static TemplateConfig getTemplateConfig() {
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        TemplateConfig tc = new TemplateConfig();

        if (controller)
            if (isApi)//接口类型模板
                tc.setController(templatePath + "controller.java");
            else//页面类型模板
                tc.setController(templatePath + "pageController.java");
        else
            tc.setController(null);

        if (service)
            tc.setService(templatePath + "service.java");
        else
            tc.setService(null);

        if (serviceImpl)
            tc.setServiceImpl(templatePath + "serviceImpl.java");
        else
            tc.setServiceImpl(null);

        if (!bean)
            tc.setEntity(null);

        if (!xml)
            tc.setXml(null);

        if (!dao)
            tc.setMapper(null);
        //tc.setEntity("/mybatis/entity.java");
        //tc.setMapper("/mybatis/mapper.java");
        //tc.setXml("/mybatis/mapper.xml");
        return tc;
    }

    /**
     * 策略配置
     */
    public static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        if (StringUtils.isNotEmpty(baseEntity))
            strategy.setSuperEntityClass(baseEntity);
        if (StringUtils.isNotEmpty(baseController))
            strategy.setSuperControllerClass(baseController);
        if (StringUtils.isNotEmpty(baseDao))
            strategy.setSuperMapperClass(baseDao);
        if (StringUtils.isNotEmpty(baseService))
            strategy.setSuperServiceClass(baseService);
        if (StringUtils.isNotEmpty(baseServiceImpl))
            strategy.setSuperServiceImplClass(baseServiceImpl);

        strategy.setInclude(scannerRequired("表名"));
        if (StringUtils.isNotEmpty(logicDeleteFieldName))
            strategy.setLogicDeleteFieldName(logicDeleteFieldName);


        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix("t_");
        //strategy.entityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(isApi);
        return strategy;
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        mpg.setGlobalConfig(getGlobalConfig());

        // 数据源配置
        mpg.setDataSource(getDataScource());

        // 包配置
        mpg.setPackageInfo(getPackageConfig());

        //自定义配置
        mpg.setCfg(getInjectionConfig());

        // 配置自定义输出模板
        mpg.setTemplate(getTemplateConfig());

        // 策略配置
        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new VelocityTemplateEngine());

        ConfigBuilder config = mpg.getConfig();

        mpg.execute();
    }

}
