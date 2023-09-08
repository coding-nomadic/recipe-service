package com.recipe.server.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final ObjectMapper mapper;

    @Autowired
    public LoggingAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Pointcut("within(com.recipe.server.controller..*)")
    public void loggableMethods() {
    }

    @Before("loggableMethods()")
    public void logMethodEntry(JoinPoint joinPoint) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            log.info("Entered method '{}' with arguments: {}", signature.getMethod().getName(), mapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException e) {
            log.error("Error while converting to JSON", e);
        }
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, ResponseEntity<?> result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            log.info("Exited method '{}' with response: {}", signature.getMethod().getName(), mapper.writeValueAsString(result.getBody()));
        } catch (JsonProcessingException exception) {
            log.error("Error while converting response to JSON", exception);
        }
    }

    public Map<String, Object> extractMethodParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();
        Map<String, Object> parameterMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            parameterMap.put(parameterNames[i], parameterValues[i]);
        }
        return parameterMap;
    }
}
