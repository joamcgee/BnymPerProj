package com.ProjectForBNYM.service;

import com.ProjectForBNYM.controller.UserRepository;
import com.ProjectForBNYM.model.UserModel;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired(required = true)
    UserRepository userRepository;
     public List<UserModel> getAllUsers() {
         return userRepository.getAllUsers();
     }
}
