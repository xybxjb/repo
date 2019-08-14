package cn.deepcoding.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConstUtils{
	static Properties prop;
	
	static {
		InputStream in = ConstUtils.class.getClassLoader().getResourceAsStream("const.properties");
		prop = new Properties();
		try {
			prop.load(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getString(String key){
		return (String)prop.get(key);
	}
	

}
