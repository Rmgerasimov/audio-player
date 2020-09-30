package models;

import exceptions.IncorrectSongParameters;
import models.enums.Genre;

public class Song {
    private final String title;
    private final Author author;
    private final Genre genre;
    private final String timing; // todo -> change to seconds

    public Song(String title, Author author, Genre genre, String timing) throws IncorrectSongParameters {
        if (!validateGenre(genre)) {
            throw new IncorrectSongParameters("Invalid genre!");
        }

        this.title = title;
        this.author = author;
        this.genre = genre; // todo -> check how is working
        this.timing = timing;
    }

    private boolean validateGenre(Genre genre) {
        for (Genre currentGenre : Genre.values()) {
            if (currentGenre.ordinal() == genre.ordinal()) {
                return true;
            }
        }
        return false;
    }

    // todo write method who return song title and author name

    @Override
    public String toString() {
        return String.format("Song's author name: %s", this.author.getName()) + System.lineSeparator() +
                String.format("Song's title: %s", this.title) + System.lineSeparator() +
                String.format("Song's genre: %s", this.genre.getName()) + System.lineSeparator() +
                String.format("Song's length: %s", this.timing);
    }
}
