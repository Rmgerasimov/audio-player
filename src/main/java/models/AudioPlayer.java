package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A class that contains a list of songs and various functionalities
 * that allow comfortable work with the playlist.
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


    /**
     * A method that plays songs sorted by title.
     *
     * @return command if the user enters a command,
     * else returns the command "Play" and re-enters this method.
     * @throws InterruptedException
     * @throws IOException
     */
    public String play() throws InterruptedException, IOException {
        songs.sort(Comparator.comparing(Song::getTitle));
        return playImpl(0, "Play");
    }

    /**
     * А method that plays songs starting with the last one that was listened to.
     *
     * @return command if the user enters a command,
     * else returns the command "Play" and enter in play() method.
     * @throws InterruptedException
     * @throws IOException
     */
    public String playLastListened() throws InterruptedException, IOException {
        int index = songs.indexOf(currentSong);
        return playImpl(index, "Play");
    }

    /**
     * А method that plays songs in shuffle mode.
     *
     * @return command if the user enters a command,
     * else returns the command "Play shuffle" and re-enters this method.
     * @throws IOException
     * @throws InterruptedException
     */
    public String playShuffle() throws IOException, InterruptedException {
        Collections.shuffle(songs);
        return playImpl(0, "Play shuffle");
    }


    /**
     * A method that represents the "Play" commands implementation.
     *
     * @param index       that represents the index of a song in the list.
     * @param nextCommand that represents the command that will be returned
     *                    if the user doesn't enter a command.
     * @return next command.
     * @throws InterruptedException
     * @throws IOException
     */
    private String playImpl(int index, String nextCommand) throws InterruptedException, IOException {
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
        return nextCommand;
    }

    /**
     * A method that pause playlist,
     * then waits for other command.
     *
     * @return next command.
     * @throws IOException
     */
    public String pause() throws IOException {
        System.out.println("Paused..");
        return getInput();
    }

    /**
     * A method that stop playlist,
     * then waits for other command.
     *
     * @return next command
     * @throws IOException
     */
    public String stop() throws IOException {
        System.out.println("Player stopped");
        currentSong = null;
        return getInput();
    }

    /**
     * A method that plays the next song in the playlist.
     *
     * @return next command
     * @throws IOException
     */
    public String next() throws IOException {
        int index = songs.indexOf(currentSong) + 1;

        if (index >= songs.size()) {
            currentSong = songs.get(0);
        } else {
            currentSong = songs.get(index);
        }
        return getInput();
    }


    /**
     * A method that plays the previous song in the playlist.
     *
     * @return next command
     * @throws IOException
     */
    public String prev() throws IOException {
        int index = songs.indexOf(currentSong) - 1;

        if (index < 0) {
            currentSong = songs.get(songs.size() - 1);
        } else {
            currentSong = songs.get(index);
        }
        return getInput();
    }

    /**
     * A method that adds a song to the playlist.
     *
     * @param song that will be added.
     * @return next command
     * @throws IOException
     */
    public String addSong(Song song) throws IOException {
        songs.add(song);
        return getInput();
    }


    /**
     * @param songNumber number of the song in the playlist that will be removed.
     * @return next command
     * @throws IOException
     */
    public String removeSong(int songNumber) throws IOException {
        if (validateSongNumber(songNumber - 1)) {
            System.out.println("There is no such song! Enter again:");
            return getInput();
        }

        songs.remove(songNumber - 1);
        System.out.println("The song is removed!");
        return getInput();
    }

    /**
     * @param songNumber number of the song in the playlist.
     * @return true if song's number is invalid
     */
    private boolean validateSongNumber(int songNumber) {
        return songNumber < 0 || songNumber >= songs.size();
    }

    /**
     * A method that prints song's number, performer's name and song's title.
     *
     * @return next command
     * @throws IOException
     */
    public String info() throws IOException {
        int songNumber = songs.indexOf(currentSong) + 1;
        System.out.printf("%d. %s", songNumber, currentSong.getDetails());
        return getInput();
    }

    /**
     * @return next command or null if nothing is entered by the user
     * @throws IOException
     */
    private String checkInput() throws IOException {
        return bufferedReader.ready() ? bufferedReader.readLine() : null;
    }

    /**
     * @return next command
     * @throws IOException
     */
    private String getInput() throws IOException {
        while (true) {
            String checked = checkInput();
            if (checked != null) {
                return checked;
            }
        }
    }

    /**
     * @param title that represent song's title.
     * @return song's number and performer's name if the song is valid.
     */
    public String findPerformerByTitle(String title) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equalsIgnoreCase(title)) {
                return String.format("Song number: %d\nAuthor: %s", i, songs.get(i).getPerformerName());
            }
        }
        return "There is no found song";
    }

    /**
     * @param name -> performer's name
     * @return all songs by the given performer.
     */
    public String findSongsByPerformer(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            if (song.getPerformerName().equalsIgnoreCase(name)) {
                stringBuilder.append(song.getTitle())
                        .append(System.lineSeparator());
            }
        }
        if (stringBuilder.length() == 0) {
            return "This performer does't have songs";
        }
        return stringBuilder.toString().trim();
    }

    /**
     * @return the number of songs in the playlist.
     */
    public int getSongsNumber() {
        return songs.size();
    }

    /**
     * @return all information about all songs.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            stringBuilder.append(song).append("\n\n");
        }
        return stringBuilder.toString().trim();
    }
}
