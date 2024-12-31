package com.security.securityproject.Entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsersPrinciples implements UserDetails {

    
    private Student student;
    public UsersPrinciples(Student student)
    {
        this.student=student;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
       return Collections.singleton(new SimpleGrantedAuthority("User"));
    }

    @Override
    public String getPassword() {
      
        return student.getPassword();
    }

    @Override
    public String getUsername() {

        return student.getName();
    }
    
}
