package com.starwarsapis.starwarscharacters.service;

import com.starwarsapis.starwarscharacters.model.CharacterDTO;
import com.starwarsapis.starwarscharacters.model.CharacterWithFilmsDTO;

import java.util.List;

public interface StarWarsService {
    List<CharacterDTO> getCharactersByName(String name);

    List<CharacterDTO> getCharactersByGender(String gender);

    CharacterWithFilmsDTO getCharacterWithFilms(int id);

    CharacterDTO getCharacterById(int id);
}
