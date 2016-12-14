package com.maming.hdfs.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;

import com.maming.hdfs.proxy.ProxyFactory;
import com.maming.hdfs.proxy.ProxyInterface;

public class HttpRequestHandler implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
        //获得请求访问的uri  http://localhost:9966/aaa/bb/cc?dd=ee
        String uri = exchange.getRequestURI();//输出/aaa/bb/cc
        //通过uri,得到具体的配置文件代理类
        ProxyInterface proxy = ProxyFactory.getInstance(uri);
        //设置请求头
        HeaderMap responseHeaders = exchange.getResponseHeaders();
        responseHeaders.put(Headers.CONTENT_TYPE, "text/plain;charset=utf-8");
        
        String content = "{\"error\":\"uri is error!\"}";
        if (proxy != null) {
            content = proxy.getContent(exchange.getQueryParameters());
            if("".equals(content)){
            	content = "{\"error\":\"content is empty!\"}";
            }
        }
        exchange.getResponseSender().send(content);
	}

}
