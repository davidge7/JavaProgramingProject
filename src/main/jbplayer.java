package main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class jbplayer {


    static boolean exit = true;
    static String filepath;

    //to store info at an instance
    static Long currpos;
    static Clip clip;

    //to store current task
    static String status;

    AudioInputStream ais;

    public static void audioplayer(String link) {
        filepath = link;

        try {
            //creating object of inner class
            jbplayer player = new jbplayer();

            player.play();
            Scanner sc = new Scanner(System.in);

            while (!(exit != true)) {
                System.out.println("""
                        
                        ****Player Controls****
                        1. pause
                        2. resume
                        3. restart
                        4. Jump to specific time
                        5. Stop and Next(only for playlists)
                        6. Stop and Exit""");
                int c = sc.nextInt();
                player.gotochoice(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public jbplayer() {
        try {
            // creating the stream of audio files
            ais = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

            //clip reference creation
            clip = AudioSystem.getClip();

            //open Ais through clip

            clip.open(ais);
            //loop condition
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private void gotochoice(int c) {

        switch (c) {
            case 1 -> {
                //calling method to pause
                pause();
            }
            case 2 -> {
                //calling method to resume
                resume();
                break;
            }
            case 3 -> {
                //calling method to restart
                restart();
                break;
            }
            case 4 -> {
                System.out.println("Enter time (" + 0 +
                        ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);
            }
            case 5 -> {
                //calling stop method
                stopandnext();
                break;
            }
            case 6 -> {
               // calling stop and exit method
                stopandexit();

            }
            default -> throw new IllegalStateException("Unexpected value: " + c);
        }
    }


    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();
        status = "play";
    }

    // method to pause
    public void pause() {
        if (status.equalsIgnoreCase("Paused")) {
            System.out.println("Audio is already paused");
            return;
        }

        this.currpos = clip.getMicrosecondLength();
        clip.stop();
        status = "Paused";
    }

    // Method to resume the audio
    public void resume() {
        if (status.equalsIgnoreCase("play")) {
            System.out.println("Audio is already being Played");
            return;
        }

        clip.close();
        resetaudiostream();
        clip.setMicrosecondPosition(currpos);
        this.play();
    }

    public void restart() {
        clip.stop();
        clip.close();

        resetaudiostream();
        currpos = 0L;
        clip.setMicrosecondPosition(0L);
        this.play();
    }

    public void jump(long c) {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetaudiostream();
            currpos = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }

    }
    public void stopandnext()
    {
        currpos = 0L;
        clip.stop();
        clip.close();
    }

    public void stopandexit()
    {
        currpos =0L;
        clip.stop();
        clip.close();
        exit = false;
        jbhome.usermenu();
    }

    public void resetaudiostream() {
        try {
            ais = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio format not supported");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

