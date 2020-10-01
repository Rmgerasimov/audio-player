package models;

import exceptions.IncorrectParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class who represent person who write and perform certain song.
 * The class Author contains List with his songs as well as his name and age.
 */
public class Author {
    private final String name;
    private List<Song> songs;
    private int age;

    public Author(String name, int age) throws IncorrectParameters {
        validateParameters(name, age);

        this.name = name;
        this.age = age;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void validateParameters(String name, int age) throws IncorrectParameters {
        if (!validateName(name)) {
            throw new IncorrectParameters("Invalid author name!");
        }

        if (!validateAge(age)) {
            throw new IncorrectParameters("Invalid author age!");
        }
    }

    private boolean validateName(String title) {
        return title != null && !title.trim().isEmpty();
    }

    private boolean validateAge(int age) {
        return age >= 14 && age <= 100;
    }
}
