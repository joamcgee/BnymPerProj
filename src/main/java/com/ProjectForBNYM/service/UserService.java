package com.ProjectForBNYM.service;

import com.ProjectForBNYM.Repositories.SkillsRepo;
import com.ProjectForBNYM.controller.UserRepo;
import com.ProjectForBNYM.model.SkillsModel;
import com.ProjectForBNYM.model.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired(required = true)
    private final UserRepo userRepo;
    @Autowired
    private final SkillsRepo skillsRepo;
    @Autowired
    private final SkillsService skillsService;

    public List<UserProfile> getAllProfile() {
        return userRepo.findAll();
    }
    public Optional<UserProfile> getByID(String Id){
        return userRepo.findById(Id);
        }

    public List<UserProfile> getByName(String name) {
        //grabbing a list of records
        List<UserProfile> records = userRepo.findAll();
        //creating a list to populate
        List<UserProfile> listOfExperts = new ArrayList<>();
        //for each record inside the list of records w/ "name", the system will render those users.
        records.forEach((record) -> {
            if (record.getName().equals(name)) {
                listOfExperts.add(record);
            } else {
                System.out.println("Users not found");
            }
        }); return listOfExperts;
    }

    //
    public List<UserProfile> getBySkill(String skillName) {
        //grabbing one skill ("skillName")
        SkillsModel skillsRecord = skillsService.getSkillByName(skillName);
        //grabbing a list of records
        List<UserProfile> records = userRepo.findAll();
        //creating a list to populate
        List<UserProfile> listOfProfiles = new ArrayList<>();
        //for each record inside the list of records w/ "skill", the system will render those users.
        records.forEach((record) -> {
            if (record.getSkills().contains(skillsRecord)) {
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
            if (userProfile.getName().charAt(i) != ' ')
                count++;
        }
        if (count >= 3) {
            addedUser.setEmployeeId(userProfile.getEmployeeId());
            addedUser.setName(userProfile.getName());
            addedUser.setDepartment(userProfile.getDepartment());
            addedUser.setDateJoined(LocalDate.now());
            addedUser.setSalary(userProfile.getSalary());
            if (userProfile.getSkills() != null) {
                addedUser.setSkills(userProfile.getSkills());
            }

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
