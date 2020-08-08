# [模板引擎]

常见的模板引擎有`JSP`、`Velocity`、`Freemarker`、`Thymeleaf`

![template-engine](thymeleaf模板引擎.assets/template-engine.png)

SpringBoot推荐使用Thymeleaf；语法更简单，功能更强大

## [引入thymeleaf]

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

**如需切换thymeleaf版本：**

```xml
<properties>
        <thymeleaf.version>3.X.X.RELEASE</thymeleaf.version>
        <!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
        <!-- thymeleaf2   layout1-->
        <thymeleaf-layout-dialect.version>2.X.X</thymeleaf-layout-dialect.version>
  </properties>
```

## [Thymeleaf使用]

```java
package org.springframework.boot.autoconfigure.thymeleaf;
......
@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML";
```

默认只要我们把HTML页面放在`classpath:/templates/`，thymeleaf就能自动渲染；

1. 创建模板文件`t1.html`，并导入thymeleaf的名称空间

   ```html
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   ```

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   
   </body>
   </html>
   ```

2. 使用模板

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>[[${title}]]</title>
   </head>
   <body>
   <h1 th:text="${title}"></h1>
   <div th:text="${info}">这里的文本之后将会被覆盖</div>
   </body>
   </html>
   ```

3. 在controller中准备数据

   ```java
   @Controller
   public class HelloT {
   
       @RequestMapping("/ht")
       public String ht(Model model) {
           model.addAttribute("title","hello Thymeleaf")
                .addAttribute("info","this is first thymeleaf test");
           return "t1";
       }
   }
   ```

## [语法规则]

`th:text` --> 改变当前元素里面的文本内容；

`th：任意html属性` --> 来替换原生属性的值

![thymeleaf](thymeleaf.assets/2018-02-04_123955.png)

更多配置参考官方文档：https://www.thymeleaf.org/documentation.html

中文参考书册：https://www.lanzous.com/i7dzr2j

```properties
简单表达式的语法：
	变量表达式：${...}：后去变量值；OGNL
		1. 获取对象的属性、调用方法
		2. 使用内置的基本对象：
			#ctx：上下⽂对象。 
			#vars：上下⽂变量。 
			#locale：上下⽂区域设置。 
			#request:(仅在Web	Contexts中）HttpServletRequest对象。 
			#response：（仅在Web上下⽂中）HttpServletResponse对象。 
			#session:(仅在Web上下⽂中）HttpSession对象。 
			#servletContext:(仅在Web上下⽂中）ServletContext对象。
			
			eg:${session.foo}  // 获取session的'foo'属性
		3. 内置的一些工具对象：
		   #execInfo：有关正在处理的模板的信息。
            #messages：⽤于在变量表达式中获取外部化消息的⽅法，与使⽤＃{...}语法获得的⽅式相同。 
            #uris：转义URL/URI部分的⽅法 
            #conversions：执⾏配置的转换服务（如果有的话）的⽅法。 
            #dates：java.util.Date对象的⽅法：格式化，组件提取等 
            #calendars：类似于#dates，但对于java.util.Calendar对象。 
            #numbers：⽤于格式化数字对象的⽅法。 
            #strings：String对象的⽅法：contains，startsWith，prepending/appending等 
            #objects：⼀般对象的⽅法。 
            #bools：布尔评估的⽅法。 
            #arrays：数组的⽅法。 
            #lists：列表的⽅法。 
            #sets：集合的⽅法。 
            #maps：地图⽅法。
            #aggregates：在数组或集合上创建聚合的⽅法。 
            #ids：处理可能重复的id属性的⽅法（例如，作为迭代的结果
            
            eg:${#strings.toString(obj)}  // 使用String对象的方法

	选择变量表达式：*{...}  和${...}在功能上是一样的；
		补充：配合 th:object="${session.object}"
            eg:
                <div th:object="${session.user}" >
                <p>Name: <span th:text="*{firstName}">Sebastian</span> .</p>		
                <p>Surname:	<span th:text="*{lastName}">Pepper</span>. </p>			
                <p>Nationality:	<span th:text="*{nationality}">Saturn< /span>.</p>
                </div>
                        
	消息表达式：＃{...}：获取国际化内容
	链接⽹址表达式：@{...}：定义URL链接
		   eg: @{/order/process(execId=${execId},execType='FAST')}
	⽚段表达式：〜{...}：片段引用表达式
		   eg: <div th:insert="~{commons :: main}">...</div>
		   
⽂字：
    ⽂字⽂字：'one text', 'Another one!'	
    数字字⾯值：0,34,3.0,12.3，...
    布尔⽂字：true，false
    空字⾯值：null
    Token：one，sometext，main，...

⽂本操作：
    字符串连接：+
    ⽂本替换：|The name is ${name}|

算术运算符：
    运算符：+，-，\*，/，%

负号（⼀元运算符）：-

布尔运算符：
    运算符：and，or
    布尔否定（⼀元运算符）：！，not

⽐较和相等运算符：
	⽐较运算符：>;，<;，>=，<=（gt，lt，ge，le）
	相等运算符：==，！=（eq，ne）

条件运算符：
	If-then:(if) ? (then)
	If-then-else:(if) ? (then) : (else)
	Default:(value) ?: (defaultvalue)

特殊符号：
	无操作的符号：_
```

