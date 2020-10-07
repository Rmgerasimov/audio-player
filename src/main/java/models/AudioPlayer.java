package models;

import exception.IncorrectParametersException;

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
    private Song currentSong;
    private final List<Song> songs;
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public AudioPlayer(Song... songs) {
        this.songs = new ArrayList<>(Arrays.asList(songs));
    }

    /**
     * A method that plays songs sorted by title.
     *
     * @return command if the user enters a command,
     * else returns the command "Play" and re-enters this method.
     * @throws InterruptedException
     */
    public String play() throws InterruptedException, IOException {
        songs.sort(Comparator.comparing(Song::getTitle));
        return playCommandImpl(0, "Play");
    }

    /**
     * А method that plays songs starting with the last one that was listened to.
     *
     * @return command if the user enters a command,
     * else returns the command "Play" and enter in play() method.
     * @throws InterruptedException
     */
    public String playLastListened() throws InterruptedException, IOException {
        if (currentSong == null) {
            play();
        }
        return playCommandImpl(songs.indexOf(currentSong), "Play");
    }

    /**
     * А method that plays songs in shuffle mode.
     *
     * @return command if the user enters a command,
     * else returns the command "Play shuffle" and re-enters this method.
     * @throws InterruptedException
     */
    public String playShuffle() throws InterruptedException, IOException {
        Collections.shuffle(songs);
        return playCommandImpl(0, "Play shuffle");
    }

    /**
     * A method that represents the "Play" commands implementation.
     *
     * @param index that represents the index of a song in the list.
     * @param nextCommand that represents the command that will be returned
     *                    if the user doesn't enter a command.
     * @return next command.
     * @throws InterruptedException
     */
    private String playCommandImpl(int index, String nextCommand) throws InterruptedException, IOException {
        for (int i = index; i < songs.size(); i++) {
            currentSong = songs.get(i);
            System.out.println("Now playing: " + currentSong.getDetails());

            for (int j = 0; j < currentSong.getLengthInSeconds(); j++) {
                Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
                if (bufferedReader.ready()) {
                    return bufferedReader.readLine();
                }
            }
        }
        return nextCommand;
    }

    /**
     * A method that pause playlist,
     * then waits for other command.
     *
     * @return text that will be print on the console.
     */
    public String pause() {
        if (currentSong == null) {
            return "There is no current song for pausing! Enter new command:";
        } else {
            return "Paused..";
        }
    }

    /**
     * A method that stop playlist,
     * then waits for other command.
     *
     * @return text that will be print on the console.
     */
    public String stop() {
        if (currentSong == null) {
            return "There is no current song for stopping! Enter new command:";
        } else {
            currentSong = null;
            return "Player stopped!";
        }
    }

    /**
     * A method that plays the next song in the playlist.
     * @return text that will be print on the console.
     */
    public String next() {
        int nextSongIndex = songs.indexOf(currentSong) + 1;
        currentSong = nextSongIndex >= songs.size() ? songs.get(0) : songs.get(nextSongIndex);
        return "Current song: " + currentSong.getDetails();
    }

    /**
     * A method that plays the previous song in the playlist.
     * @return text that will be print on the console.
     */
    public String prev() {
        int prevSongIndex = songs.indexOf(currentSong) - 1;
        currentSong = prevSongIndex < 0 ? songs.get(songs.size() - 1) : songs.get(prevSongIndex);
        return "Current song: " + currentSong.getDetails();
    }

    /**
     * A method that adds a song to the playlist.
     *
     * @param songInput information about new song object
     * @return text that will be print on the console.
     */
    public String addSong(String[] songInput) {
        if (songInput.length != 5) {
            throw new IncorrectParametersException("Parameters must be exactly five!");
        }

        Song song = new Song(
                new Performer(songInput[0], Integer.parseInt(songInput[1])),
                songInput[2], songInput[3], Integer.parseInt(songInput[4]));
        songs.add(song);
        return "The song is added!";
    }

    /**
     * @param songNumber number of the song in the playlist that will be removed.
     * @return text that will be print on the console.
     */
    public String removeSong(int songNumber) throws IOException {
        songs.remove(songNumber - 1);
        return "The song is removed!";
    }

    /**
     * A method that return song's number, performer's name and song's title.
     * @return text that will be print on the console.
     */
    public String info() {
        if (currentSong == null) {
            return "There is no current song! Enter new command:";
        } else {
            int songNumber = songs.indexOf(currentSong) + 1;
            return songNumber + ". " + currentSong.getDetails();
        }
    }

    /**
     * A method that find performer by given song title.
     *
     * @param title that represent song's title.
     * @return song's number and performer's name if the song is valid.
     */
    public String findPerformerByTitle(String title) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).checkSongTitle(title)) {
                return "Song number: " + (i + 1) +
                        "\nAuthor: " + songs.get(i).getPerformerName();
            }
        }
        return "There is no found song";
    }

    /**
     * A method that find songs by given performer name.
     *
     * @param name that represent performer's name
     * @return all songs by a given performer.
     */
    public String findSongsByPerformer(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            if (song.checkPerformerName(name)) {
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
     * @return all information about songs.
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
