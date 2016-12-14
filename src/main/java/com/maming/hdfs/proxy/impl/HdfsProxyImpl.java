package com.maming.hdfs.proxy.impl;

import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

import com.maming.hdfs.proxy.ProxyInterface;
import com.maming.hdfs.util.JsonUtil;
import com.maming.hdfs.util.UsingProcessBuilder;

/**
 * http://localhost:9966/hdfs?p=/log/statistic
 * 读取hdfs的内容
 */
public class HdfsProxyImpl implements ProxyInterface{

	private final static HdfsProxyImpl INSTANCE = new HdfsProxyImpl();
	
	public HdfsProxyImpl(){
		
	}
	
	public static HdfsProxyImpl getIntance(){
		return INSTANCE;
	}
	
	@Override
	public boolean init() {
		return true;
	}

	@Override
	public String getContent(Object param) {
		
		@SuppressWarnings("unchecked")
		Map<String,Deque<String>> params = (Map<String,Deque<String>>)param;
		
        if (params.get("p") == null || params.get("p").size() == 0) {
            return "没有发现p参数";
        }
        
		String path = params.get("p").peek();
		System.out.println("path===>"+path);
		String[] args = {"hadoop","fs","-ls",path};
		return process(UsingProcessBuilder.executeCommands(args));//drwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim
	}

	private String process(String rawString){
		Map<String,String> recodes = new TreeMap<String,String>();
		
		if("".equals(rawString)){
			recodes.put("error", "content is empty!");
		}
		String[] lines = rawString.split("\r\n");
		for(String line:lines){
			recodes.put(line.substring(line.lastIndexOf(" ")+1),line);
		}
		return JsonUtil.mapToJson(recodes).toString();
	}
	
	@Override
	public void flush() {
		
	}

}
