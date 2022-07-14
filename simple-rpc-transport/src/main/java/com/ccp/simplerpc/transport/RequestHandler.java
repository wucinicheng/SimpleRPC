package com.ccp.simplerpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toResp);
}
