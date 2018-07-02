package cc.mi.record.server;

import cc.mi.core.constance.IdentityConst;
import cc.mi.core.manager.ServerManager;

public class RecordServerManager extends ServerManager {
	private static RecordServerManager instance;
	
	public static RecordServerManager getInstance() {
		if (instance == null) {
			instance = new RecordServerManager();
		}
		return instance;
	}
	
	public RecordServerManager() {
		super(IdentityConst.SERVER_TYPE_RECORD);
	}
}
