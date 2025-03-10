package com.starwarsapis.starwarscharacters.model;

import java.util.List;

public class CharacterDTO {
    private String name;
    private String height;
    private String mass;
    private String gender;
    private List<String> films; // Lista de URLs de películas

    // Constructor
    public CharacterDTO(String name, String height, String mass, String gender, List<String> films) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.gender = gender;
        this.films = films;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }
}