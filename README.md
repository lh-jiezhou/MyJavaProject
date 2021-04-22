# 一、Spring Boot入门

## 1、Spring Boot简介

## 2、微服务

## 3、环境准备

##4、第一个Hello World程序

###1. 创建一个maven工程；（.jar）

###2. 导入spring boot相关的依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.1.RELEASE</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

</dependencies>
```

###3. 编写一个主程序，启动spring boot 应用

HelloWorldMainApplication.java

```java
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    // 快捷键 psvm
    public static void main(String[] args) {

        // Spring 应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);

    }
}
```

###4. 编写相关的Controller、Service

```java
@Controller
public class HelloController {

    @ResponseBody // return写回给浏览器
    @RequestMapping("/hello") // 接受来自浏览器的请求
    public String hello(){
        return "Hello World";
    }
}
```

###5. 运行主程序测试

###6. 简化部署

```xml
<build>
        <plugins>
            <!--这个插件，可以将应用打包成为一个可执行的jar包 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <executable>true</executable>
                </configuration>
            </plugin>

        </plugins>
    </build>
```

将这个应用打成jar包（idea侧边栏 -- Maven -- 项目名下 -- Lifecycle -- package 双击打包 -- 生成jar在项目 target文件下  ），直接使用java -jar的命令进行执行



## 5、Holle World探究

###1、POM文件

#### 1. 父项目

```xml
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>
 </parent>

它的父项目是
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.3.1.RELEASE</version>
</parent>
他来真正管理Spring Boot 应用里面的所有依赖版本
```

Spring Boot 的版本仲裁中心；

以后我们导入依赖默认是不需要写版本；（没有在dependencies里面管理的依赖自然需要声明版本号）

#### 2. 启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
不需要写版本号，因为Spring Boot自动仲裁
```

spring-boot-starter-**web**：

​	spring-boot-starter：spring-boot场景启动器；帮我们导入了web模块正常运行所依赖的组件

​	Spring Boot将所有的功能场景都抽取出来，做成一个个的starters(启动器)，只需要在项目里面引入这些starters，则相关场景的的所有依赖都会导入进来.要用什么功能就导入什么场景启动器

###2、主程序类、主入口类

```java
@SpringBootApplication
public class HelloWorldMainApplication {
    // 快捷键 psvm
    public static void main(String[] args) {
        // Spring 应用启动起来
 SpringApplication.run(HelloWorldMainApplication.class, args);

    }
}
```

@**SpringBootApplication：Spring** Boot应用标注在某个类上说明这个类是SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
```

@**SpringBootConfiguration**： Spring Boot 配置类；

​	标注在某个类上，表示这是一个Spring Boot的配置类；

​	@Configuration：配置类上来标明这个注解

​			配置类 --- 配置文件 ； 配置类也是容器中的一个组件；@Component

@**EnableAutoConfiguration**：开启自动配置功能；

```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
```

​		@**AutoConfigurationPackage**:自动配置包

​				@Import({Registrar.class})

​				Spring的底层注解@Import，给容器中导入一个组件 Registrar.class

**将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到spring容器中**

​				@Import({AutoConfigurationImportSelector.class})导入哪些组件的选择器；将所有需要导入的组件以全类名的方式返回；这些组件就会被添加到容器中

​				会给容器中导入非常多的自动配置类（xxxAutoConfiguration）给容器中导入这个场景需要的组件，并配置好这些组件

**Spring  Boot 在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作**

J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-2.3.1.RELEASE.jar包里



![image-20210420193356672](C:\Users\89785\AppData\Roaming\Typora\typora-user-images\image-20210420193356672.png)



## 6、使用Spring Initializer快速创建Spring Boot项目

IDE都支持使用spring的项目创建向导快速创建一个spring boot项目；

IDEA为例：新建项目选择Spring Initializer

默认生成的Spring Boot项目；

- 主程序已经生成好，我们只需要我们自己的逻辑
- resources文件夹中目录结构
  - static： 保存所有的静态资源；js\css\images
  - templates：保存所有的模板页面；（Spring Boot默认jar包使用嵌入式的Tomcat,默认不支持jsp页面）；可以使用模板引擎（freemarker\ thymeleaf）
  - application.properties: Spring Boot配置文件





# 二、配置文件

## 1、配置文件

Spring Boot 使用一个全局的配置文件，配置文件名是固定的；

- application.properties（优先级高与yml）
- application.yml (较新)

配置文件的作用：修改SpringBoot自动配置的默认值（SpringBoot在底层都给我们自动配置好的东西）

YAML（YAML Ain't Markup Language）

​	YAML  A Markup Language: 是标记语言

​	YAML isn't Markup Language: 不是标记语言

标记语言：

​	以前的配置文件，大多数都使用 xxx.xml文件；

​	YAML: 以数据为中心，比json,xml等更适合做配置文件

​	YAML: 配置实例	

```yaml
server:
  port: 8081
