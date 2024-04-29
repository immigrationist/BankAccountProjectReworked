import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String name = "";
        char gender = ' ';
        int age = 0;
        int phoneNumber = 0;
        String address = "";
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        Bank bank = new Bank();
        Person person = new Person(name, gender, age, address, phoneNumber);

        int options = 0;
        try {
            bank.loadAccountsFromFile("/Users/ahmetberky/Desktop/saved.txt");
        }
        catch(Exception e){
            System.out.println("No accounts to load");
        }

        while(options != 7) {

            System.out.println("\nBank Operations:");
            System.out.println("1. Create New Account");
            System.out.println("2. Perform Operations in an existing account");
            System.out.println("3. Delete an existing account");
            System.out.println("4. Display the average of all account balances");
            System.out.println("5. Display the maximum and minimum account balances");
            System.out.println("6. Display all accounts that have low balance");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            options = scanner.nextInt();

            switch (options) {
                case 1:
                    // Create New Account
                    System.out.println("\nEnter Personal Information:");
                    System.out.println("Name: ");
                    person.setName(scanner.next());
                    System.out.println("Gender");
                    person.setGender(scanner.next().charAt(0));
                    System.out.println("Age: ");
                    person.setAge(scanner.nextInt());
                    System.out.println("Phone Number: ");
                    person.setPhoneNumber(scanner.nextInt());
                    System.out.println("Home Address: ");
                    person.setHomeAddress(scanner.next());

                    Person person1 = new Person(name, gender, age, address, phoneNumber);

                    account.setAccountHolder(person1);
                    System.out.print("Enter account number: ");
                    account.setAccountNumber(scanner.nextDouble());
                    System.out.print("Enter initial balance: ");
                    account.setBalance(scanner.nextDouble());
                    System.out.print("Enter date created: ");
                    account.setDateCreated(scanner.nextInt());
                    System.out.print("Enter withdraw limit: ");
                    account.setWithdrawLimit(scanner.nextDouble());
                    bank.addAccount(new Account(account.getAccountNumber(), account.getBalance(),
                            account.getDateCreated(), account.getWithdrawLimit()));
                    System.out.println("Account created successfully!");
                    break;
                case 2:
                    // Perform Operations in an existing account
                    System.out.print("Enter account number: ");
                    int existingAccNumber = scanner.nextInt();
                    Account bankAccount = bank.findAccount(existingAccNumber);
//                    bankAccount.setAccountHolder(person);


                    if (bankAccount != null ) {

                        System.out.println("Account matched to: \n");
                        System.out.println(bankAccount.toString());

                        int choice = 0;

                        while (choice != 4) {

                            boolean success;
                            System.out.println();
                            System.out.println("Please choose from 4 of the selections:");
                            System.out.println("1. DEPOSIT MONEY" + "\n2. WITHDRAW MONEY" +
                                    "\n3. CHECK BALANCE" + "\n4. QUIT");
                            choice = scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    System.out.println("Enter amount to deposit");
                                    success = bankAccount.depositMoney(scanner.nextInt());
                                    if (success) {
                                        System.out.println("Current Balance: " + bankAccount.getBalance());
                                    } else System.out.println("Invalid Amount!");
                                    break;
                                case 2:
                                    System.out.println("Enter amount, your limit is: " +
                                            bankAccount.getWithdrawLimit());
                                    success = bankAccount.withdrawMoney(scanner.nextInt());
                                    if (success) {
                                        System.out.println("Current Balance: " + bankAccount.getBalance());
                                    } else
                                        System.out.println("Limit reached, balance is: " + bankAccount.getBalance());
                                    break;
                                case 3:
                                    System.out.println(bankAccount.getBalance());
                                    break;
                                case 4:
                                    System.out.println("RENDERING INFORMATION...");
                                    break;
                                default:
                            }
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number to delete: ");
                    boolean deleted = bank.deleteAccount(scanner.nextInt());
                    if (deleted) {
                        System.out.println("Account deleted successfully!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 4:
                    System.out.println("Average balance of all accounts: " + bank.getAverageBalance());
                    break;
                case 5:
                    System.out.println("Maximum balance: " + bank.getMaximumBalance());
                    System.out.println("Minimum balance: " + bank.getMinimumBalance());
                    break;
                case 6:
                    System.out.print("Enter balance threshold: ");
                    double balanceThreshold = scanner.nextDouble();
                    ArrayList<Account> lowBalanceAccounts = bank.getLowBalanceAccounts(balanceThreshold);
                    System.out.println("Accounts with balance below " + balanceThreshold + ":");
                    for (Account acc : lowBalanceAccounts) {
                        System.out.println("Account Number: " + acc.getAccountNumber() + ", Balance: " + acc.getBalance());
                    }
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    try {
                        bank.saveAccountsToFile("/Users/ahmetberky/Desktop/saved.txt");
                    }
                    catch(Exception e){
                        System.out.println("Unable to save accounts to file...");
                    }
                    break;
                default:
            }
        }
    }
}
