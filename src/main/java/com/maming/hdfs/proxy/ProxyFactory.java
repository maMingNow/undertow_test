package com.maming.hdfs.proxy;

import com.maming.hdfs.proxy.impl.HdfsProxyImpl;
import com.maming.hdfs.proxy.impl.HdfsProxyTestImpl;

public class ProxyFactory {

	/**
	 * @param uri http://localhost:9966/aaa/bb/cc?dd=ee 输出/aaa/bb/cc
	 */
	public static ProxyInterface getInstance(String uri){
		ProxyInterface proxyInterface = null;
		if( uri.startsWith("/hdfs_test") ){
			proxyInterface = HdfsProxyTestImpl.getIntance();
		} else if( uri.startsWith("/hdfs") ){
			proxyInterface = HdfsProxyImpl.getIntance();
		} 
		return proxyInterface;
	}
}
