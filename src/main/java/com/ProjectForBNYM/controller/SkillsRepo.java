package com.ProjectForBNYM.controller;


import com.ProjectForBNYM.model.SkillsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepo extends JpaRepository<SkillsModel, String> {

    SkillsModel getSkillById(String id);
    SkillsModel getSkillBySkillName(String skillName);

    void deleteById(String id);
}
