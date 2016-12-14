package com.maming.hdfs.proxy.impl;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.maming.hdfs.util.JsonUtil;

public class HdfsProxyImplTest {

	@Test
	public void test1(){
		String message = "drwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim\r\ndrwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim2";
		String result = process(message);
		System.out.println(result);
	}

	
	private String process(String rawString){
		Map<String,String> recodes = new TreeMap<String,String>();
		String[] lines = rawString.split("\r\n");
		for(String line:lines){
			recodes.put(line.substring(line.lastIndexOf(" ")+1),line);
		}
		return JsonUtil.mapToJson(recodes).toString();
	}
}
