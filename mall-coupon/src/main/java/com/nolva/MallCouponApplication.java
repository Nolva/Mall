package com.nolva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @使用Nacos作为配置中心统一管理配置
 * 1. 引入依赖
 *    <dependency>
 *        <groupId>com.alibaba.cloud</groupId>
 *        <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *    </dependency>
 * 2. 创建bootstrap.properties并配置应用名和服务地址
 *    spring.application.name=mall-coupon
 *    spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 * 3. 需要在Nacos配置中心默认添加一个数据集(Data Id) mall-coupon.properties 默认规则：应用名.properties
 * 4. 给mall-coupon.properties添加配置
 * 5. 动态获取配置
 *   @RefreshScope:配置在需要引用配置值的类上，动态获取并刷新配置
 *   @Value("${配置名}"):获取配置名对应的值
 *   如何配置中心和当前本地项目配置了相同的配置名，默认优先使用配置中心的配置，方便能动态修改
 *
 * @细节
 * @1.命名空间(配置隔离)
 *   默认public(保留空间)：默认新增的所有配置都在public命名空间
 *   1.1 开发/测试/生产：利用命名空间来做环境隔离
 *      注意：在bootstrap.properties配置上，需要使用的命名空间配置
 *      spring.cloud.nacos.config.namespace=对应命名空间的UUID(9de62e44-cd2a-4a82-bf5c-95878bd5e871)
 *   1.2 每一个微服务之间相互隔离，每一个微服务都创建自己的命名空间，只加载自己命名空间下的所有配置
 *
 * @2.配置集(一个概念):所有配置的集合
 *
 * @3.配置集ID:类似配置文件名
 *   Data Id:(应用名.properties)
 *
 * @4.配置分组:默认所有配置集都属于DEFAULT_GROUP
 * spring.cloud.nacos.config.group=(DEFAULT_GROUP/1111/618/1212)
 *
 * 开发中的使用：每个微服务创建自己的命名空间，使用配置分组区分环境(dev/test/prod)
 *
 * @同时加载多个配置集
 * @1.微服务任何配置信息，任何配置文件都可以放在配置中心
 * @2.只需要在bootstrap.properties说明加载配置中心的哪些文件即可
 * @3.@Value,@ConfigurationProperties...
 * 以前配置中心SpringBoor任何方法从配置文件中获取值都能使用
 * 配置中心有默认使用配置中心的配置
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCouponApplication.class, args);
    }

}
