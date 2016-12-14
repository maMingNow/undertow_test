package com.maming.hdfs.core;

import com.maming.hdfs.util.UsingProcessBuilder;

public class ReadHdfs {

	public void readHdfs(String path){
		String[] args = {"hadoop","fs","-ls",path};
		String result = UsingProcessBuilder.executeCommands(args);
		System.out.println(result);
	}
	
	public void readHdfs(String[] args){
		String result = UsingProcessBuilder.executeCommands(args);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		ReadHdfs test = new ReadHdfs();
		for(String a:args){
			System.out.println("key==>"+a);
		}
		test.readHdfs(args[0]);
	}
}
