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