```

​	XML: 配置实例

```xml
<server>
	<port>8081</port>
</server>
```



## 2、YAML语法

### 1、基本语法

k:(空格)v  表示一对键值对(空格必须有)

以空格的缩进来控制层级关系；只要左对齐的一列数据，都是同一个层级的

属性和值也是大小写敏感



### 2、值的写法

#### 字面量：普通的值（数字，字符串，布尔）

​	k: v  : 字面直接来写

​			  字符串默认不用加上单引号或者双引号

​			  “”：双引号；不会转义字符里面的特殊字符；特殊字符会作为本身想表示的意思

​						name: "zhangsan \n lisi"  输出：zhangsan 换行 lisi

​			‘’：单引号；会转义字符里面的特殊字符；特殊字符最终只是一个普通的字符串数据

​						name: "zhangsan \n lisi"  输出：zhangsan \n lisi

#### 对象、Map （属性和值）（键值对）：

​	k: v  :  在下一行写对象的属性和值的关系；注意缩进

​		对象还是k: v的方式

```yaml
friend:
  lastName: zhangsan
  age: 20
```

​		行内写法：

```yaml
friend: {lastName: zhangsan, age: 18}
```



####数组（List、Set）

​	用 - 值 表示数组中的一个元素

```yaml
pets: 
  - cat
  - dog
  - pig
```

​		行内写法：

```yaml
pets: [cat,dog,pig]
```

### 3、配置文件值注入

配置文件：

```yaml
person:
  lastName: zhangsan
  age: 18
  boss: false
  birth: 2017/12/12
  maps: {k1: v1, k2: v2}
  lists:
    - lisi
    - zhaoliu
  dog:
    name: 小狗
    age: 2
