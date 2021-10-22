/*
 * Copyright (c) 2021. <lcg51271@gmail.com> All Rights Reversed.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package cn.burningbright.poc.httpclient.agent;

import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * @author burningbright
 * @since 2021/10/20 17:32 PM
 */
@Slf4j
public class SimpleHttpClientAgent {

    private SimpleHttpClientAgent() {
        throw new IllegalStateException("agent class");
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        log.info("In premain method {}", agentArgs);
        inst.addTransformer(new BasicHttpResponseInterceptor(), true);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        log.info("In agentmain method {}", agentArgs);
    }

}
