package cc.mi.record;

import cc.mi.core.log.CustomLogger;
import cc.mi.core.net.ClientCore;
import cc.mi.record.config.ServerConfig;
import cc.mi.record.net.RecordHandler;

public class Startup {
	static final CustomLogger logger = CustomLogger.getLogger(Startup.class);
	private static void start() throws NumberFormatException, Exception {
		ServerConfig.loadConfig();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						ClientCore.INSTANCE.start(ServerConfig.getCenterIp(), ServerConfig.getCenterPort(), new RecordHandler());
						logger.devLog("连接中心服错误,系统将在1秒钟后重新连接");
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "bootstrap-to-center").start();
	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		start();
	}
}
