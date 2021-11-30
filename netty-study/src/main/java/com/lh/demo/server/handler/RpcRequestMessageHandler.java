package com.lh.demo.server.handler;

import com.lh.demo.message.RpcRequestMessage;
import com.lh.demo.message.RpcResponseMessage;
import com.lh.demo.server.service.HelloService;
import com.lh.demo.server.service.ServicesFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 服务器端
 */
@Slf4j
@ChannelHandler.Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage message) throws Exception {

        RpcResponseMessage response = new RpcResponseMessage();
        response.setSequenceId(message.getSequenceId());
        try {
            // ===反射===
            // 根据接口名字找其实现类对象 (HelloService)
            HelloService service = (HelloService)
                    ServicesFactory.getService(Class.forName(message.getInterfaceName()));
            // (HelloService 中的 sayHello() 方法 )
            Method method = service.getClass().getMethod(message.getMethodName(), message.getParameterTypes());
            // 返回结果 HelloService.sayHello(message.getParameterValue())
            Object invoke = method.invoke(service, message.getParameterValue());

            response.setReturnValue(invoke);

        } catch (Exception e){
            e.printStackTrace();
            // 失败 则设置异常信息 (原始异常对象 过大)
//            response.setExceptionValue(e);
            // 只返回必要信息
            String msg = e.getCause().getMessage();
            response.setExceptionValue(new Exception("远程调用出错 "+ msg));
        }

        // 响应对象放回给客户端
        ctx.writeAndFlush(response);

    }



    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RpcRequestMessage message = new RpcRequestMessage(
                1,
                "com.lh.demo.server.service.HelloService",
                "sayHello",
                String.class,
                new Class[]{String.class},
                new Object[]{"张三"}
        );

        // ===反射===
        // 根据接口名字找其实现类对象 (HelloService)
        HelloService service = (HelloService)
                ServicesFactory.getService(Class.forName(message.getInterfaceName()));
        // (HelloService 中的 sayHello() 方法 )
        Method method = service.getClass().getMethod(message.getMethodName(), message.getParameterTypes());
        Object invoke = method.invoke(service, message.getParameterValue());
        System.out.println(invoke);
    }


}
