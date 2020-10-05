package models;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class that represents a person who performs songs.
 * The class Performer contains a List with his songs as well as his name and age.
 */
public class Performer {
    private final String name;
    private List<Song> songs;
    private int age;

    public Performer(String name, int age) {
        if (validateName(name)) {
            name = "Nameless";
        }
        if (validateAge(age)) {
            age = 0;
        }

        this.name = name;
        this.age = age;
        this.songs = new ArrayList<>();
    }

    public Performer(String name) {
        if (validateName(name)) {
            name = "Nameless";
        }
        this.name = name;
        this.age = 0;
        this.songs = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    /**
     * @return true if performer name is invalid
     */
    private boolean validateName(String name) {
        return name == null || name.trim().isEmpty();
    }

    /**
     * @return true if performer age is invalid
     */
    private boolean validateAge(int age) {
        return age < 0 || age > 100;
    }
}
