package com.example.TopPMSAPI;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

	@GetMapping(value="/customer", produces=MediaType.APPLICATION_JSON_VALUE)
	public String customer() {
		return "Vasyl";
	}

	private ProjectRepository repository;

    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

	@GetMapping(value="/cool-projects", produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
    public Collection<Project> coolProjects() {
        return repository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/projects/create", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public void createProject(@RequestBody Project postProject) {
        Project project = new Project();
        project.setName(postProject.getName());
        project.setOwner(postProject.getOwner());
        project.setType(postProject.getType());
        this.repository.save(project);
    }

    private boolean isCool(Project project) {
        return !project.getName().equals("AMC Gremlin") &&
                !project.getName().equals("Triumph Stag") &&
                !project.getName().equals("Ford Pinto") &&
                !project.getName().equals("Yugo GV");
    }
}