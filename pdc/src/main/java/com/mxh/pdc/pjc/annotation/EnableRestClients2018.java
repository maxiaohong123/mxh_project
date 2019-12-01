package com.mxh.pdc.pjc.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by Maxiaohong on 2019-12-01.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RestClientsRegistrar2018.class})
public @interface EnableRestClients2018 {

    Class<?>[] clients() default {};
}
