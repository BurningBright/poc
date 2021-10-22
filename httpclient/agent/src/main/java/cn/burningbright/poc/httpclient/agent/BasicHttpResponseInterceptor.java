/*
 * Copyright (c) 2021. <lcg51271@gmail.com> All Rights Reversed.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package cn.burningbright.poc.httpclient.agent;

import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @author burningbright
 * @since 2021/10/20 17:32 PM
 */
@Slf4j
public class BasicHttpResponseInterceptor implements ClassFileTransformer {

    private static final String CLASS_NAME = "org/apache/http/message/BasicHttpResponse";
    private static final String HACK_METHOD = "getEntity";
    
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined
            , ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.equals(CLASS_NAME)) {
            String targetClassName = CLASS_NAME.replaceAll("\\/", ".");
            log.info("Found {}", className);
            
            try {
                ClassPool pool = ClassPool.getDefault();
                CtClass ctClass = pool.get(targetClassName);
                CtMethod method = ctClass.getDeclaredMethod(HACK_METHOD);

                StringBuilder code = new StringBuilder();
                code.append("org.apache.http.entity.BufferedHttpEntity newEntity = new org.apache.http.entity.BufferedHttpEntity(this.entity);");
                code.append("org.apache.http.HttpEntity originalEntity = this.entity;");
                code.append("this.entity = newEntity;");
                code.append("return originalEntity;");
                method.insertBefore(code.toString());

                ctClass.detach();
                return ctClass.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                log.error("Transform {} fail", CLASS_NAME, e);
            }
        }
        return null;
    }

}