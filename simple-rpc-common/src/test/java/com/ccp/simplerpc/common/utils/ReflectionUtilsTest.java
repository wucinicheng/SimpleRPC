package com.ccp.simplerpc.common.utils;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @org.junit.Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @org.junit.Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);

        String mname  = methods[0].getName();
        assertEquals("b", mname);
    }

    @org.junit.Test
    public void invoke() {
        Method b = ReflectionUtils.getPublicMethods(TestClass.class)[0];

        TestClass t = new TestClass();
        Object r = ReflectionUtils.invoke(t, b);

        assertEquals("b", r);

    }
}