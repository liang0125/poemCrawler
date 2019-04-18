## 项目名称
    唐诗分析
## 项目描述
    本项目主要是对网页https://so.gushiwen.org/gushi/tangshi.aspx的所有唐诗进行分析提取的	过程，先从网页中提取出来需要的内容，比如标题,作者，朝代，以及正文内容，并对提取出来的内容进行解	析，解析的过程包括带有链接的内容以及无链接的内容，将所有解析出来的内容存储于数据库，并对内容进	行进一步的分析，最终以网页的形式呈现出来的是作者以及作品的数量，还有唐诗中出现词语从多到少的	页面即以词云的形式呈现
## 涉及技术
+ Stream流式处理
+ 文本分词和解析（ ansj ）
+ 多线程
+ 网页解析工具（ htmlunit ）
+ 数据库和JDBC编程
+ 数据可视化（ HTML/CSS/JavaScript , echarts , jQuery ）
+ Servlet/Sparkjava嵌入式Web容器/java-httpd(比特项目)快速构建web应用
+ DIY(Do It Yourself)构建对象管理工程


## 实现功能
+ 数据采集
+ 清洗
+ 存储数据分析
+ 可视化展示

## 具体实现

![设计图](images\design.png)

## 项目效果
![作者及作品数量](images\author_count.png)
![词云](images\word_cloud.png)


## 项目意义
+ 了解数据分析的基本流程
+ 熟悉数据分析方法
+ 锻炼应用技术解决问题的能力
