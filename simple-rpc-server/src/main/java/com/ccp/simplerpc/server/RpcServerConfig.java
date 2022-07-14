package com.ccp.simplerpc.server;

import com.ccp.simplerpc.codec.Decoder;
import com.ccp.simplerpc.codec.Encoder;
import com.ccp.simplerpc.codec.JSONDecoder;
import com.ccp.simplerpc.codec.JSONEncoder;
import com.ccp.simplerpc.transport.HTTPTransportServer;
import com.ccp.simplerpc.transport.TransportServer;
import lombok.Data;

/**
 * Server 配置
 */
@Data
public class RpcServerConfig {
    /**
     * 使用那个网络模块
     */
    private Class<? extends TransportServer> transportServerClass = HTTPTransportServer.class;
    /**
     * 使用那个序列化实现
     */
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    /**
     * 启动之后监听什么端口
     */
    private int port = 3000;

}
