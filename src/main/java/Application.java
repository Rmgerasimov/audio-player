import exceptions.IncorrectParameters;
import models.AudioPlayer;
import models.Author;
import models.Song;

import java.util.Scanner;

import static models.enums.Genre.*;

public class Application {
    public static void main(String[] args) throws IncorrectParameters, InterruptedException {

        Song theBestSong = new Song("The best song", new Author("Ivan Ivanovski", 18), RAP, 10);
        Song rado = new Song("Rado - vecheryai", new Author("Petkan Divaka", 55), TECHNO, 15);
        Song pardop = new Song("Pardop", new Author("Just Magda", 42), METAL, 300);

        AudioPlayer audioPlayer = new AudioPlayer(theBestSong);
//        audioPlayer.play();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int index = 0;
        while (!input.equalsIgnoreCase("Exit")) {
            switch (input) {
                case "Play":
                    audioPlayer.playMusic(index);
                    break;
                case "Stop":
                    index = 0;
                    audioPlayer.playMusic(index);
                    break;
                case "Pause":
                    audioPlayer.pause();
                    break;
                case "Next song":
                    index++;
                    audioPlayer.playMusic(index);
                    break;
                case "Previous song":
                    index--;
                    audioPlayer.playMusic(index);
                    break;
                case "Add song":
                    audioPlayer.addSong(rado);
                    break;
                case "Remove song":
                    audioPlayer.removeSong(rado);
                    break;
                case "Info":
                    break;
            }
            input = scanner.nextLine();
        }

    }
}