```

javaBean:

```java
/**
 * 将配置文件(yaml)中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties: 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 * prefix = "person" 配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器的组件，才能使用容器提供的@ConfigurationProperties功能
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
```

可以导入配置文件处理器，以后编写配置就有提示了

```xml
<!--导入配置文件处理器,配置文件进行绑定就会有提示 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

**自动匹配生成 last-name 与 lastName 等价 （-n = N）**



#### 1）properties为 ASCII 码 但在idea中默认为 utf-8 可能乱码

```properties
# 配置端口
#server.port=8081

# idea使用的是 UTF-8 编码
# properties默认为 ASCII 码
# 在Setting =》File Encodeing 勾选 Transparent native-to-ascii conversion, 必要时删除properties文件再重建 可解决乱码
#配置 person 的值
person.last-name=张三
person.birth=2017/12/15
person.boss=false
person.maps.k1 = v1
person.maps.k2 = v2
person.lists=a, b, c
person.dog.name=dog
person.dog.age=15
```



#### 2）@Value 与 @ConfigurationProperties获取值比较

#### 3） 配置文件注入值数据校验

https://www.bilibili.com/video/BV1gW411W76m?p=13 配置文件注入与 @Value注解 进行验证（Validation）的区别

####4）@PropertySource 与 @ImportResource

**@PropertySource : 加载指定的配置文件；

```java
@PropertySource(value = {"classpath:person.properties"}) // classpath:后不加空格
@Component
@ConfigurationProperties(prefix = "person")
//@Validated // 启动校验 例如 校验邮箱@Email类型
public class Person {

    /**
     * 以前赋值
     * <bean class="Person">
     *      <property name="lastName" value="字面值/ ${key}从环境变量，配置文件中获取值/ ${SpEl}"></property>
     * </bean>
     */

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
```

**@ImportResource**: 导入Spring的配置文件，让配置文件里面的内容生效

Spring Boot里面没有Spring 的配置文件，我们自己编写的配置文件，也不能自动识别，想让Spring Boot 的配置文件生效，加载进来；将**@ImportResource**:标注在配置类上

```java
@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringBoot02ConfigApplication {
引入Spring的配置文件让其生效
```



不来编写配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloService" class="com.lh.springboot.service.HelloService">

    </bean>
</beans>
```

SpringBoot 推荐给容器中添加组件的方式：推荐使用全注解的方式

1、配置类（相当于Spring配置文件）

2、使用@Bean给容器中添加组件

```java
package com.lh.springboot.config;

/**
 * @Configuration: 指明当前类是一个配置类，代替之前的配置文件
 * 在配置文件中用 <bean></bean> 标签添加组件
 *
 */
@Configuration
public class MyAppConfig {

    // 将方法的返回值添加到容器中，容器中这个组件的默认id 就是方法名
    @Bean
    public HelloService helloService(){
        System.out.println("配置类@Bean给容器中添加了组件");
        return new HelloService();
    }
}
```

### 4、配置文件占位符

#### 1）随机数

```properties
${random.value},${random.int}, ${random.long},  ${random.int(10)}
```

####2) 占位符获取之前配置的值，如果没有可以是用: 指定默认值

```properties
person.last-name=张三${random.uuid} 
# 随机数
person.age=${random.int} 
person.birth=2017/12/15
person.boss=false
person.maps.k1 = v1
person.maps.k2 = v2
person.lists=a, b, c
# 占位符
#person.dog.name=${person.last-name}_dog
# 默认值 :
person.dog.name=${person.hello:hello}_dog 
person.dog.age=15
```

### 5、Profile文件 多环境支持

- 开发人员：开发环境 dev
- 项目发布后：生产环境 prob
- 测试期间：测试环境 

#### 1）多Profile文件

我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml

默认使用的application.properties的配置

#### 2）yml支持多文档块方式

```yml
# 配置端口
server:
  port: 8082
# 激活
spring:
  profiles:
    active: dev
---
# 文档块
server:
  port: 8083
spring:
  profiles: dev

---
server:
  port: 8084
spring:
  profiles: prod  # 指定属于哪个环境
```



#### 3）激活指定Profile

​	1、在配置文件(application.properties)中指定 spring.profiles.active=dev

​	2、命令行 

​			--spring.profils.active=dev

​			在Run/Debug Configurations中 Program arguments加上面那一行 

​			或者 java -jar spring-xxx.jar --spring.profils.active=dev

​	3、虚拟机参数

​			-Dspring.profils.active=dev

​			在Run/Debug Configurations中 VM options 加上面那一行 



### 6、配置文件加载位置

springboot启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件

-file:./config/  （项目文件夹下）

-file:./

-classpath:/config/  （类路径）
优先级由高到底，高优先级的配置会覆盖低优先级的配置；

SpringBoot会从这四个位置全部加载主配置文件;**互补配置;**


**我们还可以通过spring.config.location来改变默认的配置文件位置**

项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置;指定配置文件和默认加载的这些配置文件共同起作用形成互补配置；


<<<<<<< HEAD

###7、外部配置加载顺序

**SpringBoot 也可以从以下位置加载配置；优先级从高到低；高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置**

1、命令行参数 

​	java -jar xxxx.jar --server.port = 8087

2、来自java:com/env的 JNDI属性

3、Java系统属性（System.getProperties() ）

4、操作系统环境变量

5、RandomValuePropertySource 配置的 random.* 属性值

.....等等

**由jar包外向jar包内进行寻找;**
**优先加载带profile**

6.jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件 （同一文件夹下）
7.jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件

**再来加载不带profile**
8.jar包外部的application.properties或application.yml(不带spring.profile)配置文件
9.jar包内部的application.properties或application.yml(不带spring.profile)配置文件

10.@Configuration注解类上的@PropertySource
11.通过SpringApplication.setDefaultProperties指定的默认属性

所有支持的配置加载来源 ，参考官方文档



###<u>8、自动配置原理</u>

#### 1、自动配置原理

配置文件到底能写什么？怎么写？自动配置原理：

[配置文件能配置的属性参照：](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#server-properties)



**自动配置原理：（重要！！）**

1）、SpringBoot启动的时候加载主配置类，开启了自动配置功能 @EnableAutoConfiguration

**2)、@EnableAutoConfiguration作用：**

  - 利用AutoConfigurationImportSelector 给容器中导入一些组件

  - 查看selectImport()方法的内容

  - ```java
    autoConfigurationEntry = this.getAutoConfigurationEntry(annotationMetadata);
    return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
    ```

- ```java
  List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
  
  loadSpringFactories:
   Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
  
  扫描所有jar包类下路径下的 META-INF/spring.factories
  把扫描到的这些文件的内容包装成properties对象
  从properties中获取到 EnableAutoConfiguration.class类（类名）对应的值，然后把它们添加在容器中 
  ```



**将类路径下 META-INF/spring.factories 里面配置的所有EnableAutoConfiguration的值加入到了容器中**

```properties
spring.factories 文件...
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
...
```

每一个这样的 xxxAutoConfiguration类都是容器中的一个组件，都加入到容器中，用他们来做自动配置

3）、每一个自动配置类进行自动配置功能：

4）、以HttpEncodingAutoConfiguration 为例解释自动配置原理；

```java
// 表示这是一个配置类，以前编写的配置文件一样也可以给容器中加组件
@Configuration(
    proxyBeanMethods = false
)
// 启用指定类的 ConfigurationProperties功能
// ConfigurationProperties功能：将配置文件中对应的值和ServerProperties绑定起来
@EnableConfigurationProperties({ServerProperties.class})
//Spring底层@Conditional注解，根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效， 判断当前应用是否是web应用，如果是，当前配置类生效
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
// 判断当前项目有没有CharacterEncodingFilter这个类
// CharacterEncodingFilter:SpringMVC中进行乱码解决的过滤器
@ConditionalOnClass({CharacterEncodingFilter.class})
// 判断配置文件中是否存在某个配置: server.servlet.encoding
@ConditionalOnProperty(
    prefix = "server.servlet.encoding",
    value = {"enabled"},
    matchIfMissing = true // 如果不存在，判断也是成立的
)
// 即使我们配置文件中不配置server.servlet.encoding.enable=true,也是默认生效的
public class HttpEncodingAutoConfiguration {
    
    // 他已经和SpringBoot的配置文件映射了
    private final Encoding properties;

    // 只有一个有参构造器的情况下，参数的值就会从容器中拿
    public HttpEncodingAutoConfiguration(ServerProperties properties) {
        this.properties = properties.getServlet().getEncoding();
    }
    
    @Bean // 给容器中添加一个组件，这个组件的某些值需要从properties中获取
    @ConditionalOnMissingBean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
        filter.setForceRequestEncoding(this.properties.shouldForce(org.springframework.boot.web.servlet.server.Encoding.Type.REQUEST));
        filter.setForceResponseEncoding(this.properties.shouldForce(org.springframework.boot.web.servlet.server.Encoding.Type.RESPONSE));
        return filter;
    }
```

根据当前不同的条件判断，决定这个配置类是否生效?

**一旦这个配置类生效，这个配置类就会给容器中添加各种组件；这些组件的属性是从对应的properties类中获取的，这些类里面的每一个属性又是和配置文件绑定的**。



application.properties** 中能配置的类都来源于这个功能的properties类

spring.http.



5）、所有在配置文件中能配置的属性都是在xxxxProperties类中封装者;配置文件能配置什么就可以参照某个功育能对应的这个属性类

```java
ServerProperties类：

// 从配置文件中获取指定的值和Bean属性进行绑定
@ConfigurationProperties(
    prefix = "server",
    ignoreUnknownFields = true
)
public class ServerProperties {
```



**精髓：**

	1. **SpringBoot 启动会加载大量的自动配置类**
	2. **我们看我们需要的功能有没有SpringBoot默认写好的自动配置类**
	3. **我们再来看这个自动配置类中到底配置了哪些组件；（只要有我们要用的组件，我们就不需要再来配置了）**
	4. **给容器中自动配置类添加属性的时候，会从properties类中获取某些属性，我们就可以在配置文件中指定这些属性的值；**



xxxAutoConfiguration: 自动配置类

给容器中添加组件

xxxProperties: 封装配置文件中相关属性



#### 2、其他细节

1）、@**Controller** 派生注解的（Spring注解版原生的@Controller作用）

作用：必须是@Controller 指定的条件成立，才给容器中添加组件，配置类里边的所有内容才生效；

| Conditional扩展注解             | 作用（判断是否满足当前指定条件）                 |
| ------------------------------- | ------------------------------------------------ |
| @ConditionalOnjava              | 系统的java版本是否符合要求                       |
| @ConditionalOnBean              | 容器中存在指定Bean ;                             |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean ;                           |
| @ConditionalOnExpression        | 满足SpEL表达式指定                               |
| @ConditionalOnClass             | 系统中有指定的类                                 |
| @ConditionalOnMissingClass      | 系统中没有指定的类                               |
| @ConditionalonSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
| @ConditionalOnWebApplication    | 当前是web环境                                    |
| @ConditionalOnNotWebApplication | 当前不是web环境                                  |
| @ConditionalOnjndi              | JNDI存在指定项                                   |

**自动配置类必须在一定的条件下才能生效**；

我们怎么知道哪些自动配置类生效？

可以通过启用SpringBoot 的 debug 模式 ：(application.properties 中 **debug=true**属性)

来让控制台打印**自动配置报告**，这样就可以很方便的知道哪些自动配置类生效

```java
============================
CONDITIONS EVALUATION REPORT
============================


Positive matches: （自动配置类启动的）
-----------------

   AopAutoConfiguration matched:
      - @ConditionalOnProperty (spring.aop.auto=true) matched (OnPropertyCondition)
   AopAutoConfiguration.ClassProxyingConfiguration matched:
      - @ConditionalOnMissingClass did not find unwanted class 'org.aspectj.weaver.Advice' (OnClassCondition)
      - @ConditionalOnProperty (spring.aop.proxy-target-class=true) matched (OnPropertyCondition)
          
Negative matches:（自动配置类没启动的，没有匹配成功的自动配置类）
-----------------

   ActiveMQAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'javax.jms.ConnectionFactory' (OnClassCondition)

   AopAutoConfiguration.AspectJAutoProxyingConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.aspectj.weaver.Advice' (OnClassCondition)

```





# 三、日志

## 1、日志框架

小张; 开发一个大型系统;
	1、System.out.println("");将关键数据打印在控制台﹔去掉? 写在一个文件?
	2、框架来记录系统的一些运行时信息; 日志框架; zhanglogging.jar ;
	3、高大上的几个功能? **异步模式? 自动归档?** xxx ?  zhanglogging-good.jar ?
	4、将以前框架卸下来?换上新的框架，重新修改之前相关的APl ; zhanglogging-prefect.jar ;
	5、采用 JDBC---数据库驱动 模式;
		写了一个统一的接口层﹔日志门面（日志的一个抽象层）; logging-abstract.jar ;
		给项目中导入具体的日志实现就行了;我们之前的日志框架都是实现的抽象层﹔
市面上的日志框架﹔



市面上的日志框架；

JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j...

| 日志门面（日志的抽象层）                                     | 日志实现                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ~~JCL (Jakarta Commons Logging )~~、SLF4j ( Simple Logging Facade for Java）、jboss-logging | Log4j -还行、~~JUL ( java.util.logging )-凑数~~、Log4j2-最先进还未适配、Logback-较先进 |

左边选一个门面（抽象层）、右边来选一个实现

推荐：

日志门面：SLF4J

日志实现：Logback；（或者Log4j2）



SpringBoot: 底层是Spring框架，Spring框架默认是用 JCL

​	**SpringBoot 选用 SLF4j 和 logback**



## 2、SLF4j使用

### 1、如何在系统中使用SLF4j

以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日志抽象层里面的方法；

应该给系统里面导入slf4j的jar 和 logback的实现jar

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World"); // 打印到控制台 或输出到文件
  }
}
```



每一个日志的实现框架都有自己的配置文件，使用sf4j后；**配置文件还是做成日志实现框架中绑架自己身体的配置文件**



### 2、遗留问题

a系统（slf4j+logback）:其中又使用到其他框架Spring (commons-logging)、Hibernate ( Jboss-logging)、Mybatis、xxx

所以要 统一日志记录，即使是别的框架和我一起统一使用slf4j进行输出；

self4j文档：http://www.slf4j.org/images/legacy.png

- jcl-over-slf4j.jar replaces commons-logging.jar
- log4j-over-slf4j.jar replaces log4j.jar
- sLF4JBrindgeHandler is installed (requires jul-to-slf4j.jar)



**总结：如何让系统中所有的日志都统一到slf4j:**

<u>1、将系统中其他日志框架先排除出去﹔</u>
<u>2、用中间包来替换原有的日志框架;</u>
<u>3、我们导入sIf4j其他的实现</u>



## 3、SpringBoot日志关系

SpringBoot使用它来做日志功能：

（idea 中 pom文件dependencies 鼠标右击 选择 diagrams -> show 可查看）

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-logging</artifactId>
  <version>2.3.4.RELEASE</version>
  <scope>compile</scope>
</dependency>
```

