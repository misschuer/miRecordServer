package cc.mi.record.net;

import cc.mi.core.coder.Packet;
import cc.mi.core.handler.ChannelHandlerGenerator;
import cc.mi.record.system.RecordSystemManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RecordHandler extends SimpleChannelInboundHandler<Packet> implements ChannelHandlerGenerator {
	
	public void channelActive(final ChannelHandlerContext ctx) {
		System.out.println("connect to center success");
		RecordSystemManager.setCenterChannel(ctx.channel());
		RecordSystemManager.regToCenter();
	}
	
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
	}
	
	@Override
	public void channelRead0(final ChannelHandlerContext ctx, final Packet coder) throws Exception {
		
	}
	
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("record client inactive");
		ctx.fireChannelInactive();
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
		throwable.printStackTrace();
		ctx.close();
	}

	@Override
	public ChannelHandler newChannelHandler() {
		return new RecordHandler();
	}
}
