package com.security.securityproject.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.securityproject.Entity.Student;
import com.security.securityproject.JWT.JwtService;
import com.security.securityproject.Repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    public Student addStudent(Student student)
    {

        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        
        return repo.save(student);
        

    }
    public String varify(Student student) {

       Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(student.getName(), student.getPassword()));
        
        if(authentication.isAuthenticated())
        {
            return jwtService.sendToken(student.getName());
        }
        return "Fail";

        
    }
    
}
