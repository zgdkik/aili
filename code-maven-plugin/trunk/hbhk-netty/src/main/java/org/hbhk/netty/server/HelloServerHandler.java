package org.hbhk.netty.server;


import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // �յ���Ϣֱ�Ӵ�ӡ���
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        
        // ���ؿͻ�����Ϣ - ���Ѿ����յ��������Ϣ
        ctx.writeAndFlush("Received your message !\n");
    }
    
    /*
     * 
     * ���� channelActive ���� ��channel�����õ�ʱ�򴥷� (�ڽ������ӵ�ʱ��)
     * 
     * channelActive �� channelInActive �ں���������н����������Ȳ�����ϸ������
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        
        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
        
        super.channelActive(ctx);
    }
}