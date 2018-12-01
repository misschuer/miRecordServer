package cc.mi.record.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cc.mi.core.constance.IdentityConst;
import cc.mi.core.handler.Handler;
import cc.mi.core.log.CustomLogger;
import cc.mi.core.manager.ServerManager;
import cc.mi.core.packet.Packet;

public class RecordServerManager extends ServerManager {
	static final CustomLogger logger = CustomLogger.getLogger(RecordServerManager.class);
	
	private static RecordServerManager instance = new RecordServerManager();
	
	// 消息收到以后的回调
	private static final Map<Integer, Handler> handlers = new HashMap<>();
	private static final List<Integer> opcodes;
	
	// 帧刷新
	private final ScheduledExecutorService excutor = Executors.newScheduledThreadPool(1);
	// 消息包队列
	private final Queue<Packet> packetQueue = new LinkedList<>();
	// 最后一次执行帧刷新的时间戳
	protected long timestamp = 0;
	
	static {
		
		opcodes = new LinkedList<>();
		opcodes.addAll(handlers.keySet());
	}
	
	public static RecordServerManager getInstance() {
		return instance;
	}
	
	public RecordServerManager() {
		super(IdentityConst.SERVER_TYPE_RECORD, opcodes);
		excutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				long prev = instance.timestamp;
				long now = System.currentTimeMillis();
				int diff = 0;
				if (prev > 0) diff = (int) (now - prev);
				instance.timestamp = now;
				if (diff < 0 || diff > 1000) {
					logger.warnLog("too heavy logical that execute");
				}
				try {
					instance.doWork(diff);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}, 1000, 100, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 进行帧刷新
	 */
	private void doWork(int diff) {
		// 处理包信息
		this.dealPacket();
	}
	
	private void dealPacket() {
		while (!packetQueue.isEmpty()) {
			Packet packet = packetQueue.poll();
			this.invokeHandler(packet);
		}
	}
	
	private void invokeHandler(Packet packet) {
		int opcode = packet.getOpcode();
		Handler handle = handlers.get(opcode);
		if (handle != null) {
			handle.handle(null, this.centerChannel, packet);
		}
	}
	
	public void pushPacket(Packet packet) {
		synchronized (this) {
			packetQueue.add(packet);
		}
	}
}
