package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.ExceptionHandling.ResourceNotFoundException;
import com.ProjectForBNYM.model.UserProfile;
import com.ProjectForBNYM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    //MAKES API endpoint static
    private static final String PROFILE = "/profile";


    // get all of employee profile details
    @GetMapping(PROFILE + "/getProfile")
    public ResponseEntity<List<UserProfile>> getProfile() {
        List<UserProfile> response;
        try {
            response = userService.getAllProfile();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResourceNotFoundException("Not found Tutorial with id = ");
        }

    }


    // get employee profile by ID
    @GetMapping(PROFILE + "/get-profile-by-id")
    public ResponseEntity<UserProfile> getByID(@RequestParam String profile_id) {
        Optional<UserProfile> response = userService.getByID(profile_id);
        if (response.isPresent()) {
            return ResponseEntity.ok().body(response.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get employee profile by name
    @GetMapping(PROFILE + "/name")
    public ResponseEntity<List<UserProfile>> getByName(@RequestParam String name) {
        try {
            List<UserProfile> response = userService.getByName(name);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get employee profile by skill
    @GetMapping(PROFILE + "/skill")
    public ResponseEntity<List<UserProfile>> getBySkill(@RequestParam String skillName) {
        try {
            List<UserProfile> response = userService.getBySkill(skillName);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //insert employee details
    //use @RequestBody when working with a object(UserProfile) rather than requesting one value (@RequestParam)
    @PostMapping(PROFILE + "/saveProfile")
    public ResponseEntity<UserProfile> saveProfile(@RequestBody UserProfile userProfile) {
        UserProfile response = userService.saveUserProfile(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(PROFILE + "/delete")
    public ResponseEntity<String> deleteById(@RequestParam String Id) {

        userService.deleteById(Id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(""); //no content status update doesn't body messages
    }

    @GetMapping (PROFILE + "/departmentCount")
    public ResponseEntity<Map<String, Long>> getDepartmentEmployeeCount(@RequestParam String department) {
        Map<String, Long> response = userService.getDepartmentEmployeeCount(department);
        return ResponseEntity.ok().body(response);
    }
}
