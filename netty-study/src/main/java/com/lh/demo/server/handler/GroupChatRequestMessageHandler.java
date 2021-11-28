package com.lh.demo.server.handler;

import com.lh.demo.message.GroupChatRequestMessage;
import com.lh.demo.message.GroupChatResponseMessage;
import com.lh.demo.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * 遇到 GroupChatRequestMessage 才会进入 channelRead0
 *      不存在状态信息 可共享
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {

        // 得到所有群成员的 channels
        List<Channel> channels = GroupSessionFactory.getGroupSession()
                .getMembersChannel(msg.getGroupName());

        // 遍历, 向每一个 channel 发送信息
        for (Channel channel: channels){
            channel.writeAndFlush(new GroupChatResponseMessage(msg.getFrom(), msg.getContent()));
        }


    }
}
