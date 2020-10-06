package models;

/**
 * An enum class that represents the genre (music style) of the song.
 */
public enum Genre {
    RAP("Rap"),
    POP("Pop"),
    METAL("Metal"),
    TECHNO("Techno"),
    CHALGA("Chalga"),
    OTHER("Other");

    private String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}