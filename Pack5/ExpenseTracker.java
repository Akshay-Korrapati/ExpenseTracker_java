package Pack5;

import Pack1.Expense;
import Pack1.Income;
import Pack2.OneTimeExpense;
import Pack2.OtherExpense;
import Pack2.TimelyExpense;
import Pack3.SalaryIncome;
import Pack3.GiftIncome;
import Pack3.OtherIncome;
import Pack4.Profile;

import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize user profile
        System.out.println("Welcome to Expense Tracker, please give your profile details:");

        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = 0;
        boolean validAgeInput = false;

        while (!validAgeInput) {
            System.out.print("Age: ");
            String ageInput = sc.nextLine();

            if (ageInput.matches("\\d+")) {
                age = Integer.parseInt(ageInput);
                validAgeInput = true;
            } else {
                System.out.println("Invalid age input. Please enter a valid number.");
            }
        }
        String phno = "";
        boolean validPhoneNumber = false;

        while (!validPhoneNumber) {
            System.out.print("Phone Number: ");
            phno = sc.nextLine();

            if (phno.matches("\\d{10}")) {
                validPhoneNumber = true;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit numeric phone number.");
            }
        }
        System.out.print("Address: ");
        String address = sc.nextLine();

        Profile userProfile = new Profile(name, age, phno, address);

        // Initialize arrays for expenses and incomes
        Expense[] expenses = new Expense[100];
        Income[] incomes = new Income[100];

        // Initialize counters
        int expenseCount = 0;
        int incomeCount = 0;

        boolean continueManaging = true;

        while (continueManaging) {
            System.out.println("Expense Tracker Menu");
            System.out.println("1. Profile");
            System.out.println("2. Expenses");
            System.out.println("3. Income");
            System.out.println("4. Display Expenses");
            System.out.println("5. Display Incomes");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Profile Menu");
                    System.out.println("1. Edit Profile");
                    System.out.println("2. Display Profile");
                    System.out.print("Enter your choice: ");
                    int profileChoice = Integer.parseInt(sc.nextLine());

                    switch (profileChoice) {
                        case 1:
                            System.out.print("Edit your name: ");
                            name = sc.nextLine();
                            System.out.print("Edit your age: ");
                            String editedAgeInput = sc.nextLine();

                            if (editedAgeInput.matches("\\d+")) {
                                age = Integer.parseInt(editedAgeInput);
                            } else {
                                System.out.println("Invalid age input. Please enter a valid number.");
                                continue;
                            }

                            System.out.print("Edit your phone number: ");
                            String editedPhno = sc.nextLine();

                            if (editedPhno.matches("\\d{10}")) {
                                phno = editedPhno;
                            } else {
                                System.out.println("Invalid phone number. Please enter a 10-digit numeric phone number.");
                                continue;
                            }

                            System.out.print("Edit your address: ");
                            address = sc.nextLine();

                            userProfile = new Profile(name, age, phno, address);
                            System.out.println("Profile updated successfully.");
                            break;

                        case 2:
                            System.out.println("Displaying Profile:");
                            System.out.println("Name: " + userProfile.name);
                            System.out.println("Age: " + userProfile.age);
                            System.out.println("Phone Number: " + userProfile.phno);
                            System.out.println("Address: " + userProfile.address);
                            break;

                        default:
                            System.out.println("Invalid choice for Profile.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Select to add your Expense:");
                    System.out.println("1. One-Time Expense");
                    System.out.println("2. Other Expense");
                    System.out.println("3. Timely Expense");
                    System.out.print("Enter your choice: ");
                    int expenseTypeChoice = Integer.parseInt(sc.nextLine());

                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Amount: ");
                    double amount = Double.parseDouble(sc.nextLine());
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
                        default:
                            System.out.println("Invalid choice for Expense Type.");
                            break;
                    }

                    if (expenses[expenseCount] != null) {
                        expenseCount++;
                        System.out.println("Expense added successfully.");
                    }
                    break;

                case 3:
                    System.out.println("Select Income Type:");
                    System.out.println("1. Salary Income");
                    System.out.println("2. Gift Income");
                    System.out.println("3. Other Income");
                    System.out.print("Enter your choice: ");
                    int incomeTypeChoice = Integer.parseInt(sc.nextLine());

                    System.out.print("Source: ");
                    String source = sc.nextLine();
                    System.out.print("Amount: ");
                    double incomeAmount = Double.parseDouble(sc.nextLine());
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
                        default:
                            System.out.println("Invalid choice for Income Type.");
                            break;
                    }

                    if (incomes[incomeCount] != null) {
                        incomeCount++;
                        System.out.println("Income added successfully.");
                    }
                    break;

                case 4:
                    System.out.println("Displaying Expenses:");
                    for (int i = 0; i < expenseCount; i++) {
                        expenses[i].display();
                    }
                    break;

                case 5:
                    System.out.println("Displaying Incomes:");
                    for (int i = 0; i < incomeCount; i++) {
                        incomes[i].display();
                    }
                    break;

                case 6:
                    continueManaging = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }

        System.out.println("Thank you for using Expense Tracker!");
        sc.close();
    }
}
