import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerMp3 {
    private int pausedOnFrame = 0;

    public void PlayerMp() {
        Scanner scanner = new Scanner(System.in);
        try {
            FileInputStream fileInputStream = new FileInputStream("src/tootttooottoo.mp3");
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
            String s = scanner.nextLine();

            if (s.equals("k")){
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
            }
            player.play(pausedOnFrame, Integer.MAX_VALUE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PlayerMp3 mp = new PlayerMp3();
        mp.PlayerMp();
    }
}
