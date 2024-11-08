import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerMp3 implements Runnable {

    private boolean exit; // if true , it will cause the action within the thread to stop
    Thread thread ;

    PlayerMp3(){
        thread = new Thread(this);
        exit = false;
        thread.start();
    }

    public void PlayerMp() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/tootttooottoo.mp3");
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
            player.play();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!exit){
            PlayerMp();
        }
    }

    public void stop(){ // use this method to stop the thread
        exit = true;
    }

    public static void main(String[] args) {
        PlayerMp3 mp = new PlayerMp3();
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("s")){
            mp.stop();
        }
    }

}
