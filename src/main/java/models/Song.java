package models;

import exceptions.IncorrectParameters;
import models.enums.Genre;

public class Song {
    private final String title;
    private final Author author;
    private final Genre genre;
    private final int songLengthInSeconds; // todo -> change to seconds

    public Song(String title, Author author, Genre genre, int songLengthInSeconds) throws IncorrectParameters {
        if (!validateTitle(title)) {
            throw new IncorrectParameters("Invalid title!");
        }

//        if (!validateGenre(genre)) {
//            throw new IncorrectSongParameters("Invalid genre!");
//        }

        // todo timing validation

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.songLengthInSeconds = songLengthInSeconds;
    }

    public int getSongLengthInSeconds() {
        return songLengthInSeconds;
    }

    private boolean validateTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

//    private boolean validateGenre(Genre genre) {  // not necessary
//        for (Genre currentGenre : Genre.values()) {
//            if (currentGenre.ordinal() == genre.ordinal()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public String getTitleAndAuthorName() {
        return String.format("%s - %s\n", author.getName(), title);
    }

    @Override
    public String toString() {
        return String.format("Song's author name: %s", author.getName()) + System.lineSeparator() +
                String.format("Song's title: %s", title) + System.lineSeparator() +
                String.format("Song's genre: %s", genre.getName()) + System.lineSeparator() +
                String.format("Song's length: %d", songLengthInSeconds);
    }
}
