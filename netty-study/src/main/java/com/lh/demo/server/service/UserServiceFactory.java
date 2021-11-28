package com.lh.demo.server.service;

/**
 * 工厂类
 */
public abstract class UserServiceFactory {

    private static UserService userService = new UserServiceMemoryImpl();

    public static UserService getUserService() {
        return userService;
    }

}
