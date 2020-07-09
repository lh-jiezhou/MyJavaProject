## Hello Spring Boot 学习

1. 创建一个maven工程；（.jar）

2. 导入spring boot相关的依赖

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

3. 编写一个主程序，启动spring boot 应用HelloWorldMainApplication.java

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

4. 编写相关的Controller、Service

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

5. 运行主程序测试

6. 简化部署

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