总结：

1 )、SpringBoot底层也是使用slf4j+logback的方式进行日志记录
2 ) 、SpringBoot也把其他的日志都替换成了slf4j;
3 )、中间替换包 jul-to-slf4j、log4j-to-slf4j

4)、如果我们要引入其他框架、一定要把这个框架的默认日志依赖移除掉

**SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候，只需要把这个框架依赖的日志框架排除掉;**



## 4、日志使用

### 1、默认配置

SpringBoot 默认帮我们配置好了日志；

```java
// 记录器
Logger logger = LoggerFactory.getLogger(getClass());

@Test
void contextLoads() {
    //System.out.println(); //采用日志,不使用sout输出

    // 常用方法
    // 日志的级别：由高到低 trace<debug<info<warn<error
    // 可以调整输出的日志级别，日志就只会在这个级别以后的高级别生效

    logger.trace("这是trace日志..");
    logger.debug("这是debug日志..");
    // SpringBoot默认给我们使用的是info级别的（root级别）, 在配置文件中可进行调整logging.level
    logger.info("这是info日志..");
    logger.warn("这是warn日志..");
    logger.error("这是error日志..");

}
```

```xml
 日志输出格式：
	%d表示日期时问，
	%thread表示线程名，
	%-5level:级别从左显示5个字符宽度
	%logger{50}表示logger名字最长50个字符，否则按照句点分割。
	%msg∶日志消息，
	%n是换行符
	例：%d{yyyy-MM-dd HH:mm:ss.ssS} == [%thread] == %-5level == %logger{50} ==- %msg%n
```

