import java.io.*;
import java.util.ArrayList;

public class Bank extends Account{
    private ArrayList<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {

        if(account != null){
            accounts.add(account);
        }

    }

    public Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null; // Account not found
    }

    public boolean deleteAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
            return true; // Account deleted successfully
        }
        return false; // Account not found
    }

    public double getAverageBalance() {
        if (accounts.isEmpty()) return 0.0;

        double totalBalance = 0.0;
        for (Account acc : accounts) {
            totalBalance += acc.getBalance();
        }
        return totalBalance / accounts.size();
    }

    public double getMaximumBalance() {
        if (accounts.isEmpty()) return 0.0;

        double maxBalance = Double.MIN_VALUE;
        for (Account acc : accounts) {
            maxBalance = Math.max(maxBalance, acc.getBalance());
        }
        return maxBalance;
    }

    public double getMinimumBalance() {
        if (accounts.isEmpty()) return 0.0;

        double minBalance = Double.MAX_VALUE;
        for (Account acc : accounts) {
            minBalance = Math.min(minBalance, acc.getBalance());
        }
        return minBalance;
    }
    public ArrayList<Account> getLowBalanceAccounts(double balanceThreshold) {
        ArrayList<Account> lowBalanceAccounts = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getBalance() < balanceThreshold) {
                lowBalanceAccounts.add(acc);
            }
        }
        return lowBalanceAccounts;
    }
    public void saveAccountsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Account account : accounts) {
                writer.println(account.writeToFile());
            }
            System.out.println("Accounts saved to " + fileName);
        } catch (Exception e) {
            System.out.println("Error saving accounts to file");
        }
    }

    // Method to load accounts from a file
    public void loadAccountsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = Account.readFromFile(line);
                if (account != null) {
                    accounts.add(account);
                }
            }
            System.out.println("Accounts loaded from " + fileName);
        } catch (Exception e) {
            System.out.println("Error loading accounts from file");
        }
    }
}
