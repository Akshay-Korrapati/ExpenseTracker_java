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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/ExpenseTrackerDB";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234";

    static {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Failed to load MySQL JDBC driver. Make sure to add the JDBC driver to your project.");
        }
    }

    // Establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    // Inside the DBConnection class
    public static void addExpense(String type, String description, double amount, String date, String frequency)
            throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO Expenses (type, description, amount, date, frequency) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, type);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, amount);
                preparedStatement.setString(4, date);
                preparedStatement.setString(5, frequency);
                preparedStatement.executeUpdate();
            }
        }
    }

    public static void addIncome(String type, String source, double amount, String date) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO Incomes (type, source, amount, date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, type);
                preparedStatement.setString(2, source);
                preparedStatement.setDouble(3, amount);
                preparedStatement.setString(4, date);
                preparedStatement.executeUpdate();
            }
        }
    }

}

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize user profile
        System.out.println("Welcome to Expense Tracker, please give your profile details:");

        System.out.print("Name: ");
        String name = sc.nextLine();
        int age = 0;
        boolean validAgeInput = false;

        while (!validAgeInput) {
            System.out.print("Age: ");
            String ageInput = sc.nextLine();

            if (isNumeric(ageInput)) {
                age = Integer.parseInt(ageInput);
                validAgeInput = true;
            } else {
                System.out.println("Invalid age input. Please enter a valid number:");
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
                System.out.println("Invalid phone number. Please enter a 10-digit numeric phone number:");
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
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            System.out.print("\033[H\033[2J");

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

                            if (isNumeric(editedAgeInput)) {
                                age = Integer.parseInt(editedAgeInput);
                            } else {
                                System.out.println("Invalid age input. Please enter a valid number:");
                                continue;
                            }

                            System.out.print("Edit your phone number: ");
                            String editedPhno = sc.nextLine();

                            if (editedPhno.matches("\\d{10}")) {
                                phno = editedPhno;
                            } else {
                                System.out.println(
                                        "Invalid phone number. Please enter a 10-digit numeric phone number:");
                            }

                            System.out.print("Edit your address: ");
                            address = sc.nextLine();

                            userProfile = new Profile(name, age, phno, address);
                            System.out.println("Profile updated successfully.\n");
                            break;

                        case 2:
                            System.out.println("Displaying Profile:");
                            System.out.println("Name: " + userProfile.name);
                            System.out.println("Age: " + userProfile.age);
                            System.out.println("Phone Number: " + userProfile.phno);
                            System.out.println("Address: " + userProfile.address + "\n");
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
                    System.out.print("Enter your choice: ");
                    int expenseTypeChoice = Integer.parseInt(sc.nextLine());

                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    double amount = 0.0;
                    boolean validAmount = false;
                    while (!validAmount) {
                        System.out.print("Amount (in Rs): ");
                        String amountInput = sc.nextLine();

                        if (isNumeric(amountInput)) {
                            amount = Double.parseDouble(amountInput);
                            validAmount = true;
                        } else {
                            System.out.println("Invalid amount input. Please enter a valid number.\n");
                        }
                    }
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    switch (expenseTypeChoice) {
                        case 1:
                            // expenses[expenseCount] = new OneTimeExpense(description, amount, date);
                            addExpenseToDatabase("One-Time Expense", description, amount, date);
                            break;
                        case 2:
                            // expenses[expenseCount] = new OtherExpense(description, amount, date);
                            addExpenseToDatabase("Other Expense", description, amount, date);
                            break;
                        case 3:
                            // expenses[expenseCount] = new TimelyExpense(description, amount, date,
                            // frequency);
                            System.out.print("Frequency: ");
                            String frequency = sc.nextLine();
                            addExpenseToDatabase("Timely Expense", description, amount, date, frequency);
                            break;
                        default:
                            System.out.println("Going back to the main menu\n");
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
                    System.out.print("Enter your choice: ");
                    int incomeTypeChoice = Integer.parseInt(sc.nextLine());

                    System.out.print("Source: ");
                    String source = sc.nextLine();
                    double incomeAmount = 0.0;
                    boolean validIncomeAmount = false;
                    while (!validIncomeAmount) {
                        System.out.print("Amount (in Rs): ");
                        String incomeAmountInput = sc.nextLine();

                        if (isNumeric(incomeAmountInput)) {
                            incomeAmount = Double.parseDouble(incomeAmountInput);
                            validIncomeAmount = true;
                        } else {
                            System.out.println("Invalid amount input. Please enter a valid number.\n");
                        }
                    }
                    System.out.print("Date (YYYY-MM-DD): ");
                    String incomeDate = sc.nextLine();

                    switch (incomeTypeChoice) {
                        case 1:
                            // incomes[incomeCount] = new SalaryIncome(source, incomeAmount, incomeDate);
                            addIncomeToDatabase("Salary Income", source, incomeAmount, incomeDate);
                            break;
                        case 2:
                            // incomes[incomeCount] = new GiftIncome(source, incomeAmount, incomeDate);
                            addIncomeToDatabase("Gift Income", source, incomeAmount, incomeDate);
                            break;
                        case 3:
                            // incomes[incomeCount] = new OtherIncome(source, incomeAmount, incomeDate);
                            addIncomeToDatabase("Other Income", source, incomeAmount, incomeDate);
                            break;
                        default:
                            System.out.println("Going back to the main menu\n");
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
                    System.out.print("Enter your choice: ");
                    int displayExpenseChoice = Integer.parseInt(sc.nextLine());

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
                    System.out.print("Enter your choice: ");
                    int displayIncomeChoice = Integer.parseInt(sc.nextLine());

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

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
