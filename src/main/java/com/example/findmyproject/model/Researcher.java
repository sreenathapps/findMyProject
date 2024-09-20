package com.example.findmyproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(mappedBy = "researchers")
    List<Project> projects = new ArrayList<>();

}