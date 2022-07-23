package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class jbsignup {


    static int countusername = 0;
    static int countemail = 0;
    static int countpassword =0;

    public static String acceptusername() {
        String finalname = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your Name");
        System.out.println("1.Name must be 5-29 characters\n" +
                "2.Use underscore instead of space");
        String username = sc.next();

        boolean ismatch = Pattern.matches("^[A-Za-z]{5,29}$", username);


        while (countusername < 3) {
            if (ismatch) {
                finalname = username;
                countusername =5;

            } else {
                System.out.println("Your Name was not in Required Format");
                acceptusername();
                countusername++;
            }

        }
        return finalname;
    }

    public static String acceptemail()
    {
        String finalemail = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Email");
        String email = sc.next();

        boolean ismatch = Pattern.matches("^(.+)@(.+)$",email);

        while (countemail < 3) {
            if (ismatch) {
                finalemail = email;
                countemail = 5;

            } else {
                System.out.println("Your email is not in Required Format");
                acceptemail();
                countemail++;
            }
        }
        return finalemail;
    }

    public static String acceptpassword()
    {
        String finalpassword=null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Your Password [characters (8-20)]");
        System.out.println("""
                1. Password must contain at least one digit [0-9].
                2. Password must contain at least one Latin character [a-zA-Z]
                Input a password (You are agreeing to the above Terms and Conditions.):\s""");
        String password = sc.next();

        boolean ismatch = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",password);

        while (countpassword < 3)
        {
            if (ismatch)
            {
                finalpassword = password;
                countpassword = 5;
            }
            else
            {
                System.out.println("Your password was inconsistent");
                acceptpassword();
                countpassword++;
            }
        }
        return  finalpassword;
    }

    public static void signup()
    {
        String name ;
        String email ;
        String password ;
        String location ;

        Scanner sc = new Scanner(System.in);

            System.out.println();
            name = acceptusername();
            System.out.println();
            email= acceptemail();
            System.out.println();
            password = acceptpassword();
            System.out.println();
            System.out.println("Enter your location");
            location = sc.next();
            System.out.println();



        String q1 = "insert into customer values(null,'"+name+"','"+location+"','"+email+"','"+password+"')";

        boolean ret = jdbcconnect.insert(q1);

        if(ret)
        {

            System.out.println("++++++++++++Your Account Details are:");
            System.out.println("User Name : "+name);
            System.out.println("User Email : "+email);
            System.out.println("User Password : "+password);
            System.out.println("User Location : "+location);
            System.out.println();
            System.out.println("********** Account Created Successfully **********");
            System.out.println("Proceed to login");
            System.out.println();
            System.out.println();
        }
    }
}
