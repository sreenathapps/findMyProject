package com.example.findmyproject.repository;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearcherRepository {
    List<Researcher> getResearchers();
    Researcher addResearcher(Researcher researcher);
    Researcher getResearcher(int id);
    Researcher updateResearcher(int researcherId, Researcher researcher);
    void deleteResearcher(int id);
    List<Project> getResearcherProjects(int researcherId);
}