package com.starwarsapis.starwarscharacters.model;

import java.util.List;

public class CharacterWithFilmsDTO {
    private String name;
    private String height;
    private String mass;
    private String gender;
    private List<Film> films; // Lista de objetos Film

    // Constructor
    public CharacterWithFilmsDTO(String name, String height, String mass, String gender, List<Film> films) {
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

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "CharacterWithFilmsDTO{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", gender='" + gender + '\'' +
                ", films=" + films +
                '}';
    }
}