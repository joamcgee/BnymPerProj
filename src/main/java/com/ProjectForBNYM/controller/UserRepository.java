package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface UserRepository extends JpaRepository<UserModel, Long> {
    }

