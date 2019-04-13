package io.github.poemHelper.crawler.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储清洗的数据
 *
 * Author:lorrie
 * Create:2019/3/17
 */
public class DataSet {
    /*
    data把DOM（文档对象模型）解析，清洗之后存储的数据
    比如：
    标题：送孟浩然之广陵
    作者：李白
    正文：XXX
     */
    private Map<String,Object> data=new HashMap<>();

    public void putData(String key,Object value){
        this.data.put(key,value);
    }

    public Object getData(String key){
        return this.data.get(key);
    }
    public Map<String, Object> getData() {
        return new HashMap<>(data);
    }
}
