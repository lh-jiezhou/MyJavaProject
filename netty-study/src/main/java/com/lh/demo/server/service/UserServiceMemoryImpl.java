package com.lh.demo.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  基于内存实现
 */
public class UserServiceMemoryImpl implements UserService{

    private Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }


    @Override
    public boolean login(String username, String password) {
        // 验证登录
        String pass = allUserMap.get(username);
        if (pass == null){
            return false;
        }
        return pass.equals(password);
    }
}
