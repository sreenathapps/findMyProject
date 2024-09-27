package com.example.findmyproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "researcher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int researcherId;
    @Column(name = "name")
    private String researcherName;
    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(mappedBy = "researchers")
    @JsonIgnoreProperties("researchers")
    List<Project> projects = new ArrayList<>();

}