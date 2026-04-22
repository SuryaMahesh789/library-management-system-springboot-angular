package com.example.knowledgehub_library_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@EnableJpaAuditing
@SpringBootApplication
public class KnowledgeHubLibraryManagementSystemApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(KnowledgeHubLibraryManagementSystemApplication.class, args);
        System.out.println("Hello World..");
    }

}


//netstat -ano | findstr :8080 to check PORT


