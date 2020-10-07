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
     */
    private static void startApplication(AudioPlayer audioPlayer)
            throws InterruptedException, IOException {

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
                    System.out.println(audioPlayer.stop());
                    input = scanner.nextLine();
                    break;
                case "Pause":
                    System.out.println(audioPlayer.pause());
                    input = scanner.nextLine();
                    break;
                case "Next":
                    System.out.println(audioPlayer.next());
                    input = scanner.nextLine();
                    break;
                case "Prev":
                    System.out.println(audioPlayer.prev());
                    input = scanner.nextLine();
                    break;
                case "Add song":
                    System.out.println("Enter the song data in order: performer name, performer age, title, genre, song's length in seconds");
                    String[] songInput = scanner.nextLine().split(", ");
                    System.out.println(audioPlayer.addSong(songInput));
                    input = scanner.nextLine();
                    break;
                case "Remove song":
                    System.out.println("Enter the number of the song: ");
                    int songNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println(audioPlayer.removeSong(songNumber));
                    input = scanner.nextLine();
                    break;
                case "Info":
                    System.out.println(audioPlayer.info());
                    input = scanner.nextLine();
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
     */
    private static AudioPlayer initAudioPlayer() {
        Song seriousSong = new Song(new Performer("S kvo si doshla", 27), "S Elena Gomez", "Pop", 10);
        Song assLikeThat = new Song(new Performer("Eminem", 48), "Ass like that", "Rap", 25);
        Song kravi4kata = new Song(new Performer("KoseBose", 25), "Kravi4kata", "Folklore", 23);
        return new AudioPlayer(seriousSong, assLikeThat, kravi4kata);
    }
}
