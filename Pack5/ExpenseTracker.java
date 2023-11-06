package Pack5;

import Pack1.*;
import Pack2.*;
import Pack3.*;
import Pack4.*;

import java.util.Scanner;

public class ExpenseTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize user profile
        System.out.println("Welcome to Expense Tracker, please give your profile details:");

        System.out.print("Name: ");
        String name = sc.nextLine();

        int age = checkNumber("age");

        String phno = checkPhoneNumber();

        System.out.print("Address: ");
        String address = sc.nextLine();

        Profile userProfile = new Profile(name, age, phno, address);

        // Initialize arrays for expenses and incomes
        Expense[] expenses = new Expense[100];
        Income[] incomes = new Income[100];

        // Initialize counters
        int expenseCount = 0;
        int incomeCount = 0;

        // To clear terminal screen, reffered tutorialpoints.com
        System.out.print("\033[H\033[2J");

        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Expense Tracker Menu");
            System.out.println("1. Profile");
            System.out.println("2. Add Expenses");
            System.out.println("3. Add Income");
            System.out.println("4. Display Expenses");
            System.out.println("5. Display Incomes");
            System.out.println("Any other number to exit");

            int choice = checkNumber("choice");

            System.out.print("\033[H\033[2J");

            switch (choice) {
                case 1:
                    System.out.println("Profile Menu");
                    System.out.println("1. Edit Profile");
                    System.out.println("2. Display Profile");
                    int profileChoice = checkNumber("choice");

                    switch (profileChoice) {
                        case 1:
                            System.out.print("Edit your name: ");
                            name = sc.nextLine();
                            System.out.print("Edit your age: \n");
                            age = checkNumber("age");
                            System.out.print("Edit your phone number: \n");
                            phno = checkPhoneNumber();
                            System.out.print("Edit your address: ");
                            address = sc.nextLine();

                            userProfile = new Profile(name, age, phno, address);
                            System.out.println("Profile updated successfully.\n");
                            break;

                        case 2:
                            System.out.println("Displaying Profile:");
                            userProfile.displayProfile();
                            break;

                        default:
                            System.out.println("Invalid choice for Profile.\n");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Select to add your Expense:");
                    System.out.println("1. One-Time Expense");
                    System.out.println("2. Other Expense");
                    System.out.println("3. Timely Expense");
                    System.out.println("Enter any other number to backtrack");
                    int expenseTypeChoice = checkNumber("choice");

                    if (expenseTypeChoice > 3 || expenseTypeChoice < 1) {
                        System.out.println("Going back to main menu\n");
                        break;
                    }

                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    double amount = checkNumber("Amount");
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    switch (expenseTypeChoice) {
                        case 1:
                            expenses[expenseCount] = new OneTimeExpense(description, amount, date);
                            break;
                        case 2:
                            expenses[expenseCount] = new OtherExpense(description, amount, date);
                            break;
                        case 3:
                            System.out.print("Frequency: ");
                            String frequency = sc.nextLine();
                            expenses[expenseCount] = new TimelyExpense(description, amount, date, frequency);
                            break;
                    }

                    if (expenses[expenseCount] != null) {
                        expenseCount++;
                        System.out.println("Expense added successfully.\n");
                    }
                    break;

                case 3:
                    System.out.println("Select Income Type:");
                    System.out.println("1. Salary Income");
                    System.out.println("2. Gift Income");
                    System.out.println("3. Other Income");
                    System.out.println("Enter any other number to backtrack");
                    int incomeTypeChoice = checkNumber("choice");

                    if (incomeTypeChoice > 3 || incomeTypeChoice < 1) {
                        System.out.println("Going back to main menu\n");
                        break;
                    }

                    System.out.print("Source: ");
                    String source = sc.nextLine();
                    double incomeAmount = checkNumber("Amount");
                    System.out.print("Date (YYYY-MM-DD): ");
                    String incomeDate = sc.nextLine();

                    switch (incomeTypeChoice) {
                        case 1:
                            incomes[incomeCount] = new SalaryIncome(source, incomeAmount, incomeDate);
                            break;
                        case 2:
                            incomes[incomeCount] = new GiftIncome(source, incomeAmount, incomeDate);
                            break;
                        case 3:
                            incomes[incomeCount] = new OtherIncome(source, incomeAmount, incomeDate);
                            break;
                    }

                    if (incomes[incomeCount] != null) {
                        incomeCount++;
                        System.out.println("Income added successfully.\n");
                    }
                    break;

                case 4:
                    System.out.println("Display Expenses Menu");
                    System.out.println("1. Display All Expenses");
                    System.out.println("2. Display Expenses by Date");
                    System.out.println("3. Display Total Amount of Expenses");
                    System.out.println("Enter any other number to backtrack");
                    int displayExpenseChoice = checkNumber("choice");

                    switch (displayExpenseChoice) {
                        case 1:
                            System.out.println("\nDisplaying All Expenses:\n");
                            for (int i = 0; i < expenseCount; i++) {
                                expenses[i].displayExpense();
                            }
                            System.out.println();
                            break;
                        case 2:
                            System.out.print("Enter a date (YYYY-MM-DD): ");
                            String displayExpenseDate = sc.nextLine();
                            boolean foundExpenseByDate = false;
                            for (int i = 0; i < expenseCount; i++) {
                                if (expenses[i].date.equals(displayExpenseDate)) {
                                    expenses[i].displayExpense();
                                    foundExpenseByDate = true;
                                }
                            }
                            if (!foundExpenseByDate) {
                                System.out.println("No expenses found for the given date.\n");
                            }
                            break;
                        case 3:
                            double totalExpenseAmount = 0;
                            for (int i = 0; i < expenseCount; i++) {
                                totalExpenseAmount += expenses[i].amount;
                            }
                            System.out.println("Total Amount of Expenses (in Rs): ₹" + totalExpenseAmount + "\n");
                            break;
                        default:
                            System.out.println("Going back to main menu");
                            break;
                    }
                    break;

                case 5:
                    System.out.println("Display Incomes Menu");
                    System.out.println("1. Display All Incomes");
                    System.out.println("2. Display Incomes by Date");
                    System.out.println("3. Display Total Amount of Income");
                    System.out.println("Enter any other number to backtrack");
                    int displayIncomeChoice = checkNumber("choice");

                    switch (displayIncomeChoice) {
                        case 1:
                            System.out.println("\nDisplaying All Incomes:\n");
                            for (int i = 0; i < incomeCount; i++) {
                                incomes[i].displayIncome();
                            }
                            System.out.println();
                            break;
                        case 2:
                            System.out.print("Enter a date (YYYY-MM-DD): ");
                            String displayIncomeDate = sc.nextLine();
                            boolean foundIncomeByDate = false;
                            for (int i = 0; i < incomeCount; i++) {
                                if (incomes[i].date.equals(displayIncomeDate)) {
                                    incomes[i].displayIncome();
                                    foundIncomeByDate = true;
                                }
                            }
                            if (!foundIncomeByDate) {
                                System.out.println("No income found for the given date.\n");
                            }
                            break;
                        case 3:
                            double totalIncomeAmount = 0;
                            for (int i = 0; i < incomeCount; i++) {
                                totalIncomeAmount += incomes[i].amount;
                            }
                            System.out.println("Total Income Amount (in Rs): ₹" + totalIncomeAmount + "\n");
                            break;
                        default:
                            System.out.println("Going back to main menu");
                            break;
                    }
                    break;

                default:
                    System.out.println("Thank you for using Expense Tracker!");
                    sc.close();
                    return;
            }
        }
    }

    // Took from chat-gpt to check if the string contains only numeric values or no.
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static int checkNumber(String x) {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
    
        while (!validInput) {
            System.out.print("Enter " + x + ": ");
            String input = sc.nextLine();
    
            if (isNumeric(input)) {
                int returnInput = Integer.parseInt(input);
                return returnInput;
            } else {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
        // You should never reach this point, so you should return something here to avoid a compilation error.
        return -1; // You can return a default value or throw an exception if needed.
    }

    private static String checkPhoneNumber(){
        Scanner sc = new Scanner(System.in);
        boolean validPhoneNumber = false;

        while (!validPhoneNumber) {
            System.out.print("Phone Number: ");
            String phno = sc.nextLine();

            if (phno.matches("\\d{10}")) {
                return phno;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit numeric phone number:");
            }
        }
        // You should never reach this point, so you should return something here to avoid a compilation error.
        return "0"; // You can return a default value or throw an exception if needed.
    }
    
}
