package cc.mi.record;

import cc.mi.core.net.ClientCore;
import cc.mi.record.config.ServerConfig;
import cc.mi.record.net.RecordHandler;

public class Startup {
	private static void start() throws NumberFormatException, Exception {
		ServerConfig.loadConfig();
		ClientCore.INSTANCE.start(ServerConfig.getCenterIp(), ServerConfig.getCenterPort(), new RecordHandler());
	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		start();
	}
}
