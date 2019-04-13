package io.github.poemHelper.crawler.pipeline;

import io.github.poemHelper.crawler.common.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:lorrie
 * Create:2019/3/17
 */
/*
控制台，只负责打印
 */
public class ConsolePipeline implements Pipeline {
    @Override
    public void pipeline(final Page page) {
        Map<String,Object> data=page.getDataSet().getData();
        System.out.println(data);
    }
}
