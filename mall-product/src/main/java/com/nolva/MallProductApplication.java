package com.nolva;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1.整合Mybatis-Plus
 *     1)导入依赖
 *     <dependency>
 *        <groupId>com.baomidou</groupId>
 *        <artifactId>mybatis-plus-boot-starter</artifactId>
 *        <version>3.3.2</version>
 *     </dependency>
 *     2)配置
 *        1)配置数据源：
 *           a.导入数据库驱动 https://dev.mysql.com/doc/connector-j/8.0/en
 *           b.在application.yml配置数据源相关信息
 *        2)配置Mybatis-Plus:
 *           a.使用@MapperScan
 *           b.告诉Mybatis-Plus，sql映射文件位置
 *
 *  2.逻辑删除
 *    1)配置全局的逻辑删除规则(可省略)
 *    2)实体类字段上加上@TableLogic注解
 *
 */
@MapperScan("com.nolva.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
