package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<UserProfile, String> {

    //The parameter must match the registered id in model class(UserProfile) to the jpa repo will be able to reference
    Optional<UserProfile> findById(String Id);

    UserProfile findByName(String name);

    List<UserProfile> findAll();
}
