package com.starwarsapis.starwarscharacters.feign;

import com.starwarsapis.starwarscharacters.model.Film;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "film-service", url = "http://localhost:8081")
public interface FilmClient {

    @GetMapping("/api/films/{id}")
    Film getFilmById(@PathVariable int id);
}
