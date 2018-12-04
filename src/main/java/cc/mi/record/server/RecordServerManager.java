package cc.mi.record.server;

import cc.mi.core.constance.IdentityConst;
import cc.mi.core.log.CustomLogger;
import cc.mi.core.manager.ServerManager;

public class RecordServerManager extends ServerManager {
	static final CustomLogger logger = CustomLogger.getLogger(RecordServerManager.class);

	private static RecordServerManager instance = new RecordServerManager();
	
	public static RecordServerManager getInstance() {
		return instance;
	}
	
	@Override
	protected void onOpcodeInit() {
		
	}
	
	public RecordServerManager() {
		super(IdentityConst.SERVER_TYPE_RECORD);
	}
	
	@Override
	protected void doWork(int diff) {
		// 处理包信息
		this.dealPacket();
	}
}
