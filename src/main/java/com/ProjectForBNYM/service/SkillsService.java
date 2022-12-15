package com.ProjectForBNYM.service;

import com.ProjectForBNYM.controller.SkillsRepo;
import com.ProjectForBNYM.controller.UserRepo;
import com.ProjectForBNYM.model.SkillsModel;
import com.ProjectForBNYM.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SkillsService {
    @Autowired(required = true)
    SkillsRepo skillsRepo;

    public SkillsModel getSkillByName(String skillName) {

        return skillsRepo.getSkillBySkillName(skillName);
    }

    public SkillsModel saveAddedSkill(SkillsModel skillsModel) {

        SkillsModel addedSkill = new SkillsModel();
        addedSkill.setSkillName(skillsModel.getSkillName());

        return skillsRepo.save(addedSkill);
    }

    public void deleteById(String id) { skillsRepo.deleteById(id); }


}
