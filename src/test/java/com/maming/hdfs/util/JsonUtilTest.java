package com.maming.hdfs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class JsonUtilTest {

	@Test
	public void jsonArrayTest(){
		String message = "drwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim\r\ndrwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim1\r\ndrwxr-xr-x   - root hdfs          0 2016-06-15 11:11 /log/statistics/dim3";
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		String[] lines = message.split("\r\n");
		for(String line:lines){
			Map<String,String> recodes = new TreeMap<String,String>();
			recodes.put(line.substring(line.lastIndexOf(" ")+1),line);
			list.add(recodes);
		}
		System.out.println(JsonUtil.listToJsonArray(list));
	}

}
