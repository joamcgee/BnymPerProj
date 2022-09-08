package com.ProjectForBNYM.controller;



import com.ProjectForBNYM.model.UserModel;
import com.ProjectForBNYM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();

    }




}
