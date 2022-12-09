package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.model.UserProfile;
import com.ProjectForBNYM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        List<UserProfile> response = userService.getAllProfile();
        return ResponseEntity.ok().body(response);
    }

    // get employee profile by ID
    //ResponseeEntity gives http status update 
    @GetMapping(PROFILE + "/get-profile-by-id")
    public ResponseEntity<UserProfile> getByID(@RequestParam String profile_id) {
        UserProfile response = userService.getByID(profile_id);
        return ResponseEntity.ok().body(response);
   }

   //get employee profile by name
   @GetMapping(PROFILE + "/name")
    public ResponseEntity<List<UserProfile>> getByName(@RequestParam String name){
       List<UserProfile> response = userService.getByName(name);
        return ResponseEntity.ok().body(response);
    }

    //get employee profile by skill
    @GetMapping(PROFILE + "/skill")
    public ResponseEntity<List<UserProfile>> getBySkill(@RequestParam String skills){
        List<UserProfile> response = userService.getBySkill(skills);
        return ResponseEntity.ok().body(response);
    }

    //insert employee details
    //use @RequestBody when working with a object(UserProfile) rather than requesting one value (@RequestParam)
    @PostMapping(PROFILE + "/saveProfile")
    public ResponseEntity<UserProfile> saveProfile(@RequestBody UserProfile userProfile) {
            UserProfile response = userService.saveUserProfile(userProfile);
            return ResponseEntity.ok().body(response);
        }

    @DeleteMapping(PROFILE + "/delete")
    public ResponseEntity<String> deleteById(@RequestParam String profileId){
        userService.deleteById(profileId);
        return ResponseEntity.ok().body(profileId + " Deleted");
    }

}
