package com.maming.hdfs.proxy;

public interface ProxyInterface {

    /**
     * 初始化代理
     */
    boolean init();

    /**
     * 获取配置
     *
     * @return
     */
    String getContent(Object param);

    /**
     * 刷新配置
     *
     * @return
     */
    void flush();
    
}
