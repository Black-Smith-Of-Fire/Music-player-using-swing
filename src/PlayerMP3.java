import java.io.*;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerMP3 {
    int totalLength ;
    int pauseLocation;
    Player player;
    InputStream is;
    String musicFile = "mj beat it.mp3" ;

    /**
     * this method is used to play a song, if u want to
     * repeat this song,  set Repeat to true before
     * call this method
     * NOTE: the files to play must be in resources folder
     * @throws FileNotFoundException
     * @throws JavaLayerException
     * @throws IOException
     * @throws java.net.URISyntaxException
     */


    public void play() throws IOException,  JavaLayerException {
        is = this.getClass().getResourceAsStream(musicFile);
        totalLength = is.available();
        player = new Player(is);
        System.out.println(totalLength);
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

    public void rewind() throws IOException, JavaLayerException{
        pause();
        is = this.getClass().getResourceAsStream(musicFile);
        int rewindMech = totalLength - (pauseLocation +1000000);
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

    public static void main(String[] args) throws JavaLayerException, IOException{
        PlayerMP3 lis = new PlayerMP3();
        lis.play();
        Scanner scanner = new Scanner(System.in);
        while(!scanner.nextLine().equals("kill")) {
            if (scanner.nextLine().equals("p")) {
                lis.pause();
            }
            if (scanner.nextLine().equals("r")) {
                lis.resume();
            }
            if (scanner.nextLine().equals("b")) {
                lis.rewind();
            }
        }
    }
}
