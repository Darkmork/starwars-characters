package com.starwarsapis.starwarscharacters.service.impl;

import com.starwarsapis.starwarscharacters.feign.FilmClient;
import com.starwarsapis.starwarscharacters.model.Character;
import com.starwarsapis.starwarscharacters.model.CharacterDTO;
import com.starwarsapis.starwarscharacters.model.CharacterResponse;
import com.starwarsapis.starwarscharacters.model.Film;
import com.starwarsapis.starwarscharacters.model.CharacterWithFilmsDTO;
import com.starwarsapis.starwarscharacters.service.StarWarsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StarWarsServiceImpl implements StarWarsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://swapi.dev/api/";
    private static final Logger logger = LoggerFactory.getLogger(StarWarsServiceImpl.class);


    private final FilmClient filmClient; // Feign Client para el microservicio de películas

    // Método para obtener un personaje por ID
    @Override
    public CharacterDTO getCharacterById(int id) {
        String url = API_URL + "people/" + id + "/";
        logger.info("Haciendo solicitud a la URL: {}", url);
        try {
            Character character = restTemplate.getForObject(url, Character.class);
            logger.info("Respuesta recibida: {}", character);
            return mapToDTO(character);
        } catch (Exception e) {
            logger.error("Error al hacer la solicitud: {}", e.getMessage());
            return null;
        }
    }

    // Método para buscar personajes por nombre (maneja paginación)
    @Override
    public List<CharacterDTO> getCharactersByName(String name) {
        List<CharacterDTO> characters = new ArrayList<>();
        String url = API_URL + "people/";
        logger.info("Haciendo solicitud a la URL: {}", url);

        try {
            while (url != null) {
                CharacterResponse response = restTemplate.getForObject(url, CharacterResponse.class);
                if (response != null && response.getResults() != null) {
                    // Filtrar los personajes por nombre y mapear a DTO
                    characters.addAll(response.getResults().stream()
                            .filter(character -> character.getName().toLowerCase().contains(name.toLowerCase()))
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()));
                }
                // Obtener la URL de la siguiente página
                url = response != null ? response.getNext() : null;
            }
        } catch (Exception e) {
            logger.error("Error al hacer la solicitud: {}", e.getMessage());
        }
        return characters;
    }

    // Método para buscar personajes por género (maneja paginación)
    @Override
    public List<CharacterDTO> getCharactersByGender(String gender) {
        List<CharacterDTO> characters = new ArrayList<>();
        String url = API_URL + "people/";
        logger.info("Haciendo solicitud a la URL: {}", url);

        try {
            while (url != null) {
                CharacterResponse response = restTemplate.getForObject(url, CharacterResponse.class);
                if (response != null && response.getResults() != null) {
                    // Filtrar los personajes por género y mapear a DTO
                    characters.addAll(response.getResults().stream()
                            .filter(character -> character.getGender().equalsIgnoreCase(gender))
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()));
                }
                // Obtener la URL de la siguiente página
                url = response != null ? response.getNext() : null;
            }
        } catch (Exception e) {
            logger.error("Error al hacer la solicitud: {}", e.getMessage());
        }
        return characters;
    }

    // Método para obtener un personaje con información de películas
    @Override
    public CharacterWithFilmsDTO getCharacterWithFilms(int id) {
        // Obtener el personaje por ID
        CharacterDTO characterDTO = getCharacterById(id);
        if (characterDTO == null) {
            return null; // Si no se encuentra el personaje, devolver null
        }

        // Obtener los detalles de las películas
        List<Film> films = new ArrayList<>();
        if (characterDTO.getFilms() != null) {
            for (String filmUrl : characterDTO.getFilms()) {
                int filmId = extractFilmIdFromUrl(filmUrl); // Extraer el ID de la película desde la URL
                Film film = filmClient.getFilmById(filmId); // Obtener los detalles de la película
                if (film != null) {
                    films.add(film);
                }
            }
        }

        // Crear y devolver el DTO combinado
        return new CharacterWithFilmsDTO(
                characterDTO.getName(),
                characterDTO.getHeight(),
                characterDTO.getMass(),
                characterDTO.getGender(),
                films
        );
    }

    // Método para extraer el ID de la película desde la URL
    private int extractFilmIdFromUrl(String filmUrl) {
        String[] parts = filmUrl.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    // Método para mapear un Character a un CharacterDTO
    private CharacterDTO mapToDTO(Character character) {
        return new CharacterDTO(
                character.getName(),
                character.getHeight(),
                character.getMass(),
                character.getGender(),
                character.getFilms() // Lista de URLs de películas
        );
    }
}
