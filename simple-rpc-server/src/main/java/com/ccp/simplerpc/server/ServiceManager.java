package com.ccp.simplerpc.server;

import com.ccp.simplerpc.Request;
import com.ccp.simplerpc.ServiceDescriptor;
import com.ccp.simplerpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露出来的服务
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceDescriptor descriptor = ServiceDescriptor.from(interfaceClass, method);
            ServiceInstance instance = new ServiceInstance(bean, method);

            services.put(descriptor, instance);
            log.info("service registered:{},{}", descriptor.getClazz(), descriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor descriptor = request.getServiceDescriptor();
        return services.get(descriptor);
    }
}
