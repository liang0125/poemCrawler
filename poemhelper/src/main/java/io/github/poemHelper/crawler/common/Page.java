package io.github.poemHelper.crawler.common;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Author:lorrie
 * Create:2019/3/17
 */
@Data
public class Page {

    /*
    数据网站的根地址
     */
    private  final String base;

    /*
    具体网页的路径
     */
    private  final String path;

    /*
    网页的DOM对象
     */
    private  HtmlPage htmlPage;

    /*
    标识网页是否是详情页
     */
    private final boolean detail;

    /*
    子页面对象集合
     */
    private Set<Page> subPage=new HashSet<>();


    private DataSet dataSet=new DataSet();

    /*
    数据对象
     */
    public String getUrl(){
        return this.base+this.path;
    }
}
