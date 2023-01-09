package com.ProjectForBNYM.controller;

import com.ProjectForBNYM.model.SkillsModel;
import com.ProjectForBNYM.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SkillsController {
    @Autowired
    SkillsService skillsService;

    private static final String SKILL = "/skill";

    @PostMapping(SKILL + "/add")
    public ResponseEntity<SkillsModel> saveAddedSkill(@RequestBody SkillsModel skillsModel) {
        SkillsModel addedSkill = skillsService.saveAddedSkill(skillsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedSkill);
    }

    @DeleteMapping(SKILL + "/delete")
    public ResponseEntity<String> deleteById(@RequestParam String id){
        skillsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(""); //no content status update doesn't allow body messages
    }


}

