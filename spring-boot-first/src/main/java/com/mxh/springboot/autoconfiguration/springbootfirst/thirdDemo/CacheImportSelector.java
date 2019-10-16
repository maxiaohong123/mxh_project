package com.mxh.springboot.autoconfiguration.springbootfirst.thirdDemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class CacheImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        Map<String,Object> attributes =  annotationMetadata.getAnnotationAttributes(EnableDefinitionService.class.getName());
        return new String[]{CacheService.class.getName()};
    }
}
