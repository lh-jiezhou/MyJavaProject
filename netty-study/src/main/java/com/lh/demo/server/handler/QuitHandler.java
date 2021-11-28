package com.lh.demo.server.handler;

import com.lh.demo.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理退出
 *      正常退出 channelInactive
 *      异常退出(直接点×关闭) exceptionCaught
 *
 */
@Slf4j
@ChannelHandler.Sharable
public class QuitHandler extends ChannelInboundHandlerAdapter {

    // 连接断开时 触发 Inactive 事件
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 把 channel 从会话管理器中移除 (解绑)
        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("{} 已经断开", ctx.channel());
    }

    // 当捕捉住异常时 触发  exceptionCaught
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 把 channel 从会话管理器中移除 (解绑)
        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("{} 已经异常断开 异常是 {}", ctx.channel(), cause.getMessage());
    }
}
