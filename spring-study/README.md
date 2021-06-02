## Spring-Study
    Spring学习记录
    https://mp.weixin.qq.com/mp/homepage?__biz=Mzg2NTAzMTExNg==&hid=3&sn=456dc4d66f0726730757e319ffdaa23e&scene=18#wechat_redirect
    
### 1、Spring
- Spring是一个开源免费的框架 , 容器  .

- Spring是一个轻量级的框架 , 非侵入式的 .

- 控制反转 IoC  , 面向切面 Aop

- 对事物的支持 , 对框架的支持

.......

一句话概括：

Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器（框架）。


### 2、IOC理论推导
控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。

### 3、HelloSpring


### 4、IOC创建对象方式
<!--没有无参构造函数，使用有参构造函数-->
    <bean id="user" class="com.lh.pojo.User">
        <!--1、下标匹配-->
        <!--<constructor-arg index="0" value="下标 李豪"/>-->
        <!--2、类型匹配, 有重复类型则无法使用-->
        <!--<constructor-arg type="java.lang.String" value="类型 李豪"/>-->
        <!--3、通过构造函数中的参数名-->
        <constructor-arg name="name" value="参数名 李豪"/>
    </bean>
    
    总结:在配置文件加载的时候，容器中管理的对象就已经初始化了!

### 5、Spring配置

#### 5.1别名
        <alias name="user" alias="userNew"/>
        getBean()时，可通过别名取
#### 5.2 Bean的配置
        id: bean的唯一标识符
        class: 对象对应全限定名: 包名+类型
        name: 别名
#### 5.3 import
        一般用于团队开发使用，可以将多个配置文件导入合并为一个
        applicationContext.xml 中写
            <import resource="beans.xml"/>
        
### 6、依赖注入DI
    官方文档 https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-p-namespace
#### 6.1 构造器注入
    见4
#### 6.2 set注入
- 依赖注入：set注入
    - 依赖：bean对象的创建依赖于容器
    - 注入：bean对象的所有属性由容器来注入
  
不同类型参数的注入方式
    <bean id="student" class="com.lh.pojo.Student">
            <!--1、普通类型set注入, value-->
            <property name="name" value="李豪"/>
            <!--2、Bean对象注入, ref-->
            <property name="address" ref="address"/>
            <!--3、数组注入-->
            <property name="books">
                <array>
                    <value>西游记</value>
                    <value>水浒传</value>
                </array>
            </property>
            <!--4、List注入-->
            <property name="hobbies">
                <list>
                    <value>羽毛球</value>
                    <value>足球</value>
                </list>
            </property>
            <!--5、Map注入-->
            <property name="card">
                <map>
                    <entry key="ID" value="430521"/>
                    <entry key="Bank" value="6217"/>
                </map>
            </property>
            <!--6、Set注入-->
            <property name="games">
                <set>
                    <value>王者</value>
                    <value>吃鸡</value>
                </set>
            </property>
            <!--7、空值注入-->
            <property name="wife">
                <null/>
            </property>
            <!--8、Properties注入-->
            <property name="info">
                <props>
                    <prop key="id">190327045</prop>
                    <prop key="sex">男</prop>
                    <prop key="username">李豪</prop>
                </props>
            </property>
    
        </bean>

#### 6.3 其他注入
    <!--导入约束 xmlns:p="http://www.springframework.org/schema/p"-->
        <!--p命名空间注入，可以直接注入属性的值：property-->
        <bean id="user" class="com.lh.pojo.User" p:age="21" p:name="李豪"></bean>
    
        <!--导入约束 xmlns:c="http://www.springframework.org/schema/c"-->
        <!--c命名空间注入，使用构造器注入，需要有参构造函数：construct-args-->
        
#### 6.4 bean的作用域; Bean Scopes
    - singleton 单例模式 (Spring默认机制)
        <bean .. scope="singleton">
    - prototype 原型模式：每次从容器中get的时候，都会产生一个新对象
        <bean .. scope="prototype">
    - 其余的request、session、application、这几个只能在web开发中使用到
    
    
### 7、Bean的自动装配

    - 自动装配是Spring满足bean依赖的一种方式
    - Spring会在上下文中自动寻找、并自动给bean装配属性
    
 在Spring中有三种装配方式
 - 在xml中显示的配置
 - 在java中显示配置
 - 隐式的自动装配bean 【重要】
    
 #### 7.1 测试
 https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core
    
 #### 7.2 ByName自动装配
         autowire="byName"
            会自动在容器上下文中查找，和自己对象set方法后面的值对应beanid
 #### 7.3 ByType自动装配
         autowire="byType"
            会自动在容器上下文中查找，和自己对象属性类型相对的bean
    
    小结：
    - byName的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致!. 
    - byType的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致!

#### 7.4 使用注解实现自动装配

    jdk1.5开始支持注解，spring2.5开始支持注解
    
    要使用注解须知
    1. 导入约束：context约束
    2. 配置注解的支持: <context:annotation-config/>
    
    @Autowired：原理(反射)
       - 直接写在属性上，
       使用Autowired，可以省略set方法；前提是你这个自动装配的属性在IOC(Spring)容器中存在
       且符合 byType
    
        注解是反射直接给属性复制
        xml是反射获取getter/setter方法复制
    
    注：
        @NullAble：允许为空
        @Autowired(required = false) 说明这个对象可以为null,否则不可以为空
        @Resource 也可以自动配置(属java的注解)；先通过id名查找，再通过type类型查找
        
       
    如果仅注解@Autowired无法装配时，可配合@Qualifier(value = "xxx")使用
    @Qualifier(value = "id") ; 类型重复时，Qualifier通过id选择 （类似别名）
    
    
    @Resource与@Autowired区别
        - 都可放在属性字段上，用于自动注入
        - @Autowired是byType,如果有多个Type,再byName,可结合@Qualifier来选择
        - @Resource是byName,如果找不到再bYType
        
        
