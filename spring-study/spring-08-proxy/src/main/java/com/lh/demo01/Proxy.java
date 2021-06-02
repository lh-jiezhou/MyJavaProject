package com.lh.demo01;


/**
 * 代理角色：
 *   代理真实角色后，一般还会进行一些附属操作
 */
public class Proxy implements Rent{

    private Host host; // 组合优于继承

    public Proxy(){
        super();
        this.host = new Host(); // 代理的真实角色
    }

//    public Proxy(Host host){
//        this.host = host;
//    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        contract();
        fare();
    }

    // 代理可以添加其他功能
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    public void contract(){
        System.out.println("签署租赁合同");
    }
    public void fare(){
        System.out.println("收中介费");
    }

}
