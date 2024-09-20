package com.example.findmyproject.service;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ResearcherJpaRepository;
import com.example.findmyproject.repository.ResearcherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearcherJpaService implements ResearcherRepository {
    private ResearcherJpaRepository researcherJpaRepository;
    @Override
    public List<Researcher> getResearchers() {
        return researcherJpaRepository.findAll();
    }

    @Override
    public Researcher addResearcher(Researcher researcher) {
        return null;
    }

    @Override
    public Researcher getResearcher(int id) {
        return null;
    }

    @Override
    public Researcher updateResearcher(int researcherId, Researcher researcher) {
        return null;
    }

    @Override
    public void deleteResearcher(int id) {

    }

    @Override
    public List<Project> getResearcherProjects(int researcherId) {
        return null;
    }
}