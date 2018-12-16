# 课程管理系统（基于 springboot2.0 简化版系统实现）
在easyspringboot项目上进行改良
这个项目用到的技术有:

* SpringBoot2.0
* 通用Mapper和Mybatis
* Thymeleaf
* Mysql
* Gradle
* log4j2和Druid连接池
* Gradle

## 特点

* 简单易用
* RESTful API
* ...


## 版本
springboot2.0
tomcat9
mysql8
IDE:eclipse photon

使用：
项目导入ide，数据库用户名及密码在yml中，数据库文件temp.sql

功能：
1.用户模块:
拦截器
登录与注册
用户中心可以修改部分信息，上传头像

2.课程信息：
可以创建新课程，修改课程，删除课程，上传教材图片，评论课程
根据课程评论和课程类型查看统计图

3.课程类型信息：
可以创建修改删除课程类型信息

4.游戏模块
简单的打地鼠游戏，实现前五名最高分排名

5.留言板
带表情留言，但性能似乎有问题，打开留言板时间较长

6.管理员功能
管理角色，用户，以及所有其他信息

添加了xss过滤器，优化了界面。


