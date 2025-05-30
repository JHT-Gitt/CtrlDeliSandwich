package org.pluralsight;

import org.pluralsight.screens.Home;

import java.util.Scanner;

public class Main {


    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Press Enter to continue ------------------>");
        scanner.nextLine();

        Home h = new Home(); // Good ol clean lonely Main
        h.display();

    }
}