package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A class that contains a list of songs and various functionalities that allow comfortable work.
 *
 * 
 */
public class AudioPlayer {
    private static final int ONE_SECOND_IN_MILLISECONDS = 1000;
    private List<Song> songs;
    private BufferedReader bufferedReader;
    private Song currentSong;

    public AudioPlayer(Song... songs) {
        this.songs = new ArrayList<>();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Collections.addAll(this.songs, songs);
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public String play() throws InterruptedException, IOException {
        songs.sort(Comparator.comparing(Song::getTitle));
        return playImpl(0);
    }

    public String playLastListened() throws InterruptedException, IOException {
        int index = songs.indexOf(currentSong);
        return playImpl(index);
    }

    public String playShuffle() throws IOException, InterruptedException {
        Collections.shuffle(songs);
        return playImpl(0);
    }

    private String playImpl(int index) throws InterruptedException, IOException {
        for (int i = index; i < songs.size(); i++) {
            currentSong = songs.get(i);
            System.out.println("Now playing: " + currentSong.getDetails());

            int songTiming = currentSong.getLengthInSeconds();
            for (int j = 0; j < songTiming; j++) {
                Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
                String checked = checkInput();
                if (checked != null) {
                    return checked;
                }
            }
        }
        return "Play";
    }

    public String pause() throws IOException {
        System.out.println("Paused..");
        return getInput();
    }

    public String stop() throws IOException {
        System.out.println("Player stopped");
        currentSong = null;
        return getInput();
    }

    public String next() throws IOException {
        int index = songs.indexOf(currentSong) + 1;

        if (index >= songs.size()) {
            currentSong = songs.get(0);
        } else {
            currentSong = songs.get(index);
        }
        return getInput();
    }

    public String prev() throws IOException {
        int index = songs.indexOf(currentSong) - 1;

        if (index < 0) {
            currentSong = songs.get(songs.size() - 1);
        } else {
            currentSong = songs.get(index);
        }
        return getInput();
    }

    public String addSong(Song song) throws IOException {
        songs.add(song);
        return getInput();
    }

    public String removeSong(int songNumber) throws IOException {
        if (validateSongNumber(songNumber - 1)) {
            System.out.println("There is no such song! Enter again:");
            return getInput();
        }

        songs.remove(songNumber - 1);
        return getInput();
    }

    private boolean validateSongNumber(int songNumber) {
        return songNumber < 0 || songNumber >= songs.size();
    }

    public String info() throws IOException {
        int songNumber = songs.indexOf(currentSong) + 1;
        System.out.printf("%d. %s", songNumber, currentSong.getDetails());
        return getInput();
    }

    private String checkInput() throws IOException {
        return bufferedReader.ready() ? bufferedReader.readLine() : null;
    }

    private String getInput() throws IOException {
        while (true) {
            String checked = checkInput();
            if (checked != null) {
                return checked;
            }
        }
    }

    public String findPerformerByTitle(String title) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equalsIgnoreCase(title)) {
                return String.format("Song number: %d\nAuthor: %s", i, songs.get(i).getPerformerName());
            }
        }
        return "There is no found song";
    }

    public String findSongsByPerformer(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            if (song.getPerformerName().equalsIgnoreCase(name)) {
                stringBuilder.append(song.getTitle())
                        .append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }

    public int getSongsNumber() {
        return songs.size() - 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            stringBuilder.append(song).append("\n\n");
        }
        return stringBuilder.toString().trim();
    }
}
