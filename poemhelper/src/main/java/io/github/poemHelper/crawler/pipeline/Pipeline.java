package io.github.poemHelper.crawler.pipeline;

import io.github.poemHelper.crawler.common.Page;

/**
 * Author:lorrie
 * Create:2019/3/17
 */
public interface Pipeline {
    void pipeline(Page page);
}
