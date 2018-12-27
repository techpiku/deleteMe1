package com.itc.apollo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

public class EnvEntryServlet extends HttpServlet 
{
	private static final long serialVersionUID = -5876244938908563798L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doPost Method of WebPageServlet ...");
		StringBuilder builder = new StringBuilder();
		//Read the file
		InputStream inStream = null;
		try {
			File file = new File("C:/TEMP/a.txt");
			byte[] buffer = new byte[(int) file.length()];
			inStream = new FileInputStream(file);
			inStream.read(buffer);
			String data = new String(buffer);
			builder.append(data);
		} catch (Exception e) {
			e.printStackTrace();
			builder.append("------------ Error in Reading File ---------");
		}
//		CacheManager cacheManager = null;
//		String ehcacheConfigPath = System.getenv("EHCACHE_CONFIG");
//		if (null == ehcacheConfigPath) {
//			System.out.println("Environment variable EHCACHE_CONFIG has not yet set properly.");
//			System.out.println("Trying to load the default Cache Manager setting ...");
//			builder.append("\nEnvironment variable EHCACHE_CONFIG has not yet set properly.\n");
//			builder.append("\nTrying to load the default Cache Manager setting ...\n");
//			cacheManager = new CacheManager();
//		} else {
//			File file = new File(ehcacheConfigPath);
//			if (!file.exists()) {
//				System.out.println("Ehcache config file is not located in the defined path");
//				System.out.println("Trying to load the default Cache Manager setting ...");
//				builder.append("\nEhcache config file is not located in the defined path\n");
//				builder.append("\nTrying to load the default Cache Manager setting ...\n");
//				cacheManager = new CacheManager();
//			} else {
//				cacheManager = CacheManager.create(ehcacheConfigPath);
//				builder.append("\nLoading ehcache config file is successfull...\n");
//				builder.append("\nEhcache instance : "+cacheManager);
//			}
//		}
//		builder.append("\n------------ END ------------");
//		builder.append("\n------------ LIST OF ENV VARS ------------");
//		Map<String,String> map = System.getenv();
//		Iterator<Entry<String, String>> itr = map.entrySet().iterator();
//		while( itr.hasNext()) {
//			Map.Entry<String, String> me = itr.next();
//			System.out.println(me.getKey()+"-----"+me.getValue());
//			builder.append("\n").append(me.getKey()+"---"+me.getValue()).append("\n");
//		}
		builder.append("\n------------ END ------------");
		System.out.println("-------->"+builder.toString());
		PrintWriter out = response.getWriter();
		out.println(builder.toString());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of WebPageServlet ...");
		doPost(request, response);
	}
	
}
