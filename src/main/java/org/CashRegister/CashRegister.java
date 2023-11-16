package org.CashRegister;
import java.util.ArrayList;
import java.util.Scanner;


    public class CashRegister {
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    // User's available budget
    double budget = 300.0;
    // Total cost of items the user has purchased
    double totalCost = 0.0;
    boolean exitRequested = false;
        // A list containing the various available items

        ArrayList<Item> availableItems = new ArrayList<>();
        // Adding different items to the list
        availableItems.add(new Item("Item1", 50.0));
        availableItems.add(new Item("Item2", 75.0));
        availableItems.add(new Item("Item3", 100.0));
        availableItems.add(new Item("Discount Coupon", 0.0)); // Special item for discount

        System.out.println("Welcome to the cash register!");
        System.out.println("Your budget is: " + budget + " SEK");

        while (!exitRequested) {
            System.out.println("Choose an item to purchase (or exit with 'exit'):");
            // Printing the available items
            for (int i = 0; i < availableItems.size(); i++) {
                System.out.println((i + 1) + ". " + availableItems.get(i));
            }

            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                exitRequested = true;
            } else if (choice.equalsIgnoreCase("Discount Coupon")) {
                // Here we apply the discount code and logic
                System.out.println("Enter discount code (or confirm that you want to apply the discount): ");
                String discountCode = scanner.nextLine();

                if (discountCode.equals("DISCOUNT123") || discountCode.equalsIgnoreCase("confirm")) {
                    double discount = totalCost * 0.2; // 20% discount
                    totalCost -= discount;
                    System.out.println("The discount has been applied. Your new total cost is: " + totalCost + " SEK");
                } else {
                    System.out.println("Invalid discount code. The discount has not been applied.");
                }
            } else {
                try {
                    int itemIndex = Integer.parseInt(choice) - 1;
                    if (itemIndex >= 0 && itemIndex < availableItems.size()) {
                        Item selectedItem = availableItems.get(itemIndex);
                        double itemPrice = selectedItem.getPrice();
                        if (budget >= itemPrice) {
                            totalCost += itemPrice;
                            budget -= itemPrice;
                            System.out.println("You have purchased: " + selectedItem);
                            System.out.println("Your total cost is now: " + totalCost + " SEK");
                            System.out.println("You have remaining: " + budget + " SEK");
                        } else {
                            System.out.println("You don't have enough money to purchase this item.");
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please try again.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. Negative numbers are not allowed.");
                }


                // After the user has chosen to exit (outside the loop)i
                if (budget < 0) {
                    // The user cannot afford it, display an error message
                    System.out.println("Sorry, you don't have enough money to complete your purchase.");
                } else {
                    // The user can afford it, show a summary of their purchases
                    System.out.println("Thank you for shopping with us!");
                    System.out.println("Here's what you've purchased:");
                    for (Item purchasedItem : availableItems) {
                        System.out.println(purchasedItem.getName() + " - " + purchasedItem.getPrice() + " SEK");
                    }
                    System.out.println("Total cost: " + totalCost + " SEK");
                    System.out.println("Remaining budget: " + budget + " SEK");
                }
            }
        }
    }   }


