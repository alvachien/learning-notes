package com.alvachien;

import javax.xml.stream.events.Namespace;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static com.alvachien.ExtensionUtils.*;

import java.security.cert.Extension;

public class IllegalArgumentExceptionHandlerExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof IllegalArgumentException) {
            // Case 1. For not report as error
            // System.out.println("Exception of type IllegalArgumentException thrown by " 
            //     + context.getRequiredTestClass().getName()
            //     + "#" 
            //     + context.getRequiredTestMethod().getName()
            // );
            // return;

            // // Case 2. Store it
            // context.getStore(NAMESPACE).put(EXCEPTION_KEY, throwable);
            // return;

            // Case 3. Use engine context
            ExtensionContext engiContext = getEngineContext(context);
            engiContext.getStore(NAMESPACE).put(EXCEPTION_KEY, throwable);
            
            return;
        }
        
        throw throwable;
    }
    
}
