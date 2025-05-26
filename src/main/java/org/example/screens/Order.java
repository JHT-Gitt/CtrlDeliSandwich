package org.example.screens;

import org.example.Interface.Extras;

import java.util.Scanner;

public class Order implements Extras {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    Scanner scanner = new Scanner(System.in);
    public void addSandwich(){
        int enter;

        lines();
        System.out.println(YELLOW + "Select Sandwich Bread type:" + RESET);
        System.out.println(" 1 - White ");
        System.out.println(" 2 - Wheat");
        System.out.println(" 3 - Rye");
        System.out.println(" 4 - Wrap");
        lines();

        size();

    }
    public void size(){
        double s = 5.50;
        double m = 7.00;
        double l = 8.50;
        String indent = "        ";
        lines();
        System.out.printf(indent + YELLOW +"%-12s|" + indent +"%s\n" + RESET, "Size", "Price" );
        System.out.printf(indent + "%-12s|" + indent +"$%.2f\n", "4\"", s);
        System.out.printf(indent + "%-12s|" + indent +"$%.2f\n", "8\"", m);
        System.out.printf(indent + "%-12s|" + indent +"$%.2f\n", "12\"", l);
        lines();


        System.out.print(GREEN + "Do you want it roasted [Y/N] ?: " + RESET);
        meat();

    }
    public void meat(){
        double s = 1.00;
        double m = 2.00;
        double l = 3.00;
        String indent = "   ";
        System.out.println("\n========== 🥩 " + YELLOW + "MEAT TOPPINGS"  + RESET + " 🥩 ===========");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Steak", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Ham", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Salami", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Roast Beef", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Chicken", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Bacon", s, m, l);
        lines();
        cheese();
        extraCheese();
        extraMeat();

    }
    public void cheese(){
        double s = .75;
        double m = 1.50;
        double l = 2.25;
        String indent = "   ";
        System.out.println("\n========= 🧀 " + YELLOW + "CHEESE TOPPINGS"  + RESET + " 🧀 ==========");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "American", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Provolone", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Cheddar", s, m, l);
        System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", "Swiss", s, m, l);
        lines();
    }

public void lines(){
    System.out.println("===========================================");

}
    @Override
    public void extraMeat() {
        double s = .50;
        double m = 1.00;
        double l = 1.50;
        System.out.printf("Extra Meat  |   %.2f | %.2f | %.2f", s,m,l);
        System.out.println(GREEN + "\nAdd Extra Meat [Y/N] ? : "  + RESET );

        lines();
    }

    @Override
    public void extraCheese() {
        double s = .30;
        double m = .60;
        double l = .90;
        System.out.printf("Extra Cheese  |   %.2f | %.2f | %.2f" , s,m,l);
        System.out.println(GREEN + "\nAdd Extra Cheese [Y/N] ? : "  + RESET );

        lines();

    }
}
