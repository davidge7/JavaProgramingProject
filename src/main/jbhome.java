package main;

import java.util.Scanner;

public class jbhome {

    public static void nonusermenu() {
        System.out.println();
        System.out.println("""
                How do you like to proceed
                1. Listen to Songs OR Podcasts
                2. Create an Account""");

        Scanner sc = new Scanner(System.in);
        int c1 = sc.nextInt();
        switch (c1) {
            case 1: {
                jblisten.listen();
            }
            case 2: {
                jbsignup.signup();

                System.out.println("""
                        hoe do you like to continue
                        1. Login
                        2. Exit""");

                int c2 = sc.nextInt();
                switch (c2) {
                    case 1: {
                        jbsignin.signin();
                    }
                    case 2: {
                        jukeboxmain.exit();
                        break;
                    }
                }
            }

        }

    }

    public static void usermenu()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("""
                        =====Main Menu=====
                        1.Create Playlist\s
                        2.Add to Playlist \s
                        3.Play from the Playlist\s
                        4.Logout
                        
                        Enter Your Choice:""");
        int c5 = sc.nextInt();
        System.out.println();

        switch (c5)
        {
            case 1 :
            {
                jbplaylist.createplaylist();
                break;
            }
            case 2:
            {
                jbplaylist.addtoplaylist();
                break;
            }
            case 3 :
            {
                jbplay.playfromtheplaylists();
                break;

            }
            case 4:
            {
                logout();
                break;
            }
        }

    }

    public static void logout()
    {
        jbsignin.fetchedemail = null;
        jbsignin.fetchedpassword = null;
        System.out.println("***** You are successfully logged out*****");
        jukeboxmain.exit();
    }

}
