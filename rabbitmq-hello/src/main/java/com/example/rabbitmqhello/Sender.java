package com.example.rabbitmqhello;import org.springframework.amqp.core.AmqpTemplate;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;import java.util.Date;/** * Sender * * @author eliefly * @date 2018-03-29 */@Componentpublic class Sender {    @Autowired    private AmqpTemplate rabbitTemplate;    public void send() {        String context = "当前时间: " + new Date();        System.out.println("Sender: " + context);        this.rabbitTemplate.convertAndSend("hello", context);    }}