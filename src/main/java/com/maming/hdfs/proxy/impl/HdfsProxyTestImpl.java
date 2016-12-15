package com.maming.hdfs.proxy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.maming.hdfs.proxy.ProxyInterface;
import com.maming.hdfs.util.JsonUtil;

/**
 * http://localhost:9966/hdfs?p=/log/statistic
 * 读取hdfs的内容
 */
public class HdfsProxyTestImpl implements ProxyInterface{

	private final static HdfsProxyTestImpl INSTANCE = new HdfsProxyTestImpl();
	
	public HdfsProxyTestImpl(){
		
	}
	
	public static HdfsProxyTestImpl getIntance(){
		return INSTANCE;
	}
	
	@Override
	public boolean init() {
		return true;
	}

	@Override
	public String getContent(Object param) {
		System.out.println("---");
		String message = "drwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim\r\ndrwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim1\r\ndrwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim3";
		return process(message);//drwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim
	}

	private String process(String rawString){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if("".equals(rawString)){
			Map<String,String> recodes = new TreeMap<String,String>();
			recodes.put("error", "content is empty!");
			list.add(recodes);
		}
		String[] lines = rawString.split("\r\n");
		for(String line:lines){
			Map<String,String> recodes = new TreeMap<String,String>();
			recodes.put(line.substring(line.lastIndexOf(" ")+1),line);
			list.add(recodes);
		}
		return JsonUtil.listToJsonArray(list).toString();
	}
	
	@Override
	public void flush() {
		
	}

}
