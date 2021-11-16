package com.example.IndividualProject.service;

import com.example.IndividualProject.model.Project;
import com.example.IndividualProject.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project addProject(Project project) {
        return projectRepository.insert(project);
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProjectById(String id){
        projectRepository.deleteById(id);
    }
}
