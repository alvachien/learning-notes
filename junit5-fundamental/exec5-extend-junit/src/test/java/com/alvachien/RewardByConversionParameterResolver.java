package com.alvachien;

import com.alvachien.reward.RewardByConversionService;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class RewardByConversionParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        System.out.println("RewardByConversionParameterResolver.supportsParameter");
        return parameterContext.getParameter().getType().equals(RewardByConversionService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        RewardByConversionService service = new RewardByConversionService();
        service.setNeededPoints(100);        
        service.setAmount(10);
        return service;
    }    
}
