package org.pluralsight.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncludedToppings {

    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public String size;

    Scanner scanner = new Scanner(System.in);
    private final List<Sand<ITopping>> sandwichOrders = new ArrayList<>();
    Sand<ITopping> sandwich = new Sand<>(size);
    List<Toppings> veggies = new ArrayList<>();



    public void freeTop(){
        veggies.clear();
        veggies.add(new Toppings("Lettuce"));
        veggies.add(new Toppings("Peppers"));
        veggies.add(new Toppings("Onions"));
        veggies.add(new Toppings("Tomatoes"));
        veggies.add(new Toppings("Jalapenos"));
        veggies.add(new Toppings("Cucumbers"));
        veggies.add(new Toppings("Pickles"));


        //String[] regularToppings = {"lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles"};


 //       System.out.println("\nChoose regular toppings (enter numbers separated by commas):");
//        for (int i = 0; i < veggies.size(); i++) {
//            System.out.println((i + 1) + ". " + veggies.g);
//        }
//        int c = 1;
//        for(Toppings t : veggies){
//            System.out.println(c + ". " + t.getName() + "\n");
//            c++;
//        }
//
//        System.out.print("Your choices: ");
//        String[] choices = scanner.nextLine().split(",");
//
//        List<String> selectedToppings = Arrays.stream(choices)
//                .map(String::trim)
//                .filter(choice -> choice.matches("\\d+"))
//                .map(Integer::parseInt)
//                .filter(i -> i >= 1 && i <= veggies.size())
//                .map(i -> veggies.get(i - 1).getName())  // <-- fixed here
//                .distinct()
//                .toList();
//
//        for (String topping : selectedToppings) {
//            sandwich.addTopping(new BasicTopping(topping));
//        }
        String[] regularToppings = {"lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles"};
        System.out.println("\nChoose regular toppings (enter numbers separated by commas):");
        for (int i = 0; i < regularToppings.length; i++) {
            System.out.println((i + 1) + ". " + regularToppings[i]);
        }
        System.out.print("Your choices: ");
        String[] toppingChoices = scanner.nextLine().split(",");
        List<String> selectedToppings = Arrays.stream(toppingChoices)
                .map(String::trim)
                .filter(choice -> choice.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(i -> i >= 1 && i <= regularToppings.length)
                .map(i -> regularToppings[i - 1])
                .distinct()
                .collect(Collectors.toList());

        for (String topping : selectedToppings) {
            sandwich.addTopping(new BasicTopping(topping));
        }


// Sauces Selection
        String[] sauces = {"mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"};
        System.out.println("\nChoose sauces (enter numbers separated by commas):");
        for (int i = 0; i < sauces.length; i++) {
            System.out.println((i + 1) + ". " + sauces[i]);
        }
        System.out.print("Your choices: ");
        String[] sauceChoices = scanner.nextLine().split(",");
        List<String> selectedSauces = Arrays.stream(sauceChoices)
                .map(String::trim)
                .filter(choice -> choice.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(i -> i >= 1 && i <= sauces.length)
                .map(i -> sauces[i - 1])
                .distinct()
                .toList();

        for (String sauce : selectedSauces) {
            sandwich.addTopping(new BasicTopping(sauce));
        }

// Sides Selection
        String[] sides = {"au jus", "sauce"};
        System.out.println("\nChoose sides (enter numbers separated by commas):");
        for (int i = 0; i < sides.length; i++) {
            System.out.println((i + 1) + ". " + sides[i]);
        }
        System.out.print("Your choices: ");
        String[] sideChoices = scanner.nextLine().split(",");
        List<String> selectedSides = Arrays.stream(sideChoices)
                .map(String::trim)
                .filter(choice -> choice.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(i -> i >= 1 && i <= sides.length)
                .map(i -> sides[i - 1])
                .distinct()
                .toList();

        for (String side : selectedSides) {
            sandwich.addTopping(new BasicTopping(side));
        }

    }





}
