package io.github.poemHelper.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.github.poemHelper.analyze.dao.AnalyzeDao;
import io.github.poemHelper.analyze.dao.imp.AnalyzeDaoImpl;
import io.github.poemHelper.analyze.entity.service.AnalyzeService;
import io.github.poemHelper.analyze.entity.service.imp.AnalyzeServiceImpl;
import io.github.poemHelper.crawler.Crawler;
import io.github.poemHelper.crawler.common.Page;
import io.github.poemHelper.crawler.parse.DataPageParse;
import io.github.poemHelper.crawler.parse.DocumentParse;
import io.github.poemHelper.crawler.pipeline.ConsolePipeline;
import io.github.poemHelper.crawler.pipeline.DatabasePipeline;
import io.github.poemHelper.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:lorrie
 * Create:2019/4/10
 */
public final class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();

    private final Logger logger = LoggerFactory.getLogger(ObjectFactory.class);

    /**
     * 存放所有的对象
     */
    private final Map<Class, Object> objectHashMap = new HashMap<>();

    private ObjectFactory() {
        //1.初始化配置对象
        initConfigProperties();

        //2.数据源对象
        initDataSource();

        //3.爬虫对象
        initCrawler();

        //4.Web对象
        initWebController();

        //5.对象清单打印输出
       // printObjectList();
    }

    private void initWebController() {
        DataSource dataSource = getObject(DataSource.class);
        AnalyzeDao analyzeDao = new AnalyzeDaoImpl(dataSource);
        AnalyzeService analyzeService = new AnalyzeServiceImpl(analyzeDao);
        WebController webController = new WebController(analyzeService);
        objectHashMap.put(WebController.class, webController);
    }

    private void initCrawler() {
        ConfigProperties configProperties = getObject(ConfigProperties.class);
        DataSource dataSource = getObject(DataSource.class);
        final Page page = new Page(
                configProperties.getCrawlerBase(),
                configProperties.getCrawlerPath(),
                configProperties.isCrawlerDetail()
        );
        Crawler crawler = new Crawler();
        crawler.addParse(new DocumentParse());
        crawler.addParse(new DataPageParse());
        if (configProperties.isEnableConsole()) {
            crawler.addPipeline(new ConsolePipeline());
        }
        crawler.addPipeline(new DatabasePipeline(dataSource));
        crawler.addPage(page);

        objectHashMap.put(Crawler.class, crawler);
    }

    private void initDataSource() {
        ConfigProperties configProperties = getObject(ConfigProperties.class);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(configProperties.getDbUsername());
        dataSource.setPassword(configProperties.getDbPassword());
        dataSource.setDriverClassName(configProperties.getDbDriverClass());
        dataSource.setUrl(configProperties.getDbUrl());
        AnalyzeDao clearDb=new AnalyzeDaoImpl(dataSource);
        clearDb.clear();
        objectHashMap.put(DataSource.class, dataSource);
    }

    private void initConfigProperties() {
        ConfigProperties configProperties = new ConfigProperties();
        objectHashMap.put(ConfigProperties.class, configProperties);

        logger.info("ConfigProperties info:\n{}", configProperties.toString());

    }


    public <T> T getObject(Class classz) {
        if (!objectHashMap.containsKey(classz)) {
            throw new IllegalArgumentException("Class " + classz.getName() + " not found Object");
        }
        return (T) objectHashMap.get(classz);
    }

    public static ObjectFactory getInstance() {
        return instance;
    }


    private void printObjectList() {
        logger.info("====== ObjectFactory List =====");
        for (Map.Entry<Class, Object> entry : objectHashMap.entrySet()) {
            logger.info(String.format("[%-5s] ==> [%s]", entry.getKey().getCanonicalName(),
                    entry.getValue().getClass().getCanonicalName()));
        }
        logger.info("================================");
    }
}
