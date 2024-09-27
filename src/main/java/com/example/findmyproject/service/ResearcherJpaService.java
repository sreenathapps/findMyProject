package com.example.findmyproject.service;

import com.example.findmyproject.model.*;
import com.example.findmyproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ResearcherJpaService implements ResearcherRepository {
    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Override
    public List<Researcher> getResearchers() {
        return researcherJpaRepository.findAll();
    }

    @Override
    public Researcher addResearcher(Researcher researcher) {
        List<Integer> projectIds = new ArrayList<>();
        for(Project project : researcher.getProjects()){
            projectIds.add(project.getProjectId());
        }
        
        List<Project> projects = projectJpaRepository.findAllById(projectIds);
        if (projects.size() != projectIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        researcher.setProjects(projects);

        for(Project proj: projects) {
            proj.getResearchers().add(researcher);
        }

        Researcher researcher2 = researcherJpaRepository.save(researcher);
        projectJpaRepository.saveAll(projects);

        return researcher2;
    }

    @Override
    public Researcher getResearcher(int id) {
        return researcherJpaRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Researcher updateResearcher(int researcherId, Researcher researcher) {
        try {
            Researcher newResearcer = researcherJpaRepository.findById(researcherId).get();

            if (researcher.getResearcherName() != null) {
                newResearcer.setResearcherName(researcher.getResearcherName());
            }
            if (researcher.getSpecialization() != null) {
                newResearcer.setSpecialization(researcher.getSpecialization());
            }
            if (researcher.getProjects()!= null) {
                List<Project> projects = newResearcer.getProjects();
                for(Project prj: projects) {
                    prj.getResearchers().remove(newResearcer);
                }
                projectJpaRepository.saveAll(projects);

                List<Integer> projectIds = new ArrayList<>();
                for(Project proj: researcher.getProjects()) {
                    projectIds.add(proj.getProjectId());
                }
                
                List<Project> newProjects = projectJpaRepository.findAllById(projectIds);
                if (projects.size() != projectIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                for (Project proj : newProjects) {
                    proj.getResearchers().add(newResearcer);
                }
                newResearcer.setProjects(newProjects);

            }
            return researcherJpaRepository.save(newResearcer);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteResearcher(int id) {
        try {
            Researcher researcher = researcherJpaRepository.findById(id).get();

            List<Project> projects = researcher.getProjects();

            for(Project project: researcher.getProjects()) {
                project.getResearchers().remove(researcher);
            }
            projectJpaRepository.saveAll(projects);

            researcherJpaRepository.deleteById(id);
            
        } catch (Exception e) {
            new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Project> getResearcherProjects(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            return researcher.getProjects();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}