package com.mxh.springboot.autoconfiguration.springbootfirst.thirdDemo;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class,LoggerDefinitionRegister.class})
public @interface EnableDefinitionService {
    Class<?>[] exclude() default {};
    String[] excludeName() default {};
}
