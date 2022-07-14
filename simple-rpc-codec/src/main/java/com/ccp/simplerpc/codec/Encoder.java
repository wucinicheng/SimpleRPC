package com.ccp.simplerpc.codec;

/**
 * 序列化接口
 */
public interface Encoder {
    byte[] encode(Object obj);
}
