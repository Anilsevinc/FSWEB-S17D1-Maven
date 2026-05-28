package com.workintech.fsswebs17d1.controller;

import com.workintech.fsswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals = new HashMap<>();

    // properties injection
    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    //Get
    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    //Get-id
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animals.get(id);
    }

    //Post
    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    //Put
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id,
                               @RequestBody Animal animal) {

        Animal existing = animals.get(id);

        if (existing == null) {
            return null;
        }

        existing.setId(animal.getId());
        existing.setName(animal.getName());

        animals.put(id, existing);

        return existing;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable int id) {
        return animals.remove(id);
    }
}