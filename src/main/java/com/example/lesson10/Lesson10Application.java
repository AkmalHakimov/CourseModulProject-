package com.example.lesson10;

import com.example.lesson10.Config.runException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class Lesson10Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Lesson10Application.class, args);
    Db.Init();

//    int[] arr  = new int[10];
//    System.out.println(arr[10]);

//    if(true){
//      throw new runException();
//    }


//    System.out.println("Salom");

  }

}
