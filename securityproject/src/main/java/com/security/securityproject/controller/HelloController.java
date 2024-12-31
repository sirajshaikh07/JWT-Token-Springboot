package com.security.securityproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.securityproject.Entity.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    List<Student>list=new ArrayList<>(List.of(
    new Student(1,"siraj","shaikh@gmail.com", null),
    new Student(2,"Ram","Ram@gmail.com",null)
    ));

    @GetMapping(path="/")
    public CsrfToken sendHello(HttpServletRequest httpServletRequest)
    {
        CsrfToken csrfToken=(CsrfToken) httpServletRequest.getAttribute("_csrf");

        return csrfToken;
    }
    

    @GetMapping(path = "/student")
    public List<Student>getAllStudent()
    {
        return list;
    }

    @PostMapping(path = "/student")
    public Student addStudent(@RequestBody Student student)
    {
        list.add(student);
        return student;

    }
    
    
}
