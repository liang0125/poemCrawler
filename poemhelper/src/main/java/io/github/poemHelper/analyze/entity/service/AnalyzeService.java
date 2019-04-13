package io.github.poemHelper.analyze.entity.service;

import io.github.poemHelper.analyze.entity.model.Result;
import io.github.poemHelper.analyze.entity.model.WordCount;

import java.util.List;

/**
 * Author:lorrie
 * Create:2019/4/6
 */
public interface AnalyzeService {
    /*
    分析唐诗中作者的创作数量
     */
    List<Result> analyzeResult();

    /*
    词云分析
     */
    List<WordCount> analyzeWordCloud();
}
