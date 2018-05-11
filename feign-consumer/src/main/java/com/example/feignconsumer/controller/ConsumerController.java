package com.example.feignconsumer.controller;import com.example.feignconsumer.client.HelloServiceClient;import com.example.feignconsumer.client.RefactorHelloServiceClient;import com.example.feignconsumer.domain.User;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RestController;@RestController@RequestMapping(value = "/feign")public class ConsumerController {    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);    /**     * 引入Client并调用     */    @Autowired    HelloServiceClient helloServiceClient;    @Autowired    RefactorHelloServiceClient refactorHelloServiceClient;    @RequestMapping(value = "/consumer1", method = RequestMethod.GET)    public String helloConsumer() {        return helloServiceClient.hello();    }    @RequestMapping(value = "/consumer2", method = RequestMethod.GET)    public String helloConsumer2() {        StringBuilder sb = new StringBuilder();        sb.append(helloServiceClient.hello()).append("\n");        sb.append((helloServiceClient.hello("jack"))).append("\n");        sb.append(helloServiceClient.hello("jack", 30)).append("\n");        sb.append(helloServiceClient.hello(new User("jack", 30))).append("\n");        return sb.toString();    }    @RequestMapping(value = "/consumer3", method = RequestMethod.GET)    public String helloConsumer3() {        com.example.helloserviceapi.dto.User jack = new com.example.helloserviceapi.dto.User("jack", 30);        StringBuilder sb = new StringBuilder();        sb.append((refactorHelloServiceClient.hello4("sara"))).append("\n");        sb.append(refactorHelloServiceClient.hello5("sara", 30).toString()).append("\n");        sb.append(refactorHelloServiceClient.hello6(jack)).append("\n");        LOGGER.debug("result: {}", sb.toString());        return sb.toString();    }    @RequestMapping(value = "/consumer4", method = RequestMethod.GET)    public String helloConsumer4() {        com.example.helloserviceapi.dto.User sara = refactorHelloServiceClient.hello5("sara", 30);        return sara.toString();    }    @RequestMapping(value = "/consumer5", method = RequestMethod.GET)    public String helloConsumer5() {        com.example.helloserviceapi.dto.User jack = new com.example.helloserviceapi.dto.User("jack", 30);        return refactorHelloServiceClient.hello6(jack);    }}