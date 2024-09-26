package com.Raghab.shopApp.service;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User findBYUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
     return userRepository.findAll();
    }

    public boolean updateByUserName(User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User oldUser = findBYUserName(userName);
        if (oldUser!=null){
            oldUser.setPassword(!user.getPassword().isEmpty() ?passwordEncoder.encode(user.getPassword()):oldUser.getPassword());
            oldUser.setEmail(!user.getEmail().isEmpty() ?user.getEmail():oldUser.getEmail());
            //oldUser.setEmail(!user.getPhoneNumber().isEmpty()&& user.getPhoneNumber()!=null?user.getPhoneNumber():oldUser.getPhoneNumber());
           saveUser(oldUser);
            return  true;
        }
        return  false;
    }

    public boolean deleteByUserName(String userName) {
        return  true;
    }
}
