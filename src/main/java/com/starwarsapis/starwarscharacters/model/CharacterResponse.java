package com.starwarsapis.starwarscharacters.model;

import java.util.List;

public class CharacterResponse {
    private List<Character> results;
    private String next; // Campo para la URL de la siguiente página

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}