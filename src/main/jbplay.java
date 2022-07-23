package main;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class jbplay {

    static  String link;
    static String fetchedemail = jbsignin.fetchedemail;
    static String fetchedpassword = jbsignin.fetchedpassword;

    public static void playfromtheplaylists() {
        Scanner sc = new Scanner(System.in);
        int cid = jdbcconnect.fetchcid(fetchedemail, fetchedpassword);

        String q13 = "select p.playlistid ,p.playlistname from playlist p where p.customerid = " + cid;

        jdbcconnect.displayplaylists(q13);

        System.out.println();
        System.out.println("Select the play list id you want to play");
        int pid = sc.nextInt();

        jdbcconnect.displayplaylistsongs(pid, cid);

        System.out.println();
        System.out.println("""
                How do you like to play your playlist
                1. Select a song to play
                2. Play in the loop
                3. Shuffle the list and play
                                
                Enter our choice""");

        int c11 = sc.nextInt();

        switch (c11) {
            case 1: {
                System.out.println();
                System.out.println("Enter the serial number of the song which you want to listen");
                int c12 = sc.nextInt();

                String q14 = "select s.songlink from song s where s.songid=" + c12;


                link = jdbcconnect.fetchlink(q14);

                jbplayer.audioplayer(link);


            }
            case 2: {
                System.out.println();
                String q12 = "select s.songlink from customer c join playlist p  join songplaylist spl  join song  s on" +
                        "(c.customerID = p.customerId and p.playlistId = spl.playlistId and spl.songId = s.songId) where c.customerId = " + cid;

                List<String> songlinks = jdbcconnect.fetchlistoflinks(q12);

                songlinks.stream().forEach((song) -> {jbplayer.audioplayer(song);});
                break;
            }
            case 3: {
                System.out.println();
                String q12 = "select s.songlink from customer c join playlist p  join songplaylist spl  join song  s on" +
                        "(c.customerID = p.customerId and p.playlistId = spl.playlistId and spl.songId = s.songId) where c.customerId = " + cid;

                List<String> songlinks = jdbcconnect.fetchlistoflinks(q12);

                Collections.shuffle(songlinks);

                songlinks.stream().forEach((song) -> {
                    jbplayer.audioplayer(song);
                });
                break;
            }
        }
    }
}
