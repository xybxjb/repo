package cn.deepcoding.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImagUtils{
	public static String imgurl;
	
	
	static {
		InputStream in = ImagUtils.class.getClassLoader().getResourceAsStream("const.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			imgurl = (String) prop.get("image_url");
			System.out.println(imgurl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String imgUrl(){
		return imgurl;
	}

}
