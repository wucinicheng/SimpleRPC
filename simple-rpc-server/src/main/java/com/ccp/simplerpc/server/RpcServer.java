package com.ccp.simplerpc.server;

import com.ccp.simplerpc.Request;
import com.ccp.simplerpc.Response;
import com.ccp.simplerpc.codec.Decoder;
import com.ccp.simplerpc.codec.Encoder;
import com.ccp.simplerpc.common.utils.ReflectionUtils;
import com.ccp.simplerpc.transport.RequestHandler;
import com.ccp.simplerpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;
    public RpcServer(){
        this(new RpcServerConfig());
    }
    public RpcServer(RpcServerConfig config) {
        this.config = config;

        // net
        this.net = ReflectionUtils.newInstance(config.getTransportServerClass());
        this.net.init(config.getPort(), handler);

        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response resp = new Response();

            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inBytes, Request.class);

                log.info("get request: {}", request);

                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);

                resp.setReturnData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1); // 错误状态吗
                resp.setMessage("Rpc server got error: "
                + e.getClass().getName()
                + " : " + e.getMessage());
            }finally {
                byte[] outBytes = encoder.encode(resp);
                try {
                    toResp.write(outBytes);
                    log.info("RpcServer response");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
