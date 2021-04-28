## Spring-Study
    Spring学习记录
### 1、Spring

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
    
 在Spring中有三种自动装配方式
    - 在xml中显示的配置
    - 在java中显示配置
    - 隐式的自动装配bean 【重要】
    
 #### 7.1 测试
    
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
