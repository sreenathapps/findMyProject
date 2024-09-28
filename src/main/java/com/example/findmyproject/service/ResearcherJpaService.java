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
        Researcher newResearcher = researcherJpaRepository.findById(researcherId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (researcher.getResearcherName() != null) {
            newResearcher.setResearcherName(researcher.getResearcherName());
        }
        if (researcher.getSpecialization() != null) {
            newResearcher.setSpecialization(researcher.getSpecialization());
        }
            researcherJpaRepository.save(newResearcher);
        if (researcher.getProjects() != null) {
            List<Project> projects = newResearcher.getProjects();
            for (Project prj: projects) {
                prj.getResearchers().remove(newResearcher);
            }
            projectJpaRepository.saveAll(projects);

            List<Integer> projIds = new ArrayList<>();
            for(Project p : researcher.getProjects()) {
                projIds.add(p.getProjectId());
            }

            List<Project> newProjs = projectJpaRepository.findAllById(projIds);
            if (projIds.size() != newProjs.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            for (Project prj: newProjs) {
                prj.getResearchers().add(newResearcher);
            }
            projectJpaRepository.saveAll(newProjs);
            newResearcher.setProjects(newProjs);
        }
        return  researcherJpaRepository.save(newResearcher);
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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