SpringBoot修改日志的默认配置

```properties
# 限定在 com.lh 包下
logging.level.com.lh=trace

# 不指定路径，在当前项目下生成 pringboot.log文件
logging.file.name=springboot.log
# 在当前磁盘的根路径下创建spring文件夹和里面的log文件夹;使用spring.log作为默认文件
# logging.file.path = /spring/log


# 日志格式
# 默认：2020-09-28 17:20:54.608  WARN 13844 --- [           main] .l.s.SpringBoot03LoggingApplicationTests : 这是warn日志..
# 时间 日志级别 线程id ---(分割) 线程(main为主线程) 全类名 消息

# 在控制台输出的日志格式
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.ssS} == [%thread] == %-5level == %logger{50} ==- %msg%n
# 指定文件中日志输出的格式
#logging.pattern.file=
```



### 2、指定配置

给类路径下放上每个日志框架自己的配置文件即可;SpringBoot就不使用他默认配置的了

[详细参考](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-logging)

| Logging System          | Customization                                                |
| :---------------------- | :----------------------------------------------------------- |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

**logback.xml:** 直接就被日志框架识别了;
**logback-spring.xml:** 日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot的高级Profile功能

```properties
<springProfile name="dev | staging">
    <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
    可以指定某段配置只在某个环境下生效
</springProfile>
```

