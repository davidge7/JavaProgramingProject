package main;

import java.util.Scanner;

public class jukeboxmain {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("=========='Welcome to Juke Box'==========");
        System.out.println();
        jblisten.view();
        System.out.println();
        System.out.println();
        System.out.println("""
                Do you Have  an Account
                1. Yes
                2. No
                
                Enter your choice:""");
        int c1 = sc.nextInt();


            switch (c1) {
                case 1 -> {
                    jbsignin.signin();
                }
                case 2 -> {
                   jbhome.nonusermenu();
                }
                default -> {
                    System.out.println("Invalid entry");
                    break;
                }
            }

    }
     public static void exit()
     {
         boolean exit = true;
         while(exit) {
             System.out.println("*****Thank You*****");
             exit=false;
             break;
         }
     }
}
