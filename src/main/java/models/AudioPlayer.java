package models;

import java.util.*;

public class AudioPlayer {
    private List<Song> songs;

    public AudioPlayer(Song... songs) {
        this.songs = new ArrayList<>();
        Collections.addAll(this.songs, songs);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public String playMusic(int index) {
        return "Now playing " + songs.get(index).getTitleAndAuthorName();
    }

    public String pause() {
        return "Pause";
    }
//    public void play() throws InterruptedException {
//        for (Song song : songs) { // mn zle
//            playSong(song);
//            Thread.sleep(song.getSongLengthInSeconds() * 1000);
//        }
//    }
//
//    private void playSong(Song song) {
//        System.out.println(song.toString());
//
//        AudioPlayerTimer audioPlayerTimer = new AudioPlayerTimer(song.getSongLengthInSeconds());
//        audioPlayerTimer.startTimer();

//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            stringBuilder.append(song).append("\n\n");
        }
        return stringBuilder.toString();
    }
}
