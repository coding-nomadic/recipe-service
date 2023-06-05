package com.example.blogservice;

import com.example.blogservice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableCaching
public class RecipeServiceApplication {

    @Autowired
    RecipeRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(RecipeServiceApplication.class, args);
    }
}
