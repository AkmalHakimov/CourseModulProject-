package com.example.lesson10.Config;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class runException extends Exception {

    @Override
    public void printStackTrace() {
        System.out.println("huhuhu");
    }
    public runException(){
        System.out.println("huhuhu");
    }
}
