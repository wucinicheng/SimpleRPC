package com.ccp.simplerpc;

import lombok.Data;

/**
 * 表示RPC的返回
 */
@Data
public class Response {
    private int code = 0;   // 0 成功，非0失败
    private String message = ""; // 具体的错误信息
    private Object returnData; // 返回的数据
}
