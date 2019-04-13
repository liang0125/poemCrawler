package io.github.poemHelper;

import com.alibaba.druid.pool.DruidDataSource;
import io.github.poemHelper.config.ConfigProperties;
import io.github.poemHelper.config.ObjectFactory;
import io.github.poemHelper.crawler.Crawler;
import io.github.poemHelper.crawler.common.Page;
import io.github.poemHelper.crawler.parse.DataPageParse;
import io.github.poemHelper.crawler.parse.DocumentParse;
import io.github.poemHelper.crawler.pipeline.ConsolePipeline;
import io.github.poemHelper.crawler.pipeline.DatabasePipeline;
import io.github.poemHelper.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 分析程序的主类
 *
 * Author:lorrie
 * Create:2019/3/16
 */
public class AnalyzeApplication {
    private static final Logger LOGGER=LoggerFactory.getLogger(AnalyzeApplication.class);
    public static void main(String[] args) {
        WebController webController=ObjectFactory.getInstance().getObject(WebController.class);
        LOGGER.info("Web service  running...");
        webController.launch();

        Crawler crawler=ObjectFactory.getInstance().getObject(Crawler.class);
        LOGGER.info("Crawler started...");
        crawler.start();



    }

}
