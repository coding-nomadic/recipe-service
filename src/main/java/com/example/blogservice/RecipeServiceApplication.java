package com.example.blogservice;

import com.example.blogservice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableCaching
public class RecipeServiceApplication implements CommandLineRunner {

    @Autowired
    RecipeRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(RecipeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.findAll().forEach(s->{
            System.out.println(s.getId());
            s.getComments().forEach(c->{
                System.out.println(c.getBody());
            });
        });
    }
}
