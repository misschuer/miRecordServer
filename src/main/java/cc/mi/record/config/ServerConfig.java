package cc.mi.record.config;

import java.net.URL;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import cc.mi.core.constance.NetConst;

public class ServerConfig {
	private static final String CENTER = "center";
	
	private static String center_ip;
	private static int center_port;
	
	public static void loadConfig() throws NumberFormatException, Exception {
		Config cfg = new Config();
		URL url = ServerConfig.class.getResource("/config.ini");
		Ini ini = new Ini();
        ini.setConfig(cfg);
        try {
        	// 加载配置文件  
        	ini.load(url);

        	Section section2 = ini.get(CENTER);
        	center_ip = section2.get(NetConst.IP);
        	center_port = Integer.parseInt(section2.get(NetConst.PORT));
        	
        } catch (Throwable e) {
        	e.printStackTrace();
	    }  
	}
	
	public static String getCenterIp() {
		return center_ip;
	}
	
	public static int getCenterPort() {
		return center_port;
	}
}
