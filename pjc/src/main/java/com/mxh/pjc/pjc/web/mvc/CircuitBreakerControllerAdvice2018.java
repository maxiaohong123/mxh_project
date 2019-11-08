package com.mxh.pjc.pjc.web.mvc;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class CircuitBreakerControllerAdvice2018 {

    @ExceptionHandler
    public void onTimeoutException(TimeoutException timeoutException, Writer writer) throws IOException {
        writer.write(errorContent());
        writer.flush();
        writer.close();
    }

    //熔断后的处理方法
    public String errorContent(){
        return "error! see you say5";
    }

}
