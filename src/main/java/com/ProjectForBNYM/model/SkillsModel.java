package com.ProjectForBNYM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skills_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillsModel {
//    @ManyToMany (mappedBy = "skills",fetch = FetchType.LAZY)
//    @JoinColumn (name = "UsSk_fk", referencedColumnName = "profileId")
//    private List<UserProfile> userProfiles;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    private String skillName;

}
