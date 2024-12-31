package com.security.securityproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.securityproject.JWT.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jfilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {

        httpSecurity.csrf(customizer->customizer.disable());
        httpSecurity.authorizeHttpRequests(request->request.requestMatchers("logins").permitAll().anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jfilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();



    }

    //Mock UserName and Password not from database
    // @Bean
    // public UserDetailsService detailsService()
    // {
    //     @SuppressWarnings("deprecation")
    //     UserDetails userDetails=User.withDefaultPasswordEncoder()
    //     .username("storm")
    //     .password("storm")
    //     .roles("user")
        
    //     .build();
        




    //     return new InMemoryUserDetailsManager(userDetails);


    // }


   @Bean
   public  AuthenticationProvider authenticationProvider()
   {

        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        

        return daoAuthenticationProvider;


   }


   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder()
   {

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;

   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
   {

      return authenticationConfiguration.getAuthenticationManager();

   }


    
}
