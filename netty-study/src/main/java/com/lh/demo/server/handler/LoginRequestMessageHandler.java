package com.lh.demo.server.handler;

import com.lh.demo.message.LoginRequestMessage;
import com.lh.demo.message.LoginResponseMessage;
import com.lh.demo.server.service.UserServiceFactory;
import com.lh.demo.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 将匿名类 重构为 内部类; 优化代码 Refactor -> Convert Anonymous to inner -> Move Inner Class
 *      无状态 可加 Sharable 使用时一个实例即可
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username = msg.getUsername();
        String password = msg.getPassword();

        boolean login = UserServiceFactory.getUserService().login(username, password);
        LoginResponseMessage message;
        if (login) {
            // 保存用户 和 channel 的对应关系
            SessionFactory.getSession().bind(ctx.channel(), username);
            message = new LoginResponseMessage(true, "登录成功");
        } else {
            message = new LoginResponseMessage(false, "用户名或密码不正确");
        }
        ctx.writeAndFlush(message);
    }
}
