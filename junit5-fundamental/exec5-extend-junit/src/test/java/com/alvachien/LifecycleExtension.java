package com.alvachien;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LifecycleExtension implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback,
AfterEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("beforeEachCallback");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("afterEachCallabck");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("beforeTestExecutionCallback");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("afterTestExecutionCallback");        
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("afterAllCallback");        
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("beforeAllCallbacok");        
    }
}
