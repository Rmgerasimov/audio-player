package models;

import exception.IncorrectParametersException;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class that represents a person who performs songs.
 * The class Performer contains a List with his songs as well as his name and age.
 */
public class Performer {
    private List<Song> songs = new ArrayList<>();
    private final String name;
    private int age;

    public Performer(String name, int age) {
        validateName(name);
        validateAge(age);

        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    /**
     * @param name represent performer's name.
     * @throws IncorrectParametersException when name is invalid
     */
    private void validateName(String name) {
        if (name != null && name.trim().isEmpty()) {
            return;
        }
        throw new IncorrectParametersException("Invalid performer name");
    }

    /**
     * @param age represent performer's age.
     * @throws IncorrectParametersException when age is invalid.
     */
    private void validateAge(int age) {
        if (age > 0 && age <= 100) {
            return;
        }
        throw new IncorrectParametersException("Invalid performer age");
    }
}
