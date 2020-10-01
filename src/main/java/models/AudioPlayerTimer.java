package models;

import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayerTimer {
    private Timer timer;
    private TimerTask timerTask;
    private int secondsPassed;

    public AudioPlayerTimer(int count) {
        secondsPassed = 0;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (count <= secondsPassed) {
                    timer.cancel();
                    timer.purge();
                    return;
                }

                int minutes = secondsPassed / 60;
                int seconds = secondsPassed % 60;
                System.out.printf("%02d:%02d\n", minutes, seconds);
                secondsPassed++;
            }
        };
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
}
