package org.hbhk.aili.boot.test;

import java.util.concurrent.Callable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    @RequestMapping("/api")  
    public Callable<String> api() {  
      System.out.println("=====hello");  
      return new Callable<String>() {  
          @Override  
          public String call() throws Exception {  
              Thread.sleep(10L * 1000); //暂停两秒  
              return "hbhk";  
          }  
      };  
  }  

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
