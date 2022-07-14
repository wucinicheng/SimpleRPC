package com.ccp.simplerpc.client;

import com.ccp.simplerpc.Peer;
import com.ccp.simplerpc.codec.Decoder;
import com.ccp.simplerpc.codec.Encoder;
import com.ccp.simplerpc.codec.JSONDecoder;
import com.ccp.simplerpc.codec.JSONEncoder;
import com.ccp.simplerpc.transport.HTTPTransportClient;
import com.ccp.simplerpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
