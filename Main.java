import model.Transaction;
import service.ExpenseManager;
import io.FileHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);
        String filePath = "data/transactions.txt";

        List<Transaction> loaded = FileHandler.readFromFile(filePath);
        manager.loadTransactions(loaded);
        System.out.println("Loaded " + loaded.size() + " transactions from file.");

        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.println("4. Save & Exit");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                case 2:
                    String type = (option == 1) ? "Income" : "Expense";

                    System.out.print("Enter date (yyyy-mm-dd): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    System.out.print("Enter category (e.g. Salary/Food/Rent): ");
                    String category = sc.nextLine();

                    System.out.print("Enter amount: ₹");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    Transaction t = new Transaction(date, type, category, amount);
                    manager.addTransaction(t);
                    System.out.println("Transaction added successfully.");
                    break;

                case 3:
                    System.out.print("Enter month (1-12): ");
                    int month = sc.nextInt();
                    System.out.print("Enter year (e.g., 2025): ");
                    int year = sc.nextInt();
                    manager.showMonthlySummary(month, year);
                    break;

                case 4:
                    FileHandler.writeToFile(filePath, manager.getAllTransactions());
                    System.out.println("Data saved. Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
