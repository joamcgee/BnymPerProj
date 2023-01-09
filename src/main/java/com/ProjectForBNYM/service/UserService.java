package com.ProjectForBNYM.service;

import com.ProjectForBNYM.model.SkillsModel;
import com.ProjectForBNYM.model.UserProfile;
import com.ProjectForBNYM.repository.SkillsRepo;
import com.ProjectForBNYM.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public Optional<UserProfile> getByID(String Id) {
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
        });
        return listOfExperts;
    }


    public List<UserProfile> getBySkill(String skillName) {
        //grabbing the passed in "skillName"
        SkillsModel skillsRecord = skillsService.getSkillByName(skillName);
        //grabbing a list of records
        List<UserProfile> records = userRepo.findAll();
        //creating a list to populate with profiles corresponding to the passed in "skillName"
        List<UserProfile> listOfProfiles = new ArrayList<>();
        //for each record inside the list of records w/ "skill", the system will render those users.
        records.forEach((record) -> {
                    if (record.getSkills().contains(skillsRecord)) {
                        listOfProfiles.add(record);
                    } else {
                        System.out.println("Users not found");
                    }
                }
        );
        return listOfProfiles;
    }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        UserProfile addedUser = new UserProfile();
        int count = 0;
        //looping through the char count in name
        for (int i = 0; i < userProfile.getName().length(); i++) {
            //if the char in name at index, is not empty add to the count
            if (userProfile.getName().charAt(i) != ' ')
                count++;


        }
        //if the count is greater than three add new user
        if (count >= 3) {
            addedUser.setEmployeeId(userProfile.getEmployeeId());
            addedUser.setName(userProfile.getName());
            addedUser.setDepartment(userProfile.getDepartment());
            addedUser.setDateJoined(LocalDate.now());
            addedUser.setSalary(userProfile.getSalary());
            // create list of skills that are in the skills repo
            List<SkillsModel> listOfSkills = skillsRepo.findAll();
            //if the listOfSkills is empty, set the skills from the userProfile to the skillRepo
            if (listOfSkills.isEmpty()) {
                listOfSkills = skillsRepo.saveAll(userProfile.getSkills());
                addedUser.setSkills(listOfSkills);
            } else {
                List<SkillsModel> finalListOfSkills = listOfSkills;
                //if the list is not empty loop through the list of skills in the final list
                for (int i = 0; i < finalListOfSkills.size(); i++) {
                    SkillsModel modelOfSkills = finalListOfSkills.get(i);
                    //compare the skill ids
                    if (userProfile.getSkills().get(i).getId() != modelOfSkills.getId()) {
                        //saves new user with previous user's skill entries
                        addedUser.setSkills(finalListOfSkills);
                        //saves new user with original skills entered, however the similar skills' ids dont sync with the skills already added to the repo
                    //    addedUser.setSkills(userProfile.getSkills());

                        //if the skill ids match. Add the skill to the new user's skill
                    } else if (userProfile.getSkills().get(i).getId() == modelOfSkills.getId()){
                      addedUser.setSkills(userProfile.getSkills());
                        }
                }
            }
            return userRepo.save(addedUser);
        } else {
            System.out.println("Name field does not meet requirement of 3 character minimum");
            return null;
            }

}

    public void deleteById(String profileId) { userRepo.deleteById(profileId); }

    public List<UserProfile> getDepartment(String department) {
        //grabbing a list of records
        List<UserProfile> records = userRepo.findAll();
        //creating a list to populate
        List<UserProfile> listOfDepartmentEmps = new ArrayList<>();
        //for each record inside the list of records w/ "name", the system will render those users.
        records.forEach((record) -> {
            if (record.getDepartment().equals(department)) {
                listOfDepartmentEmps.add(record);
            } else {
                System.out.println("Users not found");
            }
        }); return listOfDepartmentEmps;
    }

    //Implement comparator so departmentEmployeeMap; can sort the map to find employees with associated departments
    public Map<String, Long> getDepartmentEmployeeCount(String department)  {
        //create new hashmap for department and number of employees
        Map<String, Long> departmentEmployeeMap = new HashMap<>();
        //create an object that holds all profiles in userRepo
        List<UserProfile> userProfileList = userRepo.findAll();
        //set employeeCount to 0
        int employeeCount = 0;
        departmentEmployeeMap =
        userProfileList.stream().collect(Collectors.groupingBy(UserProfile::getDepartment, Collectors.counting()));

            return departmentEmployeeMap;
   }

    }
