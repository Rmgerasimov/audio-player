package models;

/**
 * An enum class that represents the genre (music style) of the song.
 */
public enum Genre {
    RAP("Rap"),
    POP("Pop"),
    METAL("Metal"),
    TECHNO("Techno"),
    FOLKLORE("Uli4en folklor"),
    OTHER("Other");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}