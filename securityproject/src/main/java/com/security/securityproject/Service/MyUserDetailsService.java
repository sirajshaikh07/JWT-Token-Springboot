package com.security.securityproject.Service;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.securityproject.Entity.Student;
import com.security.securityproject.Entity.UsersPrinciples;
import com.security.securityproject.Repository.StudentRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        Student student=studentRepo.getByUserName(username);
        if(student==null)
        {
            throw new UsernameNotFoundException("not found");
        }

        return new UsersPrinciples(student);
    }
    
}
