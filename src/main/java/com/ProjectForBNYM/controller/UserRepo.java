package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.model.UserProfile;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface UserRepo extends JpaRepository<UserProfile, String> {

    //The parameter must match the registered id in model class(profile_id) to the jpa repo will be ble to reference
    UserProfile findByProfileId(String profileId);

    UserProfile findByName(String name);

    List<UserProfile> findAll();

}
