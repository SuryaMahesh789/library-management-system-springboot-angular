package com.example.knowledgehub_library_management_system.DebugPrac.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{

    @GetMapping("/test")
    public String test()
    {

        int a = 10;
        int b = 20;
        int result = a+b;
        return "Sum is: "+result;

    }

}
