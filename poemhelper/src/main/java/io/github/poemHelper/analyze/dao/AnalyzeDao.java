package io.github.poemHelper.analyze.dao;

import io.github.poemHelper.analyze.entity.PoetryInfo;
import io.github.poemHelper.analyze.entity.model.Result;

import java.util.List;

/**
 * Author:lorrie
 * Create:2019/4/6
 */
public interface AnalyzeDao {

    /*
    分析唐诗中作者的创作数量
     */
    List<Result> analyzeResult();

    /*
    查询所有的诗文，提供给业务层进行分析
     */
    List<PoetryInfo> queryAllPoetryInfo();

}
