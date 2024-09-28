package com.example.findmyproject.service;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ProjectJpaRepository;
import com.example.findmyproject.repository.ProjectRepository;
import com.example.findmyproject.repository.ResearcherJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectJpaService implements ProjectRepository {
    @Autowired
    private ProjectJpaRepository projectJpaRepository;
    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Override
    public List<Project> getProjects() {
        return projectJpaRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
        List<Integer> researcherIds = new ArrayList<>();
        for (Researcher researcher : project.getResearchers()) {
            researcherIds.add(researcher.getResearcherId());
        }

        List<Researcher> researchers = researcherJpaRepository.findAllById(researcherIds);
        if (researcherIds.size() != researchers.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        project.setResearchers(researchers);

        return projectJpaRepository.save(project);
    }

    @Override
    public Project getProject(int id) {
        return projectJpaRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Project updateProject(int projectId, Project project) {

        Project newProject = projectJpaRepository.findById(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        if (project.getProjectName() != null) {
            newProject.setProjectName(project.getProjectName());
        };
        if (project.getBudget() != 0) {
            newProject.setBudget(project.getBudget());
        }
        if (project.getResearchers() != null) {
            List<Integer> researcherIds = new ArrayList<>();
            for (Researcher researcher : project.getResearchers()) {
                researcherIds.add(researcher.getResearcherId());
            }

            List<Researcher> newResearchers = researcherJpaRepository.findAllById(researcherIds);
            if (researcherIds.size() != newResearchers.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of Researchers are not Found");
            }
            newProject.setResearchers(newResearchers);
        }
        return projectJpaRepository.save(newProject);

    }

    @Override
    public void deleteProject(int id) {
        try {
            projectJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Researcher> getProjectResearchers(int projectId) {
        Project project = projectJpaRepository.findById(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return project.getResearchers();
    }
}