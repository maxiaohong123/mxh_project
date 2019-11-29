package com.mxh.pdc.pdc;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.stream.Stream;

public class RestTemplateDemo {

    public static void main(String[] args) throws UnknownHostException {
//        String PROVIDER_SERVER_URL_PREFIX = "http://localhost:8001";
//        RestTemplate restTemplate = new RestTemplate();
//        Collection collection = restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/findall", Collection.class);
//        System.out.println(collection);
//        System.out.println(restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/findall", String.class));

//        String PROVIDER_SERVER_URL_PREFIX = "http://localhost:8001";
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        Collection collection = restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/findall", Collection.class);
//        System.out.println(collection);


        Stream.of(InetAddress.getAllByName("www.visionvepal.com")).
                forEach(System.out::println);


    }
}
