package com.lh.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component说明这个类被Spring接管了，注册到了容器中
@Component
public class User {

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Value("jiezhou") // 注入属性值
    public void setName(String name) {
        this.name = name;
    }
}
