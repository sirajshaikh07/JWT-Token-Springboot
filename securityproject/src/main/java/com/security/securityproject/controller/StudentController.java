package com.security.securityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.securityproject.Entity.Student;
import com.security.securityproject.Service.StudentService;

@RestController
public class StudentController {
 
    @Autowired
    private StudentService service;


    @PostMapping(path = "/addstudent")
    public Student addStudent(@RequestBody Student student)
    {


        return service.addStudent(student);
    }
    

    @PostMapping(path = "/logins")
    public String login(@RequestBody Student student)
    {
       

        return service.varify(student);
    }

}
