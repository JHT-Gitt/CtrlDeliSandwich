package org.pluralsight.Models;

import org.pluralsight.Interface.ITopping;

import java.util.*;

public class IncludedToppings {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    private final Scanner scanner = new Scanner(System.in);
    private final Sandwich<ITopping> sandwich;
    public String indent = "            ";

    public IncludedToppings(Sandwich<ITopping> sandwich) {
        this.sandwich = sandwich;
    }
    public void lines(){
        System.out.println("===========================================");

    }

    public void freeTop() {
        List<String> veggies = Arrays.asList("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapenos", "Cucumbers", "Pickles");

        System.out.println(indent + "🌿 " + YELLOW + "REGULAR TOPPINGS" + RESET + " 🌿");
        lines();
        printOptions(veggies);

        List<String> selected = promptSelection(veggies);
        for (String topping : selected) {
            sandwich.addTopping(new BasicTopping(topping));
            System.out.println(GREEN + topping + RESET + " added.");
        }

        addSauce();
        addSides();
    }

    private void addSauce() {
        List<String> sauces = Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette");

        lines();
        System.out.println(indent + "🧴 " + YELLOW + "SAUCES" + RESET + " 🧴");
        lines();
        printOptions(sauces);

        List<String> selected = promptSelection(sauces);
        for (String sauce : selected) {
            sandwich.addTopping(new BasicTopping(sauce));
            System.out.println(GREEN + sauce + RESET + " added.");
        }
    }

    private void addSides() {
        List<String> sides = Arrays.asList("Au Jus", "Sauce");
        lines();
        System.out.println(indent + "🍟 " + YELLOW + "SIDES" + RESET + " 🍟");
        lines();
        printOptions(sides);

        List<String> selected = promptSelection(sides);
        for (String side : selected) {
            sandwich.addTopping(new BasicTopping(side));
            System.out.println(GREEN + side + RESET + " added.");
        }
    }

    private void printOptions(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf(indent + " %d - %s\n", i + 1, items.get(i));
        }
    }

    private List<String> promptSelection(List<String> options) {
        List<String> selected = new ArrayList<>();
        lines();
        while (true) {
            System.out.print("Select a number (press 0 when done): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) break;

            if (choice >= 1 && choice <= options.size()) {
                String item = options.get(choice - 1);
                if (!selected.contains(item)) {
                    selected.add(item);
                } else {
                    System.out.println("Already selected.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
        return selected;
    }
}
