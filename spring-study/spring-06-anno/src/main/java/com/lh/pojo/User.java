package com.lh.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 等价于<bean id="user" class="com.lh.pojo.user">
// 组件, 在配置文件中配置扫描
@Component
@Scope("prototype")  // 配置作用域
public class User {

//    private String name = "jiezhou";

    /**
     * @Value可加在属性 或 set 方法上；同时加以set为准
     */
    // 相当于 <property name="name" value="jiezhou"/>
    @Value("jiezhou--Pro") // 注入值
    private String name;

    public String getName() {
        return name;
    }

//    @Value("jiezhou--Set") // 注入值 (优先级高)
//    public void setName(String name) {
//        this.name = name;
//    }
}
