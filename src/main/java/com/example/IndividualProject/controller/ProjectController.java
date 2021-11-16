package com.example.IndividualProject.controller;

import com.example.IndividualProject.model.Project;
import com.example.IndividualProject.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @GetMapping("/hello")
    @ResponseBody
    public String SayHello() {
        return "Hello, your resources work!";
    }

    @GetMapping()
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();

        if (projects != null) {
            return ResponseEntity.ok().body(projects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable(value = "id") String id) {
        Project projectApp = projectService.getProjectById(id);

        if (projectApp != null) {
            return ResponseEntity.ok().body(projectApp);
        } else {
            return new ResponseEntity("Project with ID " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project created = projectService.addProject(project);

        if (created == null) {
            String entity = "Project with ID " + project.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }

        String url = "project" + "/" + created.getId();
        URI uri = URI.create(url);
        return new ResponseEntity(uri, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable("id") String id, @RequestBody Project project) {
        Project old = projectService.getProjectById(id);

        if (old == null) {
            return new ResponseEntity("Project not found.", HttpStatus.NOT_FOUND);
        }

        old.setTitle(project.getTitle());
        old.setEmployer(project.getEmployer());
        old.setDescription(project.getDescription());

        Project updated = projectService.updateProject(old);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProjectById(@PathVariable String id) {
        Project project = projectService.getProjectById(id);

        if(project == null){
            return new ResponseEntity("Project not found.", HttpStatus.NOT_FOUND);
        }

        projectService.deleteProjectById(id);
        return ResponseEntity.ok().build();
    }
}
