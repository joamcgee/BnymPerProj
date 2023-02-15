package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.ExceptionHandling.ResourceNotFoundException;
import com.ProjectForBNYM.model.UserProfile;
import com.ProjectForBNYM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
        try {Optional<UserProfile> response = userService.getByID(profile_id);
        return ResponseEntity.ok().body(response.get());
        }  catch (Exception e) {
            throw new ResourceNotFoundException("No profile details found for " + profile_id);
        }
   }


   //get employee profile by name
   @GetMapping(PROFILE + "/name")
    public ResponseEntity<List<UserProfile>> getByName(@RequestParam String name){
       try {List<UserProfile> response = userService.getByName(name);
        return ResponseEntity.ok().body(response);
       }  catch (Exception e) {
           throw new ResourceNotFoundException("No profile details found for " + name );
       }
    }

    //get employee profile by skill
    @GetMapping(PROFILE + "/skill")
    public ResponseEntity<List<UserProfile>> getBySkill(@RequestParam String skillName){
       try { List<UserProfile> response = userService.getBySkill(skillName);
        return ResponseEntity.ok().body(response);
       }  catch (Exception e) {
           throw new ResourceNotFoundException("No profile details found for " + skillName);
       }
    }

    //insert employee details
    //use @RequestBody when working with a object(UserProfile) rather than requesting one value (@RequestParam)
    @PostMapping(PROFILE + "/saveProfile")
    public ResponseEntity<UserProfile> saveProfile(@RequestBody UserProfile userProfile) {
        try{
            UserProfile response = userService.saveUserProfile(userProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
          //  throw new UserProfileException ();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserProfile());
        }

            //Create a try-catch exception handling using controller advise using springboot
                // message should have timestamp and entity error message
        }

    @DeleteMapping(PROFILE + "/delete")
    public ResponseEntity<String> deleteById(@RequestParam String Id){
        userService.deleteById(Id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(""); //no content status update doesn't body messages
    }

}
