package models;

import exception.IncorrectParametersException;

/**
 * A class that represent musical composition intended to be performed by the performer.
 * <p>
 * The Song has methods for validation about title and length,
 * also methods for getting the information about the song.
 */
public class Song {
    private final Performer performer;
    private final String title;
    private final Genre genre;
    private final int lengthInSeconds;

    public Song(Performer performer, String title, String genre, int lengthInSeconds) {
        validateTitle(title);
        validateGenre(genre);
        validateSongLength(lengthInSeconds);

        this.performer = performer;
        this.title = title;
        this.genre = Genre.valueOf(genre.toUpperCase());
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getPerformerName() {
        return performer.getName();
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    public boolean checkPerformerName(String name) {
        return performer.getName().equalsIgnoreCase(name);
    }

    public boolean checkSongTitle(String title) {
        return this.title.equalsIgnoreCase(title);
    }

    /**
     * @param title represent song's title.
     * @throws IncorrectParametersException when title is not valid.
     */
    private void validateTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            return;
        }
        throw new IncorrectParametersException("Incorrect title!");
    }

    /**
     * @param genre represent song's genre.
     * @throws IncorrectParametersException when genre is not valid.
     */
    private void validateGenre(String genre) {
        for (Genre currentGenre : Genre.values()) {
            if (currentGenre.getName().equalsIgnoreCase(genre)) {
                return;
            }
        }
        throw new IncorrectParametersException("Incorrect genre");
    }

    /**
     * @param lengthInSeconds represent song's duration.
     * @throws IncorrectParametersException when duration is not valid.
     */
    private void validateSongLength(int lengthInSeconds) {
        if (lengthInSeconds > 0 && lengthInSeconds <= 10_800) {
            return;
        }
        throw new IncorrectParametersException("Incorrect song's length!");
    }

    /**
     * @return A string that represents the performer name and song title.
     */
    public String getDetails() {
        return String.format("%s - %s", performer.getName(), title);
    }

    /**
     * @return A String that contains all information about the song
     */
    @Override
    public String toString() {
        return String.format("Song's author name: %s", performer.getName()) + System.lineSeparator() +
                String.format("Song's title: %s", title) + System.lineSeparator() +
                String.format("Song's genre: %s", genre.getName()) + System.lineSeparator() +
                String.format("Song's length: %d", lengthInSeconds);
    }
}
