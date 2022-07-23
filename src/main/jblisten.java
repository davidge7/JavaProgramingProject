package main;

import java.util.*;

public class jblisten implements jbFunctionalInterface {

    static  String link;

    public static  void listen()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("""
                What woukd you like to listen?
                1.Songs
                2.Podcasts""");
        int c1 = sc.nextInt();
        switch (c1) {
            case 1 -> {
                System.out.println();
                // display songs
                jdbcconnect.displaysongs();
                System.out.println();
                System.out.println("Enter the serial number of the song which you want to listen");
                int c2 = sc.nextInt();

                //querry to fetch link of selected song
                String q1 = "select s.songlink from song s where s.songid=" + c2;

                //calling method to fetch the link of song

                link = jdbcconnect.fetchlink(q1);

                jbplayer.audioplayer(link);

                System.out.println("""
                        You have reached the maximum number of songs you can listen with out Logging In
                        Do you like to Proceed to create an Account?
                        1. Yes
                        2. No
                        
                        Enter your choice""");

                int c3 = sc.nextInt();
                if (c3==1)
                {
                    jbsignup.signup();
                }
                else
                {
                    jukeboxmain.exit();
                    break;
                }
            }
            case 2 -> {
                System.out.println();
                //display podcasts
                jdbcconnect.displaypodcasts();
                System.out.println();
                System.out.println("Enter the serial number of the podcast which you want to listen");
                int c3 = sc.nextInt();

                //querry to fetch link of selected podcast
                String q1 = "select p.podcastlink from podcast p where p.podcastid=" + c3;

                // calling method to fetch the link of podcast
                link = jdbcconnect.fetchlink(q1);

                jbplayer.audioplayer(link);

                System.out.println("""
                        You have reached the maximum number of podcasts you can listen with out Logging In
                        Do you like to Proceed to create an Account?
                        1. Yes
                        2. No
                        
                        Enter your choice""");

                int c4 = sc.nextInt();
                if (c4==1)
                {
                    jbsignup.signup();
                }
                else if (c4==2)
                {
                    jukeboxmain.exit();
                    break;
                }
                else
                {
                    System.out.println("you have selected a wrong choice");
                    break;
                }
            }
            default -> {
            }
        }
    }






    public static  void view()
    {

        System.out.println("The songs available in the Song Pool are:");
        jdbcconnect.displayallsongs();

        System.out.println("The podcasts available in the podcasts collective are:");
        jdbcconnect.displayallpodcasts();

    }


    /*public  static  void checkuser()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("""
                Do You Have An Account??
                1.No
                2.Yes
                
                Enter Your Choice:""");

        int c4 = sc.nextInt();

        switch (c4)
        {
            case 1:
            {
                jbsignup.signup();
            }
            case 2:
            {
                jbsignin.signin();
                break;
            }
        }

    }*/
}
