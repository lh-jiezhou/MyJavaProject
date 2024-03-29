package com.lh.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 将配置文件(yaml)中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties: 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 * prefix = "person" 配置文件中哪个下面的所有属性进行一一映射
 *
 * @ConfigurationProperties(prefix = "person") 默认从全局配置文件中取值
 *
 * 只有这个组件是容器的组件，才能使用容器提供的@ConfigurationProperties功能
 */

//@PropertySource(value = {"classpath:person.properties"}) // classpath:后不加空格
@Component
@ConfigurationProperties(prefix = "person")
@Validated // 启动校验 例如 校验邮箱@Email类型
public class Person {

    /**
     * 以前赋值
     * <bean class="Person">
     *      <property name="lastName" value="字面值/ ${key}从环境变量，配置文件中获取值/ ${SpEl}"></property>
     * </bean>
     */

    // 现在 单个注入时
//    @Value("${person.last-name}")
    private String lastName;
//    @Value("#{11*2}") // #{SpEL} Spring表达式
//    @Email() // 校验
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public Person(){}

    public Person(String lastName, Integer age, Boolean boss, Date birth, Map<String, Object> maps, List<Object> lists, Dog dog) {
        this.lastName = lastName;
        this.age = age;
        this.boss = boss;
        this.birth = birth;
        this.maps = maps;
        this.lists = lists;
        this.dog = dog;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }


    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
