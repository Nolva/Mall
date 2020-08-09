package com.nolva;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. 整合MybatisPlus
 *    1.1. 导入依赖
 *    <dependency>
 *        <groupId>com.baomidou</groupId>
 *        <artifactId>mybatis-plus-boot-starter</artifactId>
 *        <version>3.3.2</version>
 *    </dependency>
 *    1.2. 配置
 *       a) 配置数据源
 *         - 导入数据库的驱动
 *         - 在application.yml配置数据源相关信息
 *       b) 配置MybatisPlus
 *         - 使用@MapperScan(dao层)
 *         - 告诉MybatisPlus，sql映射文件位置
 */
@MapperScan("com.nolva.product.dao")
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
