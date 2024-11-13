import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class Main implements ActionListener {
    PlayerMP3 lis = new PlayerMP3();
    JButton play , rewind , forward;
    JSlider slider;

    Main(){
        int x = 250 , y = 250;
        JFrame frame = new JFrame();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.red);
        buttonPanel.setBounds(0,(y/2),x,(y/2));

        JPanel sliderPanel = new JPanel();
        sliderPanel.setBounds(0,0,x,(y/2));
        sliderPanel.setBackground(Color.blue);


        rewind = new JButton();
        rewind.setText("B");
        rewind.setBounds(0,5,50,50);
        rewind.addActionListener(this);

        play = new JButton();
        play.setText("Play");
        play.setBounds(5,5,50,50);
        play.addActionListener(this);


        slider = new JSlider(0,100, 50);
        slider.setPreferredSize(new Dimension(x,20));

        forward = new JButton();
        forward.setText("F");
        forward.setBounds(0,5,50,50);
        forward.addActionListener(this);

        buttonPanel.add(rewind);
        buttonPanel.add(play);
        buttonPanel.add(forward);

        sliderPanel.add(slider);
        frame.setSize(x,y);
        frame.setTitle("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(buttonPanel);
        frame.add(sliderPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play){
            try {
                lis.play();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }


        if(e.getSource() == rewind){
            try {
                lis.rewind();
                System.out.println("Rewind is working");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == forward){
            try {
                lis.fastForward();
                System.out.println("Forward is working");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) throws JavaLayerException, IOException {
        new Main();
//        Scanner scanner = new Scanner(System.in);
//        while(!scanner.nextLine().equals("kill")) {
//            if (scanner.nextLine().equals("p")) {
//                lis.pause();
//            }
//            if (scanner.nextLine().equals("r")) {
//                lis.resume();
//            }
//            if (scanner.nextLine().equals("b")) {
//                lis.rewind();
//            }
//        }
    }

}
