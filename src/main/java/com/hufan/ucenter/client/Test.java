package com.hufan.ucenter.client;

import java.io.InputStream;
import java.util.Properties;

public class Test {
	public static boolean IN_UC = true;
	public static String UC_IP = "127.0.0.1";
	public static String UC_API = "http://localhost/uc";
	public static String UC_CONNECT = "";
	public static String UC_KEY = "123456";
	public static String UC_APPID = "3";
	public static String UC_CLIENT_VERSION = "1.0";
	public static String UC_CLIENT_RELEASE = "20090212";
	public static String UC_ROOT = "";		//note 用户中心客户端的根目录 UC_CLIENTROOT
	public static String UC_DATADIR = UC_ROOT+"./data/";		//note 用户中心的数据缓存目录
	public static String UC_DATAURL = "UC_API"+"/data";			//note 用户中心的数据 URL
	public static String UC_API_FUNC = UC_CONNECT.equals("mysql") ? "uc_api_mysql" : "uc_api_post";
	public static String[] uc_controls = {};
	
	static {
	    InputStream in = Client.class.getClassLoader().getResourceAsStream("config.properties");
	    Properties properties = new Properties();
	    try {
			properties.load(in);
			UC_API = properties.getProperty("UC_API");
			UC_IP = properties.getProperty("UC_IP");
			UC_KEY = properties.getProperty("UC_KEY");
			UC_APPID = properties.getProperty("UC_APPID");
			UC_CONNECT = properties.getProperty("UC_CONNECT");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(UC_API);
	}
}
