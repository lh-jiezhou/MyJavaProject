package com.lh.demo.client.handler;

import com.lh.demo.message.RpcResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端
 *      泛型通配符 ? 只能向泛型容器中获取值,而不能向泛型容器中设置值
 *
 *      考虑了线程安全问题 还是可以使用 Sharable
 */
@Slf4j
@ChannelHandler.Sharable
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RpcResponseMessage> {

    /**
     * 集合
     * <k, v> ==> < 序号 SequenceId, Promise 容器 用来接收结果>
      */
    public static final Map<Integer, Promise<Object>> PROMISES = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponseMessage msg) throws Exception {

        log.debug("{}", msg);

        // 拿到空的 promise; 使用后移除 不用 get 用 remove
        Promise<Object> promise = PROMISES.remove(msg.getSequenceId());

        // 保护性判断
        if(promise != null){
            Object returnValue = msg.getReturnValue();
            Exception exceptionValue = msg.getExceptionValue();
            if(exceptionValue != null){
                // 异常
                promise.setFailure(exceptionValue);
            } else {
                promise.setSuccess(returnValue);
            }
        }

    }
}
