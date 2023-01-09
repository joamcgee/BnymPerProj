package com.ProjectForBNYM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "profile_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String Id;
    @Column
    private String employeeId;
    @Column
    public String name;
    @Column
    public String department;
    @Column
    public LocalDate dateJoined;
    @Column
    public long salary;
    @Column
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_and_Skill_table", joinColumns = @JoinColumn(name = "profileId", referencedColumnName = "Id"),
    inverseJoinColumns = @JoinColumn(name = "skillId", referencedColumnName = "Id"))
    public List<SkillsModel> skills;

}
