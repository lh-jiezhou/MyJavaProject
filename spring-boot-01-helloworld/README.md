## Hello Spring Boot 学习

### 一、第一个Hello World程序

####1. 创建一个maven工程；（.jar）

####2. 导入spring boot相关的依赖

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

####3. 编写一个主程序，启动spring boot 应用

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

####4. 编写相关的Controller、Service

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

####5. 运行主程序测试

####6. 简化部署

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



### 二、Holle World探究

####1、POM文件

##### 1. 父项目

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

##### 2. 启动器

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

####2、主程序类、主入口类

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



### 三、使用Spring Initializer快速创建Spring Boot项目

IDE都支持使用spring的项目创建向导快速创建一个spring boot项目；

IDEA为例：新建项目选择Spring Initializer

默认生成的Spring Boot项目；

- 主程序已经生成好，我们只需要我们自己的逻辑
- resources文件夹中目录结构
  - static： 保存所有的静态资源；js\css\images
  - templates：保存所有的模板页面；（Spring Boot默认jar包使用嵌入式的Tomcat,默认不支持jsp页面）；可以使用模板引擎（freemarker\ thymeleaf）
  - application.properties: Spring Boot配置文件



