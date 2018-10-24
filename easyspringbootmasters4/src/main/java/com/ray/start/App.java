package com.ray.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * App
 *启动类
 * @author ray
 * 
 *
 * ComponentScan(basePackages = "xiao.ze.demo")  这里指定你要扫描的包及其子包子类
 * MapperScan("xiao.ze.demo.mapper") 扫描：该包下相应的class,主要是MyBatis的持久化类，
 * 这里用的是mapper的扫描，不是mybatis自身扫描
 *
 */
@ComponentScan(basePackages = "com.ray")
@SpringBootApplication
@MapperScan("com.ray.mapper")
@EnableTransactionManagement
public class App extends SpringBootServletInitializer{
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(App.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
