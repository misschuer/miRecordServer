package cc.mi.record.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cc.mi.core.constance.IdentityConst;
import cc.mi.core.handler.Handler;
import cc.mi.core.manager.ServerManager;

public class RecordServerManager extends ServerManager {
	private static RecordServerManager instance;
	
	// 消息收到以后的回调
	private static final Map<Integer, Handler> handlers = new HashMap<>();
	private static final List<Integer> opcodes;
	
	static {
		
		opcodes = new LinkedList<>();
		opcodes.addAll(handlers.keySet());
	}
	
	public static RecordServerManager getInstance() {
		if (instance == null) {
			instance = new RecordServerManager();
		}
		return instance;
	}
	
	public RecordServerManager() {
		super(IdentityConst.SERVER_TYPE_RECORD, opcodes);
	}
}
