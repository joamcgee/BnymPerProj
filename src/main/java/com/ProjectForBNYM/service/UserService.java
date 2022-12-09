package com.ProjectForBNYM.service;

import com.ProjectForBNYM.controller.UserRepo;
import com.ProjectForBNYM.model.UserProfile;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserService {
    @Autowired(required = true)
    UserRepo userRepo;

    public List<UserProfile> getAllProfile() {
        return userRepo.findAll();
    }
    public UserProfile getByID(String profileId){
        return userRepo.findByProfileId(profileId);
        }

    public List<UserProfile> getByName(String name) {
        List<UserProfile> records = userRepo.findAll();
        List<UserProfile> listOfExperts = new ArrayList<>();
        records.forEach((record) -> {
            if (record.getName().equals(name)) {
                listOfExperts.add(record);
            } else {
                System.out.println("Users not found");
            }
        }); return listOfExperts;
    }

    //
    public List<UserProfile> getBySkill(String skills) {
        //grabbing a list of records
        List<UserProfile> records = userRepo.findAll();
        //creating a list to populate
        List<UserProfile> listOfProfiles = new ArrayList<>();
        //for each record inside the list of records w/ "skill", the system will render those users.
        records.forEach((record) -> {
            if (record.getSkills().contains(skills)) {
                listOfProfiles.add(record);
            } else {
                System.out.println("Users not found");
            }
        }
        ); return listOfProfiles;
             }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        UserProfile addedUser = new UserProfile();
        int count = 0;
        for (int i = 0; i < userProfile.getName().length(); i++) {
            if (userProfile.getName().charAt(i) != ' ' )
                count ++;
        }
        if (count >= 3 ) {
            addedUser.setEmployeeId(userProfile.getEmployeeId());
            addedUser.setName(userProfile.getName());
            addedUser.setDepartment(userProfile.getDepartment());
            addedUser.setDateJoined(LocalDate.now());
            addedUser.setSalary(userProfile.getSalary());
            addedUser.setSkills(userProfile.getSkills());

            return userRepo.save(addedUser);

        } else {
            System.out.println("Name field does not meet requirement of 3 character minimum");
            return null;
            }
    }

    public void deleteById(String profileId) {
        userRepo.deleteById(profileId);
    }

}
