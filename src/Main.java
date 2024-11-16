import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main implements ActionListener, ChangeListener {
    PlayerMP3 lis = new PlayerMP3();
    JButton play, pause, rewind, forward, resume;
    JSlider slider;
    int total = lis.totalLength; // 1000
    static boolean invisible;

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

        pause = new JButton();
        pause.setText("lol");
        pause.setBounds(5,5,50,50);
        pause.addActionListener(this);


        resume = new JButton();
        resume.setText("R");
        resume.setBounds(5,5,50,50);
        resume.addActionListener(this);

        invisible = true;

        slider = new JSlider(0,100,0);
        slider.setPreferredSize(new Dimension(x,20));
        if (invisible == false) {
            slider.addChangeListener(this); // Comment this
        }

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
                switcher();
                if (invisible == true) {
                    sliderValueChange();
                }//Comment this
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
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
                lis.pause();
                resume.setVisible(false);
                play.setVisible(false);
                pause.setVisible(true);
                lis.rewind(1000000);
                System.out.println("Rewind is working");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == forward){
            try {
                lis.pause();
                lis.fastForward(1000000);
                resume.setVisible(false);
                play.setVisible(false);
                pause.setVisible(true);
                System.out.println("Forward is working");
            } catch (IOException ex) {
                System.out.println("Stream close");
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void stateChanged (ChangeEvent e){
        System.out.println("SSSSSSSState is changinggggggggggggggggggggggggggg");
        lis.pause();
        int ticksMoved = slider.getValue(); // 2
        System.out.println("Ticks moved : " + ticksMoved);
        System.out.println("Total length : " + total);
        int ticksToMove = total / 100; // 1000 / 100
        System.out.println("Ticks to move : " + ticksToMove);
        lis.pauseLocation = total - (ticksMoved * ticksToMove); // 2 x 10
        System.out.println("Pause location : " + lis.pauseLocation);
        try {
            lis.resume();
//            sliderValueChange();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (JavaLayerException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sliderValueChange() {
//        System.out.println("lollllllllllllllllllllllllllllllllllllllllll");
        new Thread(){

            @Override
            public void run() {
                try {
                    int total = lis.totalLength; // 1000
//                    System.out.println("The total is : " + total);
                    int leftOver = lis.is.available();// 900
//                    System.out.println("The leftover is : " + leftOver);
                    int ticksToMove = total / 100;// 10
//                    System.out.println("The ticksToMove is : " + ticksToMove);
                    int value = (leftOver / ticksToMove); // 90
//                    System.out.println("The value is : " + value);
                    int newValue = 100 - value; // 10
//                    System.out.println("The newValue is : " + newValue);
                    slider.setValue(newValue);
                }
                catch (IOException ex) {
                }
                sliderValueChange();
            }
        }.start();
    }

    public boolean switcher() throws IOException, InterruptedException {
//        new Thread () {
//            @Override
//            public void run(){
//            int i = 0;
//            while(i != -1) {
//            try {
//                i++;
//            if (i % 2 == 0) {
//                invisible = true;
//                Thread.sleep(1000);
//                System.out.println("Invisible is trueeeeeeeeeeeee");
//            } else {
//                invisible = false;
//                System.out.println("Invisible is loooooooooooooooolllllllllllllllllllollolo");
//            }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        }}.start();
        return invisible = false;
    }

    public static void main(String[] args) throws JavaLayerException, IOException {
        new Main();
    }

}
