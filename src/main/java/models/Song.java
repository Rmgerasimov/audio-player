package models;

import exception.IncorrectParametersException;

/**
 * A class that represent musical composition intended to be performed by the performer.
 *
 * The Song has methods for validation about title and length,
 * also methods for getting the information about the song.
 */
public class Song {
    private final String title;
    private final Performer performer;
    private final Genre genre;
    private final int lengthInSeconds;

    public Song(String title, Performer performer, Genre genre, int lengthInSeconds) throws IncorrectParametersException {
        title = validateParameters(title, lengthInSeconds);

        this.title = title;
        this.performer = performer;
        this.genre = genre;
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

    /**
     * @param title -> represent song's title
     * @param lengthInSeconds -> represent song's length
     * @return song's title after validation
     * @throws IncorrectParametersException when length is invalid
     */
    private String validateParameters(String title, int lengthInSeconds) throws IncorrectParametersException {
        if (validateTitle(title)) {
            title = "No title";
        }
        if (validateSongLength(lengthInSeconds)) {
            throw new IncorrectParametersException("Incorrect song's length");
        }
        return title;
    }

    /**
     * @param title -> represent song's title
     * @return true if title is invalid
     */
    private boolean validateTitle(String title) {
        return title == null || title.trim().isEmpty();
    }

    /**
     * @param lengthInSeconds -> represent song's length
     * @return true if length is invalid
     */
    private boolean validateSongLength(int lengthInSeconds) {
        return lengthInSeconds <= 0 || lengthInSeconds > 3000;
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
