package com.lh.demo02;

public class UserServiceProxy implements UserService {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 日志方法（横切进原来的代码）
    public void log(String msg){
        System.out.println("日志：使用了"+msg+"方法");
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }
}
