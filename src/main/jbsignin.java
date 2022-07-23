package main;

import java.util.Objects;
import java.util.Scanner;

public class jbsignin {


    static String emailin ="";
    static int countsigninemail =0;
    static int countsigninpassword =0;
    static String fetchedemail="";
    static String fetchedpassword ="";




    public static void checkuseremail()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your email ");
        emailin = sc.next();
        String q2 = "select c.customeremail from customer c where c.customeremail='"+emailin+"'";
        String s = jdbcconnect.checkavailability(q2);
        if (emailin.equalsIgnoreCase(s))
        {
            fetchedemail = emailin;
            countsigninemail=5;

        }
        else if (countsigninemail<3) {
            System.out.println("you have entered a wrong email");
            countsigninemail++;
            checkuseremail();
        }
        else
        {
            System.out.println("sorry, You have entered worng email to many times");
            fetchedemail = "";
        }
    }

    public static void checkpassword()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Password");
        String passwordin = sc.next();
        String q3 = "select c.customerpassword from customer c where c.customerpassword='"+passwordin+"' and c.customeremail='"+emailin+"'";
        String s = jdbcconnect.checkavailability(q3);
        if (Objects.equals(passwordin, s))
        {
            fetchedpassword = passwordin;
            countsigninpassword = 5;
        }
        else if (countsigninpassword < 3) {
            System.out.println("you have entered a wrong password");
            countsigninemail++;
            checkpassword();
        }
        else
        {
            System.out.println("sorry, You have entered worng email to many times");
            fetchedpassword = "";
        }

    }


    public static void signin()
    {
        System.out.println("=========Welcome To Sign In Portal=========");
        System.out.println();
        checkuseremail();
        System.out.println();
        checkpassword();
        jbhome.usermenu();

    }


}
