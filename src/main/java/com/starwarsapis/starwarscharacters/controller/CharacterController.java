package com.starwarsapis.starwarscharacters.controller;

import com.starwarsapis.starwarscharacters.model.CharacterDTO;
import com.starwarsapis.starwarscharacters.service.StarWarsService;
import com.starwarsapis.starwarscharacters.model.CharacterWithFilmsDTO;
import com.starwarsapis.starwarscharacters.service.FilmClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private StarWarsService starWarsService;

    // Endpoint para obtener un personaje por ID
    @GetMapping("/{id}")
    public CharacterDTO getCharacterById(@PathVariable int id) {
        return starWarsService.getCharacterById(id);
    }

    // Endpoint para buscar personajes por nombre
    @GetMapping("/search")
    public List<CharacterDTO> getCharactersByName(@RequestParam String name) {
        return starWarsService.getCharactersByName(name);
    }

    // Endpoint para buscar personajes por género
    @GetMapping("/searchByGender")
    public List<CharacterDTO> getCharactersByGender(@RequestParam String gender) {
        return starWarsService.getCharactersByGender(gender);
    }
    @GetMapping("/{id}/with-films")
    public CharacterWithFilmsDTO getCharacterWithFilms(@PathVariable int id) {
        return starWarsService.getCharacterWithFilms(id);
    }
}