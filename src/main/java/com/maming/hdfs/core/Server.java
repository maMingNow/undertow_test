package com.maming.hdfs.core;

import io.undertow.Undertow;

import com.maming.hdfs.handler.HttpRequestHandler;

public class Server {

	public void start(){
	      Undertow server = Undertow.builder()
	                .addHttpListener(9966, "10.0.1.82")
	                .setHandler(new HttpRequestHandler()).build();
	        server.start();
	        System.out.println("server is start!");
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

}
