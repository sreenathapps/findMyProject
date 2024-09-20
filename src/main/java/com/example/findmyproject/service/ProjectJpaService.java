package com.example.findmyproject.service;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ProjectJpaRepository;
import com.example.findmyproject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectJpaService implements ProjectRepository {
    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Override
    public List<Project> getProjects() {
        return projectJpaRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
        return null;
    }

    @Override
    public Project getProject(int id) {
        return null;
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        return null;
    }

    @Override
    public void deleteProject(int id) {

    }

    @Override
    public List<Researcher> getProjectResearchers(int projectId) {
        return List.of();
    }
}