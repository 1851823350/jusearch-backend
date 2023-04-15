# 巨能搜聚合搜索平台后端

## 项目背景

基于Vue3 + Spring Boot + Elastic Stack的一-站式聚合搜索平台，也是简化版的企业级搜索中台。

对用户来说，使用该平台,可以在同-一个页面集中搜索出不同来源、不同类型的内容,提升用户的检索效率和搜索体验。对企业来说，当企业内部有多个项目的数据都存在搜索需求时，无需针对每个项目单独开发搜索功能，可以直接将各项目的数据源接入搜索中台,从而提升开发效率、降低系统维护成本。

聚合搜索页面-搜文章:

![d6e3ad0cc8f545a2a9d5db64dc6b2d61](file:///C:/Users/blablablala/Pictures/Typedown/d6e3ad0c-c8f5-45a2-a9d5-db64dc6b2d61.png?msec=1681536792153)

聚合搜索—搜图片

![5c3dc8418ca741a08b4db461933e3bc4](file:///C:/Users/blablablala/Pictures/Typedown/5c3dc841-8ca7-41a0-8b4d-b461933e3bc4.png?msec=1681536792161)

聚合搜索-搜用户

![a162cf6103114a75b8cc8d6d364750d4](file:///C:/Users/blablablala/Pictures/Typedown/a162cf61-0311-4a75-b8cc-8d6d364750d4.png?msec=1681536792154)

## 项目架构

![35606e7a7aa841848381156e986dae32](file:///C:/Users/blablablala/Pictures/Typedown/35606e7a-7aa8-4184-8381-156e986dae32.png?msec=1681536792155)

## 技术选型

### 后端

* Spring Boot 2.7.x（贼新）

* Spring MVC

* 数据抓取 （jsoup、HttpClient爬虫）

    * 离线

    * 实时

* 设计模式

    * 门面模式

    * 注册器模式

    * 适配器模式

* 数据同步

    * 定时

    * 双写

    * Logstash

    * Canal


### 数据存储

* MySQL 数据库
* Elastic Stack
    * ElasticSearch 搜索引擎（重点）
    * Logstash 数据管道
    * Kibana 数据可视化

### 前端

* Vue 3

* Ant Design Vue组件库

* 页面同步


## 启动

1. 启动Elastic Stack服务（**都要进入相应服务的bin目录下**）

   1. **ElasticSearch服务**
   elasticsearch.bat
   2. **Logstash**
   logstash.bat -f ..\config\mytask.conf
   3. **Canal**
   startup.bat
   4. **Kibana 数据可视化**
   Kibana.bat

2. 启动后端服务

3. 启动前端


## 快速上手

> 所有需要修改的地方都标记了 `todo`，便于大家找到修改的位置~

### MySQL 数据库

1）修改 `application.yml` 的数据库配置为你自己的：

    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/mysearch_db
        username: root
        password: 123456

2）执行 `sql/create_table.sql` 中的数据库语句，自动创建库表

3）启动项目，访问 `http://localhost:8101/api/doc.html` 即可打开接口文档，不需要写前端就能在线调试接口了~

### Elasticsearch 搜索引擎

1）修改 `application.yml` 的 Elasticsearch 配置为你自己的：

    spring:
      elasticsearch:
        uris: http://localhost:9200
        username: root
        password: 123456

2）复制 `sql/post_es_mapping.json` 文件中的内容，通过调用 Elasticsearch 的接口或者 Kibana Dev Tools 来创建索引（相当于数据库建表）

    PUT post_v1
    {
     参数见 sql/post_es_mapping.json 文件
    }

这步不会操作的话需要补充下 Elasticsearch 的知识，或者自行百度一下~

3）开启同步任务，将数据库的帖子同步到 Elasticsearch

找到 job 目录下的 `FullSyncPostToEs` 和 `IncSyncPostToEs` 文件，取消掉 `@Component` 注解的注释，再次执行程序即可触发同步：

    // todo 取消注释开启任务
    //@Component