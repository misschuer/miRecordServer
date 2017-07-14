package cc.mi.record;

import java.io.IOException;
import java.net.URL;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import cc.mi.record.recordClient.RecordClient;

public class Startup {
	private static final String RECORD_CLIENT = "recordClient";
	private static final String IP = "ip";
	private static final String PORT = "port";
	
	private static void loadConfig() throws NumberFormatException, Exception {
		Config cfg = new Config();
		URL url = Startup.class.getResource("/config.ini");
		Ini ini = new Ini();
        ini.setConfig(cfg);
        try {
        	// 加载配置文件  
        	ini.load(url);

        	Section section = ini.get(RECORD_CLIENT);
        	RecordClient.start(section.get(IP), Integer.parseInt(section.get(PORT)));
        	
        } catch (IOException e) {
        	e.printStackTrace();
	    }  
	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		loadConfig();
	}

}
