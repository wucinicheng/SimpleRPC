package com.ccp.simplerpc.server;

import com.ccp.simplerpc.Request;
import com.ccp.simplerpc.ServiceDescriptor;
import com.ccp.simplerpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    ServiceManager serviceManager;

    @Before
    public void init(){
        serviceManager = new ServiceManager();
        TestClass bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }
    @Test
    public void register() {
        TestClass bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor descriptor = ServiceDescriptor.from(TestInterface.class, method);

        Request request = new Request();
        request.setServiceDescriptor(descriptor);


        ServiceInstance instance = serviceManager.lookup(request);
        assertNotNull(instance);


    }
}