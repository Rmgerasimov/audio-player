import exception.IncorrectParametersException;
import models.AudioPlayer;
import models.Genre;
import models.Performer;
import models.Song;

import java.io.IOException;
import java.util.Scanner;

import static models.Genre.*;

public class Application {
    public static void main(String[] args) throws IncorrectParametersException, InterruptedException, IOException {
        printCommands();

        AudioPlayer audioPlayer = getAudioPlayer();
        startApplication(audioPlayer);

        System.out.println("Turning off...");
    }

    private static void printCommands() {
        String stringBuilder = "Welcome!\n" +
                "Our commends are: \n" +
                "Play, Play last, Play shuffle,\nPause, Stop, Next, Prev,\nAdd song, Remove song," +
                "\nFind performer by title,\nFind songs by performer,\nGet playlist size," +
                "\nInfo, Exit\n" +
                "Enter your commands here: ";
        System.out.println(stringBuilder);
    }

    /**
     * A method that represents the engine of the application.
     *
     * @throws InterruptedException
     * @throws IOException
     * @throws IncorrectParametersException
     */
    private static void startApplication(AudioPlayer audioPlayer)
            throws InterruptedException, IOException, IncorrectParametersException {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("Exit")) {
            switch (input) {
                case "Play":
                    input = audioPlayer.play();
                    break;
                case "Play last":
                    if (checkCurrentSong(audioPlayer)) {
                        audioPlayer.play();
                    }
                    input = audioPlayer.playLastListened();
                    break;
                case "Play shuffle":
                    input = audioPlayer.playShuffle();
                    break;
                case "Stop":
                    if (checkCurrentSong(audioPlayer)) {
                        System.out.println("There is no current song for stopping! Enter again: ");
                        input = scanner.nextLine();
                        break;
                    }
                    input = audioPlayer.stop();
                    break;
                case "Pause":
                    if (checkCurrentSong(audioPlayer)) {
                        System.out.println("There is no current song for pausing! Enter again: ");
                        input = scanner.nextLine();
                        break;
                    }
                    input = audioPlayer.pause();
                    break;
                case "Next":
                    input = audioPlayer.next();
                    break;
                case "Prev":
                    input = audioPlayer.prev();
                    break;
                case "Add song":
                    System.out.println("Enter the song data in order: title, performer name, genre, lengthInSeconds");
                    String[] songInfo = scanner.nextLine().split(", ");

                    Song song = new Song(songInfo[0], new Performer(songInfo[1]),
                            Genre.valueOf(songInfo[2]), Integer.parseInt(songInfo[3]));
                    input = audioPlayer.addSong(song);
                    break;
                case "Remove song":
                    System.out.println("Enter the number of the song: ");
                    int songNumber = Integer.parseInt(scanner.nextLine());
                    input = audioPlayer.removeSong(songNumber);
                    break;
                case "Info":
                    if (checkCurrentSong(audioPlayer)) {
                        System.out.println("There is no current song");
                        input = scanner.nextLine();
                        break;
                    }
                    input = audioPlayer.info();
                    break;
                case "Find performer by title":
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.println(audioPlayer.findPerformerByTitle(title));
                    input = scanner.nextLine();
                    break;
                case "Find songs by performer":
                    System.out.println("Enter performer: ");
                    String performerName = scanner.nextLine();
                    System.out.println(audioPlayer.findSongsByPerformer(performerName));
                    input = scanner.nextLine();
                    break;
                case "Get playlist size":
                    System.out.printf("Song's number: %d\n", audioPlayer.getSongsNumber());
                    input = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command! Enter again: ");
                    input = scanner.nextLine();
                    break;
            }
        }
    }

    /**
     * Creates Song objects.
     *
     * @return new AudioPlayer
     * @throws IncorrectParametersException
     */
    private static AudioPlayer getAudioPlayer() throws IncorrectParametersException {
        Song theBestSong = new Song("The best song", new Performer("Ivan Ivanovski", 18), RAP, 10);
        Song rado = new Song("Rado - vecheryai", new Performer("Petkan Divaka", 55), TECHNO, 15);
        Song pardop = new Song("Pardop", new Performer("Just Magda", 42), METAL, 180);
        return new AudioPlayer(theBestSong, rado, pardop);
    }

    /**
     * @return true if current is null
     */
    private static boolean checkCurrentSong(AudioPlayer audioPlayer) {
        return audioPlayer.getCurrentSong() == null;
    }
}