### 8、使用注解开发

    在spring4之后使用注解开发，要
    
#### 1、bean
    @Component:组件，放在类上，说明这个类被Spring管理了，就是bean

#### 2、属性如何注入

- 可以不用提供set方法，直接在直接名上添加@value("值")
- 如果提供了set方法，在set方法上添加@value("值");
- 注：同时加以set为准

#### 3、延伸的注解
    @Component有几个衍生注解，在web开发中，会按照mvc三层架构分层
    - dao 【@Repository】
    - service 【@Service】
    - controller  【@Controller】
    这四个注解功能都是一样的，都是代表将某个类注册到Spring中，装配Bean
    （要配置扫描包）
    
#### 4、自动装配注解

#### 5、作用域
    @Scope()
    
#### 6、小结
    xml与注解
    - xml更加万能，适用于任何场合，维护简单方便
    - 注解不是自己类使用不了，维护相对复杂
    xml与注解最佳实践
    - xml用来管理bean
    - 注解只负责完成属性的注入
    - 使用过程中注意让注解生效(驱动 和 扫描)、
        <!--指定要扫描的包，这个包下面的注解就会生效-->
        <context:component-scan base-package="com.lh"/>
        <!--注解支持-->
        <context:annotation-config/>
 
 
 
 ### 9、使用Java的方式配置Sprig
 
 完全不使用Spring的xml配置，全权交给Java来做
 JavaConfig是Spring的一个子项目，在Spring4之后，他成为了一个核心功能
 
 spring-07-javaconfig
 
 这种纯java的配置方式 在SpringBoot中随处可见
    
    
### 10、代理模式

#### 10.1 静态代理
角色分析:(代码步骤同)
·抽象角色:一般会使用接口或者抽象类来解决
·真实角色︰被代理的角色
·代理角色︰代理真实角色，代理真实角色后，我们一般会做一些附属操作
·客户: 访问代理对象的人!

代理模式的好处:
- 可以使真实角色的操作更加纯粹!不用去关注一 些公共的业务
- 公共也就就交给代理角色!实现了业务的分工!
- 公共业务发生扩展的时候，方便集中管理!

缺点:
- 一个真实角色就会产生-一个代理角色;代码量会翻倍开发效率会变低~


#### 10.2 加深理解
    通过代理 横切 （横向编程）
    在不该变源代码的基础上 添加相关操作
    进一步解耦
    
#### 10.3 动态代理
    - 动态代理和静态代理角色一样
    - 动态代理类是动态生成的，不是直接写好的
    - 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
        基于接口的代理--JDK动态代理【此例子使用】
        基于类的代理--cglib
        
        java字节码实现：javasist
        
    
    需要了解两个类：Proxy:代理； InvocationHandler:调用处理程序
    
    (java.lang.reflect包)
    InvocationHandler: 
    Proxy: 提供了创建动态类和实例的静态方法，他是由这些方法创建的所有动态代理类的父类
     
    动态代理的好处:
    - 可以使真实角色的操作更加纯粹!不用去关注一些公共的业务
    - 公共也就就交给代理角色!实现了业务的分工!
    - 公共业务发生扩展的时候，方便集中管理!
    - 一个动态代理类代理的是一个接口，一般就是对应的一类业务.
    - —个动态代理类可以代理多个类，只要是实现了同一个接口即可

        
### 11、AOP

#### 11.1、什么是AOP
   不影响原来类(源代码)的情况下，实现代码的动态增强
   进一步解耦

#### 11.2、Aop在Spring中的作用

#### 11.3、使用Spring实现Aop

    首先需要导入一个依赖包
         <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.9.6</version>
         </dependency>
    
    
    方式一：使用Spring的API接口 
        主要是SpringAPI接口实现
    
    方式二：自定义来实现DIY类 （功能没有方式一强大）
        主要是切面定义
        
    方式三：注解实现AOP
   
 
 ### 12、整合mybatis
 
 步骤：
 1. 导入相关jar包
 - junit
 - mybatis
 - mysql数据库
 - spring相关的
 - aop织入
 - mybatis-spring
 
 2. 编写配置文件
 
 3. 测试
 
 
 报错; java.lang.NoClassDefFoundError: org/springframework/core/metrics/Application
 一般为Maven版本冲突
 
 #### 12.1、方式一 新建SqlSessionTemplate
 
 #### 12.2、方式二 继承SqlSessionDaoSupport
 
 
 ### 13、声明式事务
 
 #### 13.1、回顾事务
 

- 把一组业务当成一个业务来做;要么都成功，要么都失败!
- 事务在项目开发中，十分的重要，涉及到数据的一致性问题，不能马虎!
- 确保完整性和一致性;

 事务ACID原则:
- 原子性
- 一致性
- 隔离性
    多个业务可能操作同一个资源，防止数据损坏
- 持久性


#### 13.2、spring中的事务管理
- 声明式事务: 交由容器管理事务 AOP应用

- 编程时事务: 需要在代码中，进行事务的管理


