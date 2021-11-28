package com.lh.demo.server.handler;

import com.lh.demo.message.ChatRequestMessage;
import com.lh.demo.message.ChatResponseMessage;
import com.lh.demo.server.session.Session;
import com.lh.demo.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 数据类型 ChatRequestMessage
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        // 根据用户名找到对应的channel
        Channel channel = SessionFactory.getSession().getChannel(to);
        if(channel != null) {
            // 对方在线
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        } else {
            // 不在线 (发送者 为 ctx 的 channel)
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或者不在线"));
        }
    }
}
