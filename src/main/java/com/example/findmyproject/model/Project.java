package com.example.findmyproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @Column(name = "name")
    private String projectName;
    @Column(name = "budget")
    private double budget;

    @ManyToMany
    @JoinTable(
            name = "project_researcher",
            joinColumns = @JoinColumn(name = "projectid"),
            inverseJoinColumns = @JoinColumn(name = "researcherid")
    )
    @JsonIgnoreProperties("projects")
    private List<Researcher> researchers = new ArrayList<>();
}
