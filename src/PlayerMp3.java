import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerMp3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            FileInputStream fileInputStream = new FileInputStream("src/tootttooottoo.mp3");
            Player player = new Player(fileInputStream);
            player.play();
            if (scanner.next().equals("k")) {

            }
            System.out.println("Song is playing");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
