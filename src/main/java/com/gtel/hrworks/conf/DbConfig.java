package com.gtel.hrworks.conf;

import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Logger;

import com.gtel.hrworks.util.ResourceLoader;


public class DbConfig {
	static ResourceLoader LOADER;
	static String ACTIVE_DB="testbed";
	private static final Logger LOGGER = Logger.getLogger(ResourceLoader.class
			.getName());
	static {
		try {
			String resourceName = "/conf/db.conf";
			InputStream is = DbConfig.class.getResourceAsStream(resourceName);
			LOGGER.info("is " + is.toString());
			System.out.println("is " + is.toString());
			LOADER = ResourceLoader.loadFromText(is);
			ACTIVE_DB = LOADER.getString("active_camundaextable");
			Class c = Class.forName(getDbDriver());
			Driver driver = (Driver)c.newInstance();		
			DriverManager.registerDriver(driver);
			
//			Class cOrcl = Class.forName(getDbOrclDriver());
//			Driver driverOrcl = (Driver)cOrcl.newInstance();		
//			DriverManager.registerDriver(driverOrcl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.warning(e.getMessage());
		}
	}
	
	public static String getDbDriver() {
		return LOADER.getString(ACTIVE_DB+"."+"drivers");
	}
	
	public static String getPoolName() {
		return LOADER.getString(ACTIVE_DB+"."+"name");
	}

	public static String getlUrl() {
		return LOADER.getString(ACTIVE_DB+"."+"pool.url");
	}

	
	public static String getUser() {
		return LOADER.getString(ACTIVE_DB+"."+"pool.user");
	}
	
	public static String getPassword() {
		return LOADER.getString(ACTIVE_DB+"."+"pool.password");
	}
	
	public static Integer getMinPool() {
		return LOADER.getInt(ACTIVE_DB+"."+"pool.minpool");
	}
	
	public static Integer getMaxPool() {
		return LOADER.getInt(ACTIVE_DB+"."+"pool.maxpool");
	}
	
	public static Integer getMaxSize() {
		return LOADER.getInt(ACTIVE_DB+"."+"pool.maxsize");
	}
	
	public static Integer getIdleTimeout() {
		return LOADER.getInt(ACTIVE_DB+"."+"pool.idleTimeout");
	}
	/********************************************************/
	public static String getDbOrclDriver() {
		return LOADER.getString("driversorcl");
	}
	public static String getDWH3Url() {
		return LOADER.getString("dwh3url");
	}
	public static String getDWH3User() {
		return LOADER.getString("dwh3user");
	}
	public static String getDWH3Pass() {
		return LOADER.getString("dwh3pass");
	}
}
