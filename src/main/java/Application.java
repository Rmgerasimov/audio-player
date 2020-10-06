import exception.IncorrectParametersException;
import models.AudioPlayer;
import models.Performer;
import models.Song;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        printCommands();

        AudioPlayer audioPlayer = initAudioPlayer();
        startApplication(audioPlayer);

        System.out.println("Turning off...");
    }

    /**
     * A method that print on the console audio-player commands.
     */
    private static void printCommands() {
        String stringBuilder = "Welcome!\n" +
                "Our commends are: \n\n" +
                "Play, Play last, Play shuffle,\nPause, Stop, Next, Prev,\nAdd song, Remove song,\n" +
                "Find performer by title,\nFind songs by performer,\nGet playlist size,\n" +
                "Get all information,\n" +
                "Info, Exit\n\n" +
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
                    input = audioPlayer.playLastListened();
                    break;
                case "Play shuffle":
                    input = audioPlayer.playShuffle();
                    break;
                case "Stop":
                    input = audioPlayer.stop();
                    break;
                case "Pause":
                    input = audioPlayer.pause();
                    break;
                case "Next":
                    input = audioPlayer.next();
                    break;
                case "Prev":
                    input = audioPlayer.prev();
                    break;
                case "Add song":
                    System.out.println("Enter the song data in order: title, performer name, performer age, genre, lengthInSeconds");
                    String[] songInput = scanner.nextLine().split(", ");
                    input = audioPlayer.addSong(songInput);
                    break;
                case "Remove song":
                    System.out.println("Enter the number of the song: ");
                    int songNumber = Integer.parseInt(scanner.nextLine());

                    input = audioPlayer.removeSong(songNumber);
                    break;
                case "Info":
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
                case "Get all information":
                    System.out.println(audioPlayer);
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
     * Initializes AudioPlayer and adds Song objects in it.
     *
     * @return new AudioPlayer.
     * @throws IncorrectParametersException when parameters isn't valid.
     */
    private static AudioPlayer initAudioPlayer() throws IncorrectParametersException {
        Song theBestSong = new Song(new Performer("Ivan Ivanovski", 18),"The best song","Rap", 10);
        Song rado = new Song(new Performer("Petkan Divaka", 55),"Rado - vecheryai","Techno", 15);
        Song pardop = new Song(new Performer("Just Magda", 42),"Pardop","Metal", 180);
        return new AudioPlayer(theBestSong, rado, pardop);
    }
}
