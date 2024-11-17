import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerMP3 {
    int totalLength ;
    int pauseLocation;
    Player player;
    InputStream is;
    String musicFile = "mj beat it.mp3" ;
//    String musicFile = "one-minute-clock.mp3" ;//1930135
//    String musicFile = "./resources/gun salute.mp3";


    public void play() throws IOException,  JavaLayerException {
        is = this.getClass().getResourceAsStream(musicFile);
        totalLength = is.available();
        player = new Player(is);

        new Thread(){

            @Override
            public void run() {
                try {
                    player.play();
                }
                catch (JavaLayerException ex) {
                    System.err.println("Error caught " + ex);
                }
            }
        }.start();

    }

    public void pause(){
        if (player != null) {
            try{
                pauseLocation = is.available();
                player.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void rewind(int value) throws IOException, JavaLayerException{
        pause();
        is = this.getClass().getResourceAsStream(musicFile);
        int rewindMech = totalLength - (pauseLocation + value);
        System.out.println("Rewind : " + rewindMech);
        is.skip(rewindMech);

        player = new Player(is);

        new Thread() {

            @Override
            public void run(){
                try {
                    player.play();
                }catch (JavaLayerException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void fastForward(int value) throws IOException, JavaLayerException{
        pause();
        is = this.getClass().getResourceAsStream(musicFile);
        int rewindMech = totalLength - (pauseLocation - value);
        is.skip(rewindMech);

        player = new Player(is);

        new Thread() {

            @Override
            public void run(){
                try {
                    player.play();
                }catch (JavaLayerException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void resume() throws IOException, JavaLayerException{
        is = this.getClass().getResourceAsStream(musicFile);
        is.skip(totalLength - pauseLocation);

        player = new Player(is);

        new Thread() {

            @Override
            public void run(){
                try {
                player.play();
                }catch (JavaLayerException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