否则报错：no applicable action for [springProfile]

```xml
<appender nane="stdout" class="ch.qos.logback.core.ConsoleAppender">
    <!--
    日志输出格式:
    %d表示日期时问，
    thread表示线程名，
    %-5level:级别从左显示5个字符宽度
    %logger{50}表示logger名字最长58个字符，否则按照句点分割
    %msg:日志消息，
    ‰n是换行符
    -->
    <layout class="ch.qos.logback.classic.PatternLayout">
        <springProfile name="dev" >
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ---> [%thread] --->  %-5level %logger{50} - %msg%n</ pattern>
        </springprofile>
        <springProfile name="!dev">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50} - %msg%n</ pattern>
        </springProfile>
    </layout>
</ appender>

```



## 5、切换日志框架

可以按照slf4j的[日志适配图](http://www.slf4j.org/images/legacy.png)，进行相关的切换；





# 四、Web开发

使用SpringBoot:

**1）、创建SpringBoot应用，选中我们需要的模块(应用场景)；**

**2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来；**

**3）、自己编写业务代码；**



重点：弄懂**自动配置原理？**

选中某个场景时，思考这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展?xxx

spring-boot-autoconfiguration包

```java
xxxxAutoConfiguration:帮我们给容器中自动配置组件
xxxxProperties:配置类来封装配置文件的内容
```



# p29

