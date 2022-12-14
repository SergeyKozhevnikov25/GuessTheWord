package com.example.guesstheword;

public class Word {
    private final String name;
    private final String word;


    public Word(String name, String word) {
        this.name = name;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String getName() {
        return name;
    }
}
