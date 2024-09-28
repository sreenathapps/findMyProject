package com.example.findmyproject.controller;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.service.ResearcherJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResearcherController {
    @Autowired
    private ResearcherJpaService researcherJpaService;

    @GetMapping("/researchers")
    public List<Researcher> getResearchers() {
        return researcherJpaService.getResearchers();
    }
    @PostMapping("/researchers")
    public Researcher addResearcher(@RequestBody Researcher researcher) {
        return researcherJpaService.addResearcher(researcher);
    }
    @GetMapping("/researchers/{researcherId}")
    public Researcher getResearcher(@PathVariable int researcherId) {
        return researcherJpaService.getResearcher(researcherId);
    }
    @PutMapping("/researchers/{researchersId}")
    public Researcher updateResearcher(@PathVariable("researchersId") int researcherId, @RequestBody Researcher researcher) {
        return researcherJpaService.updateResearcher(researcherId, researcher);
    }
    @DeleteMapping("/researchers/{id}")
    public void deleteResearcher(@PathVariable int id) {
        researcherJpaService.deleteResearcher(id);
    }
    @GetMapping("/researchers/{researcherId}/projects")
    public List<Project> getResearcherProjects(@PathVariable int researcherId) {
        return researcherJpaService.getResearcherProjects(researcherId);
    }
}