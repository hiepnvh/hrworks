package com.gtel.hrworks.conf;

import java.io.InputStream;

import com.gtel.hrworks.util.ResourceLoader;

public class ServerConfig {
	static ResourceLoader LOADER;
	static String ACTIVE_CONFIG="testbed";
	static {
		try {
			String resourceName = "/conf/server.conf";
			InputStream is = ServerConfig.class.getResourceAsStream(resourceName);
			LOADER = ResourceLoader.loadFromText(is);
			ACTIVE_CONFIG = LOADER.getString("active");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get_TEMP_FOLDER() {
		return LOADER.getString(ACTIVE_CONFIG+"."+"TEMP_FOLDER");
	}
	
	public static String get_WEB_URL() {
		return LOADER.getString(ACTIVE_CONFIG+"."+"WEB_URL");
	}
	
	public static Boolean get_SEND_EMAIL() {
		return LOADER.getBoolean(ACTIVE_CONFIG+"."+"SEND_EMAIL");
	}
	
	public static String getPhysicalDir() {
		return LOADER.getString(ACTIVE_CONFIG+"."+"physical-dir");
	}
	
	
	public static String getVirtualDir() {
		return LOADER.getString(ACTIVE_CONFIG+"."+"virtual-dir");
	}
}
