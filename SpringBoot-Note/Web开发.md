# [SpringBoot Web开发]

1. 创建SpringBoot应用，选中我们需要的模块
2. SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来
3. 自己编写业务代码

## [web自动配置规则]

1. WebMvcAutoConfiguration
2. WebMvcProperties
3. ViewResolver自动配置
4. 静态资源自动映射
5. Formatter与Converter自动配置
6. HttpMessageConverter自动配置
7. 静态首页
8. favicon
9. 错误处理

## [SpringBoot对静态资源的映射规则]

`WebMvcAutoConfiguration`类的`addResourceHandlers`方法：（添加资源映射）

```java
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

                String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                if (!registry.hasMappingForPattern(staticPathPattern)) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

            }
        }
```

所有 `/webjars/**` ，都去 `classpath:/META-INF/resources/webjars/` 找资源

`webjars`：以jar包的方式引入静态资源；

[webjars官网](https://www.webjars.org/)

![1573815091111](Web开发.assets/1573815091111.png)

例如：添加jquery的webjars

```xml
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.4.1</version>
        </dependency>
```

![1573815506777](Web开发.assets/1573815506777.png)

访问地址对应就是：http://localhost:8080/webjars/jquery/3.4.1/jquery.js

## [非webjars，自己的静态资源怎么访问]

**资源配置类：**

```java
// 说明可以在配置文件中配置静态资源的相关参数，缓存时间等
@ConfigurationProperties(    
    prefix = "spring.resources",
    ignoreUnknownFields = false
)
public class ResourceProperties {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
    private String[] staticLocations;
    private boolean addMappings;
    private final ResourceProperties.Chain chain;
    private final ResourceProperties.Cache cache;

    public ResourceProperties() {
        this.staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
        this.addMappings = true;
        this.chain = new ResourceProperties.Chain();
        this.cache = new ResourceProperties.Cache();
    }
```

![1573817274649](Web开发.assets/1573817274649.png)

上图中添加的映射访问路径`staticPathPattern`值是`/**`，对应的资源文件夹就是上面配置类`ResourceProperties`中的`CLASSPATH_RESOURCE_LOCATIONS`数组中的文件夹：

| 数组中的值（静态资源文件夹）   | 在项目中的位置                         |
| ------------------------------ | -------------------------------------- |
| classpath:/META-INF/resources/ | src/main/resources/META-INF/resources/ |
| classpath:/resources/          | src/main/resources/resources/          |
| classpath:/static/             | src/main/resources/static/             |
| classpath:/public/             | src/main/resources/public/             |

<img src="Web开发.assets/v2-d19fc37be2bc50c058752bdc1ae392b0_720w.jpg" alt="img" style="zoom:33%;" />

`/**`访问当前项目的任何资源，（静态资源文件夹）

当找不到相关映射处理的时候，默认从上面的静态资源文件夹

localhost:8080/asserts/js/Chart.min.js ---> 去静态资源文件夹里面找Chart.min.js

## [欢迎页映射]

```java
// 配置欢迎页映射
    @Bean
    public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
        WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
        welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
        welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
        return welcomePageHandlerMapping;
    }
......
    
    private Optional<Resource> getWelcomePage() {
                String[] locations = WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations());
                return Arrays.stream(locations).map(this::getIndexHtml).filter(this::isReadable).findFirst();
            }

        private Resource getIndexHtml(String location) {
            return this.resourceLoader.getResource(location + "index.html");
        }

```

![1573819949494](Web开发.assets/1573819949494.png)

`location`就是静态资源路径，所以欢迎页的页面就是上面静态资源下的`index.html`，被`/**`映射，因此直接访问项目localhost:8080就是访问欢迎页



## [Spring Boot不同版本对Favicon的支持]

在早些版本中Spring Boot对Favicon进行了默认支持，并且通过如下配置进行关闭操作：

```javascript
spring.mvc.favicon.enabled=false ## 关闭
```

默认显示效果如下：

![image](Web开发.assets/favicon-1.jpg)

但在Spring Boot项目的issues中提出，如果提供默认的Favicon可能会导致网站信息泄露。如果用户不进行自定义的Favicon的设置，而Spring Boot项目会提供默认的上图图标，那么势必会导致泄露网站的开发框架。

因此，在Spring Boot2.2.x中，将默认的favicon.ico移除，同时也不再提供上述application.properties中的属性配置。更多详细信息可查看对应的issue：https://github.com/spring-projects/spring-boot/issues/17925。

## [SpringBoot2.x项目中添加Favicon]

既然在当前版本中Spring Boot不支持默认的Favicon，我们就来看看如何自定义网站的Favicon。

我们可以在static目录下创建一个images目录，里面存放自己的Favicon.ico图标。

![img](Web开发.assets/v2-f94a2b16cddf45ae68cc5db0cb7df5e0_720w.jpg)

##  [html页面中添加]

```text
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="icon" href="images/Favicon.ico" type="image/x-icon"/>
</head>
<body>
<h1>一一哥的登录页面...</h1>
</body>
</html>
```

##  [Thymeleaf页面中添加]

```text
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8"/>
<title>Hello Favicon</title>
<link rel="icon" th:href="@{/favicon.ico}" type="image/x-icon"/>
</head>
<body>
<h1>Hello!</h1>
</body>
</html>
```

## [重启项目测试]

我们重新访问页面，可以看到Favicon图标已经换成了我自己的图标。

![img](Web开发.assets/v2-562e5e178dbd8a40875d989344c09623_720w.jpg)

上述方式有一个弊端，那就是需要在每个展示的页面中都添加对应的代码。