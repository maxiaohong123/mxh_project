package com.mxh.starter.autoconfiguration;


import com.mxh.starter.format.FormatProcessor;
import com.mxh.starter.format.HelloFormatTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FormatAutoConfiguration.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Bean
    public HelloFormatTemplate helloFormatTemplate(FormatProcessor formatProcessor,HelloProperties helloProperties){
        return new HelloFormatTemplate(formatProcessor,helloProperties);
    }
}
