package com.ccp.simplerpc.client;

import com.ccp.simplerpc.Peer;
import com.ccp.simplerpc.transport.TransportClient;

import java.util.List;

public interface TransportSelector {

    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();

}
