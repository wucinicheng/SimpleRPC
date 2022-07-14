package com.ccp.simplerpc.example;

import com.ccp.simplerpc.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(10, 8);
        int r2 = service.minus(1, 2);

        System.out.println(r1);
        System.out.println(r2);
    }
}
