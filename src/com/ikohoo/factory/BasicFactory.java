package com.ikohoo.factory;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	private static Properties prop = null;
	
	//static Logger logger = Logger.getLogger(BasicFactory.class);
	
	private BasicFactory(){}
	
	static{
		try{
			prop = new Properties();
			prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		}catch (Exception e) {
			e.printStackTrace();
			//logger.error(e);
			throw new RuntimeException(e);
		}
	}
	
	public static BasicFactory getFactory(){
		return factory;
	}
	
	public <T> T getInstance(Class<T> clazz){
		try{
		String infName = clazz.getSimpleName();
		String implName = prop.getProperty(infName);
		return (T) Class.forName(implName).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			//logger.error(e);
			throw new RuntimeException(e);
		}
		
	}

}
