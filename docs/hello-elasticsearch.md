# hello-elasticsearch

入门学习 ElasticSearch
开发环境：IntelliJ IDEA 2018.2 + JDK 8 + ElasticSearch 5.6.10
参考视频：[《ElasticSearch入门》](https://www.imooc.com/learn/889)

## 安装

到[官网](https://www.elastic.co/cn/products/elasticsearch)下载 ElasticSearch 并解压，然后运行 bin/elasticsearch (or bin\elasticsearch.bat on Windows)，在浏览器打开 `http://localhost:9200/` 查看是否运行。
![elasticsearch](images/elasticsearch.png)

## 可视化插件

[mobz/elasticsearch-head](https://github.com/mobz/elasticsearch-head)

根据 README 的安装步骤；

1. git clone git://github.com/mobz/elasticsearch-head.git
2. cd elasticsearch-head
3. npm install
4. npm run start
5. open `http://localhost:9100/`

*未连接 ElasticSearch 时：*
![elasticsearch-head-1](images/elasticsearch-head-1.png)
*已连接 ElasticSearch 时：*
![elasticsearch-head-2](images/elasticsearch-head-2.png)

## 与 SpringBoot 集成

通过 Gradle 构建，添加 ElasticSearch 依赖项时，同时需要引进相关插件 transport-netty3-client 依赖项。