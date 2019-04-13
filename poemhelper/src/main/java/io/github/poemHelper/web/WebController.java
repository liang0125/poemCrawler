package io.github.poemHelper.web;

import com.google.gson.Gson;
import io.github.poemHelper.analyze.entity.model.Result;
import io.github.poemHelper.analyze.entity.model.WordCount;
import io.github.poemHelper.analyze.entity.service.AnalyzeService;
import io.github.poemHelper.config.ObjectFactory;
import io.github.poemHelper.crawler.Crawler;
import spark.ResponseTransformer;
import spark.Spark;

import java.util.List;

/**
 * Author:lorrie
 * Create:2019/3/23
 */
public class WebController {
    private final AnalyzeService analyzeService;

    public WebController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }
    public List<Result> analyzeResult(){
        return analyzeService.analyzeResult();
    }

    public List<WordCount> analyzeWordCount(){
        return analyzeService.analyzeWordCloud();
    }
    public  void launch(){
        ResponseTransformer transformer=new JSONResponseTransformer();
        Spark.staticFileLocation("/static");
        Spark.get("/analyze/author_count", ((request, response) -> analyzeResult()),transformer);
        Spark.get("/analyze/word_cloud",((request, response) -> analyzeWordCount()),transformer);
        Spark.get("/crawler/stop",((request, response) -> {
            Crawler crawler=ObjectFactory.getInstance().getObject(Crawler.class);
            crawler.stop();
            return "停止";
        }));
    }
    public static class JSONResponseTransformer implements ResponseTransformer{
        private static Gson gson=new Gson();
        @Override
        public String render(Object o) throws Exception {
            return gson.toJson(o);
        }
    }
}
