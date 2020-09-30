package models;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private final String name;
    private List<Song> songs;
    private int age;

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
