package main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class jdbcconnect {


    public static Statement connect() {
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jukebox";
            Connection con = DriverManager.getConnection(url, "root", "root@123");
            stmt = con.createStatement();

        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return stmt;
    }

    public static void displayallsongs()
    {
        try
        {
        String q2 = "select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal  \n" +
                "where s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid  order by s.songtitle";

        Statement s = connect();
        ResultSet rs = s.executeQuery(q2);
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.format("%5s %17s %20s %20s %16s %18s", "Sl No", "Song Name", "Artist Name", "Album Name", "Genere", "Duration\n");
        System.out.println("------------------------------------------------------------------------------------------------------");
        while (rs.next()) {
            System.out.format("%3s %20s %20s %17s %20s %16s",rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getTime(6)+"\n");
        }
        System.out.println("------------------------------------------------------------------------------------------------------");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }

    public static void displayallpodcasts()
    {
        try
        {
            String q2 = "select p.podcastid,p.podcasttitle,psp.podcastspeakername,pse.podcastseriesname,p.podcasttopic,p.podcastduration " +
                    "from podcast p join podcastspeaker psp join podcastseries pse " +
                    "where p.podcastspeakerid=psp.podcastspeakerid and p.podcastseriesid = pse.podcastseriesid order by p.podcasttitle";


            Statement s = connect();
            ResultSet rs = s.executeQuery(q2);
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.format("%5s %22s %20s %23s %17s %16s", "Sl No", "Podcast Name", "Speaker Name", "Series Name", "Topic", "Duration\n");
            System.out.println("------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.format("%3s %25s %20s %28s %14s %13s",rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getTime(6)+"\n");
            }
            System.out.println("------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static  void displaysongs()
    {
        Scanner sc = new Scanner(System.in);
        String q2 = "";
        try {
            System.out.println();
            System.out.println("""
                    How do you like to categorise your songs?
                    1.Artist
                    2.Album
                    
                    Enter your choice""");
            int c1 = sc.nextInt();

            switch (c1) {
                case 1 -> {
                    System.out.println();
                    String q = "select a.songartistid , a.songartistname from  songartist a";
                    jdbcconnect.displayartists(q);
                    System.out.println();
                    System.out.println("Select the Artist id of whose songs you want to listen");
                    int c2 = sc.nextInt();

                    q2 = "select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal  \n" +
                            "where s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid and s.songartistid = "+c2+" order by s.songtitle";

                }
                case 2 -> {
                    System.out.println();
                    String q1 = "select a.songalbumid , a.songalbumname from songalbum a";
                    jdbcconnect.displayalbums(q1);
                    System.out.println();
                    System.out.println("Selct the Album id from which you want to listen");
                    int c3 = sc.nextInt();

                    q2 = "select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal  \n" +
                            "where s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid and s.songalbumid = "+c3+" order by s.songtitle";

                }
                default -> {
                }
            }


            Statement s = connect();
            ResultSet rs = s.executeQuery(q2);
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.format("%5s %17s %20s %20s %16s %18s", "Sl No", "Song Name", "Artist Name", "Album Name", "Genere", "Duration\n");
            System.out.println("------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.format("%3s %20s %20s %17s %20s %16s",rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getTime(6)+"\n");
            }
            System.out.println("------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static  void displaypodcasts()
    {
        Scanner sc = new Scanner(System.in);
        try {
            String q2 ="";

            System.out.println("""
                    How do you like to order your podcasts?
                    1.Speaker
                    2.Series
                    
                    Enter the choice""");
            int c2 = sc.nextInt();

            switch (c2) {
                case 1 -> {
                    System.out.println();

                    String q3 ="select p.podcastspeakerid ,p.podcastspeakername from podcastspeaker p";
                    jdbcconnect.displayspeaker(q3);
                    System.out.println();
                    System.out.println("Select the speaker id of whose podcasts you want to listen");
                    int c4 = sc.nextInt();

                    q2 = "select p.podcastid,p.podcasttitle,psp.podcastspeakername,pse.podcastseriesname,p.podcasttopic,p.podcastduration " +
                            "from podcast p join podcastspeaker psp join podcastseries pse " +
                            "where p.podcastspeakerid=psp.podcastspeakerid and p.podcastseriesid = pse.podcastseriesid  and psp.podcastspeakerid = "+c4;
                }
                case 2 -> {
                    System.out.println();
                    String q4 = "select p.podcastseriesid , p.podcastseriesname from podcastseries p";
                    jdbcconnect.displayseries(q4);
                    System.out.println();
                    System.out.println("Select the series from which you want to listen the podcasts");
                    int c5 = sc.nextInt();

                    q2 = "select p.podcastid,p.podcasttitle,psp.podcastspeakername,pse.podcastseriesname,p.podcasttopic,p.podcastduration " +
                            "from podcast p join podcastspeaker psp join podcastseries pse " +
                            "where p.podcastspeakerid=psp.podcastspeakerid and p.podcastseriesid = pse.podcastseriesid and pse.podcastseriesid = "+c5 ;
                }
                default -> {
                }
            }

            Statement s = connect();
            ResultSet rs = s.executeQuery(q2);
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.format("%5s %22s %20s %23s %17s %16s", "Sl No", "Podcast Name", "Speaker Name", "Series Name", "Topic", "Duration\n");
            System.out.println("------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.format("%3s %25s %20s %28s %14s %13s",rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getTime(6)+"\n");
            }
            System.out.println("------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static void displayartists(String q)
    {
        try
        {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            System.out.println("-----------------------------------");
            System.out.format("%10s %20s","Artist Id","Artist Name\n");
            System.out.println("-----------------------------------");
            while (rs.next())
            {
                System.out.format("%10s %20s",rs.getInt(1),rs.getString(2)+"\n");
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  void  displayalbums(String q)
    {
        try
        {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            System.out.println("-----------------------------------");
            System.out.format("%10s %20s","Album Id","Album Name\n");
            System.out.println("-----------------------------------");
            while (rs.next())
            {
                System.out.format("%10s %20s",rs.getInt(1),rs.getString(2)+"\n");
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayspeaker(String q)
    {
        try
        {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            System.out.println("-----------------------------------");
            System.out.format("%10s %20s","Speaker Id","Speaker Name\n");
            System.out.println("-----------------------------------");
            while (rs.next())
            {
                System.out.format("%10s %20s",rs.getInt(1),rs.getString(2)+"\n");
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  void  displayseries(String q)
    {
        try
        {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            System.out.println("-----------------------------------");
            System.out.format("%10s %20s","Series Id","Series Name\n");
            System.out.println("-----------------------------------");
            while (rs.next())
            {
                System.out.format("%10s %20s",rs.getInt(1),rs.getString(2)+"\n");
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String fetchlink(String q)
    {
        String link = null;
        try {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            rs.next();
            link = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return link;
    }

    public static boolean insert(String q)
    {
        boolean a = false;
        try {
            Statement s = jdbcconnect.connect();
            int ret =s.executeUpdate(q);
            if (ret ==1) {
                a = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return a;
    }

    public static  String checkavailability(String q)
    {
        String r = null;
        try {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            while (rs.next())
            {
                r = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static int fetchcid(String email, String password)
    {
        int cid = 0;
        try {
            String q3 = "select c.customerid from customer c where c.customeremail='" + email + "'  and c.customerpassword = '" + password + "'";

            Statement s = connect();
            ResultSet rs = s.executeQuery(q3);

            while (rs.next())
            {
                cid = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cid;
    }

    public static void displayplaylistdetails(String plname ,int cid)
    {

        try {
            String q4 = "select p.playlistid ,p.playlistname from playlist p where p.customerid = " + cid + " and p.playlistname = '" + plname + "'";

            Statement s = connect();
            ResultSet rs = s.executeQuery(q4);

            while (rs.next()) {
                System.out.println("Playlist Id = "+rs.getInt(1));
                System.out.println("Playlist Name = "+rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  boolean checkavailabilityplname(String q,String plname)
    {
        boolean r = false;
        try {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            while (rs.next())
            {
                if (plname.equalsIgnoreCase(rs.getString(1)))
                {
                    r = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static void  displayplaylists(String q)
    {
        try
        {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            System.out.println("-----------------------------------");
            System.out.format("%10s %20s","Platlist Id","Playlistname\n");
            System.out.println("-----------------------------------");
            while (rs.next())
            {
                System.out.format("%10s %20s",rs.getInt(1),rs.getString(2)+"\n");
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int fetchpplid(String q)
    {
        int i =0;
        try {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {
                i=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public static List<String> fetchlistoflinks(String q)
    {
       List<String > songlinks = new LinkedList<>();
        try {
            Statement s = connect();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {
               songlinks.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return songlinks;
    }

    public static void displayplaylistsongs(int pid ,int cid)
    {

        String q5 = "select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal\n" +
                "where s.songid in (select spl.songid from  songplaylist spl where spl.playlistid in (select p.playlistid from playlist p where p.playlistid = "+pid+" and p.customerid ="+cid+"))\n" +
                "and s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid";

        try
        {
        Statement s = connect();
        ResultSet rs = s.executeQuery(q5);
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.format("%5s %24s %22s %23s %15s %11s", "Sl No", "Song/Podcast Name", "Artist/Speaker Name", "Album/Series Name", "Topic/genere", "Duration\n");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.format("%3s %25s %20s %25s %14s %13s",rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getTime(6)+"\n");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }


    }
}
