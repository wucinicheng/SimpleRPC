package com.ccp.simplerpc.codec;

import com.alibaba.fastjson.JSON;

public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
