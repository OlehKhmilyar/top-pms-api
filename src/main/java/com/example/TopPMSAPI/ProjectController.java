package com.example.TopPMSAPI;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    private boolean isCool(Project project) {
        return !car.getName().equals("AMC Gremlin") &&
                !car.getName().equals("Triumph Stag") &&
                !car.getName().equals("Ford Pinto") &&
                !car.getName().equals("Yugo GV");
    }
}