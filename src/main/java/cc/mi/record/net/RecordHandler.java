package cc.mi.record.net;

import cc.mi.core.handler.ChannelHandlerGenerator;
import cc.mi.core.packet.Packet;
import cc.mi.record.server.RecordServerManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RecordHandler extends SimpleChannelInboundHandler<Packet> implements ChannelHandlerGenerator {
	
	public void channelActive(final ChannelHandlerContext ctx) {
		RecordServerManager.getInstance().onCenterConnected(ctx.channel());
	}
	
	@Override
	public void channelRead0(final ChannelHandlerContext ctx, final Packet coder) throws Exception {
		
	}
	
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		RecordServerManager.getInstance().onCenterDisconnected(ctx.channel());
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
