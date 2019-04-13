package io.github.poemHelper.config;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author:lorrie
 * Create:2019/3/17
 */
@Data
public class ConfigProperties {
    private String crawlerBase;
    private String crawlerPath;
    private boolean crawlerDetail;

    //数据库的相关配置
    private String dbUsername;
    private String dbPassword;
    private String dbUrl;
    private String dbDriverClass;
    private  boolean enableConsole=false;

    public ConfigProperties(){
        //从外部文件加载
        InputStream inputStream=ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties");
        Properties p=new Properties();
        try{
            p.load(inputStream);
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.crawlerBase= (String) p.get("crawler.base");
        this.crawlerPath= (String) p.get("crawler.path");
        this.crawlerDetail=Boolean.parseBoolean(String.valueOf(p.get("crawler.detail")));

        this.dbUsername=String.valueOf(p.get("db.username"));
        this.dbPassword=String.valueOf(p.get("db.password"));
        this.dbUrl=String.valueOf(p.get("db.url"));
        this.dbDriverClass=String.valueOf(p.get("db.driverClass"));
        this.enableConsole=Boolean.valueOf(p.getProperty("config.enable_console","false"));

    }

}
