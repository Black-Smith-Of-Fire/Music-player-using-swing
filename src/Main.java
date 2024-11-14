import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class Main implements ActionListener, ChangeListener {
    PlayerMP3 lis = new PlayerMP3();
    JButton play, pause, rewind, forward, resume;
    JSlider slider;
    boolean invisible = false;

    Main() throws IOException{
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

        pause = new JButton();
        pause.setText("lol");
        pause.setBounds(5,5,50,50);
        pause.addActionListener(this);


        resume = new JButton();
        resume.setText("R");
        resume.setBounds(5,5,50,50);
        resume.addActionListener(this);

        slider = new JSlider(0,100,0);
        slider.setPreferredSize(new Dimension(x,20));
        slider.addChangeListener(this);

        forward = new JButton();
        forward.setText("F");
        forward.setBounds(0,5,50,50);
        forward.addActionListener(this);

        buttonPanel.add(rewind);
        buttonPanel.add(play);
        buttonPanel.add(pause);
        buttonPanel.add(resume);
        buttonPanel.add(forward);

        pause.setVisible(false);
        resume.setVisible(false);

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
                sliderValueChange();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
            play.setVisible(false);
            pause.setVisible(true);
        }

        if(e.getSource() == pause){
            lis.pause();
            pause.setVisible(false);
            resume.setVisible(true);
        }


        if(e.getSource() == resume){
            try {
                lis.resume();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
            resume.setVisible(false);
            pause.setVisible(true);
        }

        if(e.getSource() == rewind){
            try {
                lis.rewind(1000000);
                slider.setValue(50);
                System.out.println("Rewind is working");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == forward){
            try {
                lis.fastForward(1000000);
                System.out.println("Forward is working");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void stateChanged (ChangeEvent e){
    }

    public void sliderValueChange() {
        new Thread(){

            @Override
            public void run() {
                try {
                    int total = lis.totalLength;
                    System.out.println("total is : " + total);
                    int leftOver = lis.is.available();
                    int ticksToMove = total / 100;
                    System.out.println("ticks to move  is : " + ticksToMove);
                    System.out.println("Leftover is : " + leftOver);
                    int value = (leftOver / ticksToMove);
                    System.out.println("And the value is " + value);
                    slider.setValue(100 - value);
                }
                catch (IOException ex) {
                    System.err.println("Error caught " + ex);
                }
            }
        }.start();
//        }
    }

    public static void main(String[] args) throws JavaLayerException, IOException {
        new Main();
    }

}
