package com.ProjectForBNYM.service;

import com.ProjectForBNYM.model.SkillsModel;
import com.ProjectForBNYM.repository.SkillsRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SkillsService {
    @Autowired(required = true)
    SkillsRepo skillsRepo;

    public SkillsModel getSkillByName(String skillName) {

        return skillsRepo.getSkillBySkillName((skillName));
    }

    public SkillsModel saveAddedSkill(SkillsModel skillsModel) {
        SkillsModel record = skillsRepo.getSkillBySkillName(skillsModel.getSkillName());
        SkillsModel addedSkill = new SkillsModel();
        try {
            if (!(record == skillsModel)) {
                addedSkill.setSkillName(skillsModel.getSkillName());
                }
            } catch (Exception e) {
            System.out.println("duplicate found ");
            throw e;
        }


        return skillsRepo.save(addedSkill);
    }

    public void deleteById(String id) { skillsRepo.deleteById(id); }


}
