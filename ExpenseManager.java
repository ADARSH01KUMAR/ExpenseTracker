package service;

import model.Transaction;

import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {
    private List<Transaction> transactions;

    public ExpenseManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void loadTransactions(List<Transaction> list) {
        transactions.addAll(list);
    }

    public void showMonthlySummary(int month, int year) {
        System.out.println("\n--- Monthly Summary for " + month + "/" + year + " ---");

        double totalIncome = 0, totalExpense = 0;
        Map<String, Double> categoryWise = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue() == month && t.getDate().getYear() == year) {
                if (t.getType().equalsIgnoreCase("Income")) {
                    totalIncome += t.getAmount();
                } else {
                    totalExpense += t.getAmount();
                }

                categoryWise.put(
                        t.getCategory(),
                        categoryWise.getOrDefault(t.getCategory(), 0.0) + t.getAmount()
                );
            }
        }

        System.out.println("Total Income: ₹" + totalIncome);
        System.out.println("Total Expense: ₹" + totalExpense);
        System.out.println("Category-wise breakdown:");
        for (Map.Entry<String, Double> entry : categoryWise.entrySet()) {
            System.out.println("- " + entry.getKey() + ": ₹" + entry.getValue());
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}
