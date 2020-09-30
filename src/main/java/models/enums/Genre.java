package models.enums;

/**
 * A class who represent the type of the song.
 */
public enum Genre {
    RAP("Rap"),
    POP("Pop"),
    METAL("Metal"),
    TECHNO("Techno");

    private String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}