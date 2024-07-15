package com.Raghab.shopApp.service;

import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =  userRepository.findByUserName(username);
       if (user!=null){
          return org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword()).build();
       }
       throw  new UsernameNotFoundException("User not found");
    }
}
