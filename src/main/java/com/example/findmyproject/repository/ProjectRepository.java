package com.example.findmyproject.repository;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository {
    List<Project> getProjects();
    Project addProject(Project project);
    Project getProject(int id);
    Project updateProject(int projectId, Project project);
    void deleteProject(int id);
    List<Researcher> getProjectResearchers(int projectId);
}