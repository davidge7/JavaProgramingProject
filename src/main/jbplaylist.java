package main;

import java.util.Scanner;

public class jbplaylist {

    static int countplname = 0;
    static String plname = "";
    static String fetchedemail = jbsignin.fetchedemail;
    static String fetchedpassword = jbsignin.fetchedpassword;

    public static void createplaylist() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Follow the commands to create a song playlist.");
        System.out.println("Enter the name of the playlist :");
        String playlistname = sc.next();

        int cid = jdbcconnect.fetchcid(fetchedemail, fetchedpassword);

        String q4 = "insert into playlist values(null,'" + playlistname + "'," + cid + ")";

        boolean ret = jdbcconnect.insert(q4);
        if (!ret) {
            System.out.println("Playlist not created.");
            createplaylist();
        } else {
            System.out.println();
            System.out.println("*****Play list created successfully*****");
            jdbcconnect.displayplaylistdetails(playlistname, cid);
            System.out.println();
            System.out.println("""
                    How would you like to proceed ?
                    1.Add to Playlists
                    2.Listen from Previous playlists
                    3.Logout""");
            int c1 = sc.nextInt();
            switch (c1) {
                case 1: {
                    addtoplaylist();
                }
                case 2: {
                    jbplay.playfromtheplaylists();
                }
                case 3: {
                    jbhome.logout();
                    break;
                }
            }
        }
    }


    public static void addtoplaylist() {
        Scanner sc = new Scanner(System.in);
        int cid = jdbcconnect.fetchcid(fetchedemail, fetchedpassword);
        System.out.println();
        System.out.println("The playlists available  are :");
        String q13 = " select p.playlistid, p.playlistname from playlist p where p.customerid = " + cid;
        jdbcconnect.displayplaylists(q13);
        System.out.println();
        System.out.println("Enter the name of the play list you want to edit:");
        plname = sc.next();

        String q6 = "select p.playlistname from playlist p where p.playlistname = '" + plname + "' and p.customerid = " + cid;

        boolean ret = jdbcconnect.checkavailabilityplname(q6, plname);
        while (countplname < 4) {
            if (!ret) {
                System.out.println("Sorry playlist name is entered wrongly.");
                addtoplaylist();
                countplname++;
            } else {
                countplname = 5;
            }
        }

        System.out.println();
        System.out.println("""
                What would you like to add to your play list?
                1. Songs
                2. Podcasts
                                
                Enter Your Choice:""");
        int c6 = sc.nextInt();

        switch (c6) {
            case 1 -> {
                addtosong();
            }
            case 2 -> {
                addtopodcast();

            }
        }

    }


    public static void addtosong() {
        Scanner sc = new Scanner(System.in);
        jdbcconnect.displaysongs();
        System.out.println();
        System.out.println("Enter the serial number of the song which you want to add :");
        int sid = sc.nextInt();

        int cid = jdbcconnect.fetchcid(fetchedemail, fetchedpassword);

        String q7 = "select p.playlistid from playlist p where  p.customerid = " + cid + " and p.playlistname = '" + plname + "'";
        int psid = jdbcconnect.fetchpplid(q7);

        String q8 = "insert into songplaylist values(null," + psid + "," + sid + ")";

        boolean ret = jdbcconnect.insert(q8);
        if (!ret) {
            System.out.println();
            System.out.println("Sorry,Song not inserted,Please try again.");
            System.out.println();
            addtosong();
        } else {
            System.out.println();
            System.out.println("hurray!!Song Successfully inserted into playlist.");
        }
        System.out.println();
        System.out.println("""
                Do you want to add another track ?
                1.Yes
                2.Go to main menu
                3.Logout
                                
                Enter Your Choice:""");

        int c1 = sc.nextInt();
        switch (c1) {
            case 1: {
                System.out.println("""
                        What would you like to add to your play list?
                        1. Songs
                        2. Podcasts
                                        
                        Enter Your Choice:""");
                int c6 = sc.nextInt();

                switch (c6) {
                    case 1 -> {
                        addtosong();
                    }
                    case 2 -> {
                        addtopodcast();
                    }
                }
            }
            case 2: {
                jbhome.usermenu();
            }
            case 3: {
                jbhome.logout();
                break;
            }
        }

    }

    public static void addtopodcast() {
        Scanner sc = new Scanner(System.in);
        jdbcconnect.displaypodcasts();
        System.out.println();
        System.out.println("Enter the serial number of the podcast which you want to add");
        int pcid = sc.nextInt();

        int cid = jdbcconnect.fetchcid(fetchedemail, fetchedpassword);

        String q9 = "select p.playlistid from playlist p where  p.customerid = " + cid + " and p.playlistname = '" + plname + "'";
        int ppcid = jdbcconnect.fetchpplid(q9);


        String q10 = "insert into podcastplaylist values(null," + ppcid + "," + pcid + ")";
        boolean ret = jdbcconnect.insert(q10);
        if (!ret) {
            System.out.println();
            System.out.println("Sorry,Podcast not inserted successfully.Please try Again");
            System.out.println();
            addtopodcast();
        } else {
            System.out.println();
            System.out.println("hurray,Podcast Successfully inserted into playlist");
        }
        System.out.println();
        System.out.println("""
                Do you want to add another track ??
                1.Yes
                2.Go to main menu
                3.Logout
                                
                Enter Your Choice:""");

        int c1 = sc.nextInt();
        switch (c1) {
            case 1: {
                System.out.println("""
                        What would you like to add to your play list?
                        1. Songs
                        2. Podcasts
                                        
                        Enter Your Choice:""");
                int c6 = sc.nextInt();

                switch (c6) {
                    case 1 -> {
                        addtosong();
                    }
                    case 2 -> {
                        addtopodcast();
                    }
                }
            }
            case 2: {
                jbhome.usermenu();
            }
            case 3: {
                jbhome.logout();
                break;
            }

        }

    }

}
