package com.example.findmyproject.controller;

import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.service.ProjectJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProjectController {
    @Autowired
    private ProjectJpaService projectJpaService;

    @GetMapping("researchers/projects")
    public List<Project> getProjects() {
        return projectJpaService.getProjects();
    }

    @PostMapping("researchers/projects")
    public Project addProject(@RequestBody Project project) {
        return projectJpaService.addProject(project);
    }

    @GetMapping("researchers/projects/{projectId}")
    public Project getProject(@PathVariable("projectId") int projectId) {
        return projectJpaService.getProject(projectId);
    }

    @PutMapping("researchers/projects/{projectId}")
    public Project updateProject(@PathVariable int projectId, @RequestBody Project project) {
        return projectJpaService.updateProject(projectId, project);
    }

    @DeleteMapping("researchers/projects/{projectId}")
    public void deleteProject(@PathVariable int projectId) {
        projectJpaService.deleteProject(projectId);
    }

    @GetMapping("/projects/{projectId}/researchers")
    public List<Researcher> getProjectResearchers(@PathVariable int projectId) {
        return projectJpaService.getProjectResearchers(projectId);
    }
}