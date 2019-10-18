package com.mxh.starter.format;

import com.mxh.starter.autoconfiguration.HelloProperties;

public class HelloFormatTemplate {

    private FormatProcessor formatProcessor;
    private HelloProperties helloProperties;

    public  HelloFormatTemplate(FormatProcessor formatProcessor,HelloProperties helloProperties){
        this.formatProcessor = formatProcessor;
        this.helloProperties = helloProperties;
    }

    public  <T> String doFormat(T obj){
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("begin helloproties info:");
         stringBuilder.append(formatProcessor.format(helloProperties.getInfo())).append("</br>");
         stringBuilder.append("Execute format:").append("\n");
         stringBuilder.append("Obj format result:").append(formatProcessor.format(obj)).append("</br>");
         return  stringBuilder.toString();
    }
}
