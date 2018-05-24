package com.example.TopPMSAPI;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TopPmsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopPmsApiApplication.class, args);
	}

	@Bean
    ApplicationRunner init(ProjectRepository repository) {
        return args -> {
            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
                      "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
                Project project = new Project();
                project.setName(name);
                project.setOwner("Vasyl");
                project.setType("web");
                repository.save(project);
            });
            repository.findAll().forEach(System.out::println);
        };
    }
}
