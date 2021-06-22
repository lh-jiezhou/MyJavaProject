## mybatis-study
    MyBatis学习记录
    官方文档:https://mybatis.org/mybatis-3/zh/index.html
    https://mp.weixin.qq.com/mp/homepage?__biz=Mzg2NTAzMTExNg==&hid=3&sn=456dc4d66f0726730757e319ffdaa23e&scene=18#wechat_redirect
### 1、简介

### 2、第一个MyBatis程序

### 3、CURD

### 4、配置解析

### 5、属性名与字段名不一致的问题

### 6、日志

### 7、分页

### 8、使用注解开发

### 9、Lombok工具

### 10、多对一处理

### 11、一对多处理

### 12、动态SQL

### 13、缓存

#### 13.1 简介
1. 什么是缓存（cache）
- 存在内存中的临时数据
- 将用户经常查询的数据放在缓存(内存)中，用户去查询数据就不用从磁盘上(关系数据库数据文件)查询，
从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题

2. 为什么使用缓存
- 减少与数据库的交互次数，减少系统开销，提高系统效率

3. 什么样的数据使用缓存
- 经常查询并且不经常改变的数据

   
#### 13.2 MyBatis缓存

- MyBatis系统中默认定义了两级缓存：一级缓存 和 二级缓存
    - 默认情况，只有一级缓存开启。（SqlSession级别的缓存，也称为本地缓存）
    - 二级缓存需要手动开启和配置，基于namespace级别的缓存
    - 为了提高扩展性，MyBatis定义了缓存接口Cache，可以通过实现Cache接口来自定义二级缓存
    
    
#### 13.3 一级缓存

一级缓存在一次 SqlSession 中有效, （也就是拿到连接到关闭连接这个区间段）

测试步骤：
1. 开启日志
2. 测试在一个Session中查询两次相同记录
3. 查看日志输出

缓存失效情况：
- 查询不同的数据
- 执行了增删改操作
- 查询不同的Mapper.xml
- 手动清理了缓存 sqlSession.clearCache()



#### 13.4 二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存;
- 工作机制
    - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中;
    - 如果当前会话关闭了，这个会话对应的一级缓存就没了;但是我们想要的是，会话关闭了，一级缓存中的数据被保存到主级缓存中;
    - 新的会话查询信息，就可以从二级缓存中获取内容;
    - 不同的mapper查出的数据会放在自己对应的缓存(map）中;

步骤：
现在 mybatis 配置文件中显示地开启全局缓存
     <setting name="cacheEnabled" value="true"/>
在要使用二级缓存的 mapper.xml 中加入一个标签 <cache/> 即可开启二级缓存 （在同一个Mapper中有效)
    
测试：
- 需要将实体类序列化(public class User implements Serializable) 否则报错
    Error serializing object.  Cause: java.io.NotSerializableException: com.lh.pojo.User
   或者在cache中加入参数 readOnly="true"

小结：
- 二级缓存在同一个mapper中有效
- 所有的数据都会先放在一级缓存中
- 只有当会话提交，或者关闭的时候，才会提交到二级缓存中


#### 13.5 缓存原理

访问顺序：
   先看二级缓存中有没有；再看一级缓存中有没有；再看数据库
   
   
#### 13.6 自定义缓存 - Ehcache

Ehcache是一种广泛使用的开源Java分布式缓存。主要面向通用缓存

向导包
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.1</version>
</dependency>
