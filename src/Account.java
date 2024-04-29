public class Account extends Person
{
    private double accountNumber;
    private Person accountHolder;
    private int dateCreated;
    private double balance;
    private double withdrawMoney;

    private double withdrawLimit;
//    private final String message = "\nAccount Number, Balance, Date Created, Withdraw Limit ";

    public Account()
    {
        super("",' ', 0, "",0);
        this.dateCreated = 0;
        this.balance = 0;
        this.withdrawLimit = 0;
        this.accountNumber = 0;
        withdrawMoney = 0;
    }
    public Account(double accountNumber, double balance,
                   int dateCreated, double withdrawLimit) {
        super("",' ', 0, "",0);
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dateCreated = dateCreated;
        this.withdrawLimit = withdrawLimit;

        withdrawMoney = 0;
    }


    public double getAccountNumber()
    {
        return accountNumber;
    }
    public void setAccountNumber(double accountNumber)
    {
        if(accountNumber > 0)
            this.accountNumber = accountNumber;
    }

    public void setAccountHolder(Person accountHolder)
    {
        if (accountHolder != null)
            this.accountHolder = accountHolder;
    }

    public Person getAccountHolder() {
        return accountHolder;
    }

    public int getDateCreated()
    {
        return dateCreated;
    }
    public void setDateCreated(int dateCreated)
    {
        if(dateCreated > 0)
            this.dateCreated = dateCreated;
    }

    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double currBalance)
    {
        if(currBalance > 0)
            this.balance = currBalance;
    }

    public double getWithdrawLimit()
    {

        return withdrawLimit;
    }
    public void setWithdrawLimit(double withdrawLimit)
    {
        if(withdrawLimit > 0)
            this.withdrawLimit = withdrawLimit;
    }

    public boolean withdrawMoney(double newMoney)
    {
        boolean success = false;

        if(balance >= newMoney && withdrawMoney + newMoney <= withdrawLimit)
        {

            balance -= newMoney;
            withdrawMoney += newMoney;
            success = true;
        }

        return  success;
    }

    public boolean depositMoney(double newMoney)
    {
        boolean success;
        if(newMoney > 0) {
            balance += newMoney;
            success = true;
        }
        else success = false;

        return success;
    }

    public String toString()
    {
        String info = "";
        info += "\n ACCOUNT NUMBER: " + getAccountNumber();

        info += "\n DATE CREATED: " + getDateCreated();
        info += "\n CURRENT BALANCE: " + getBalance() + "\n WITHDRAW LIMIT: " + getWithdrawLimit();
//      info += "\n" + getAccountHolder();
        return info;
    }

    public String writeToFile() {
        return accountNumber + ", " + balance + ", " + dateCreated + ", " + withdrawLimit;

    }
    public static Account readFromFile(String fileText) {
        String[] objects = fileText.split(", ");
        // since we have a total of 4 objects we will be using length == 4
        if (objects.length == 4) {
            double accountNumber = Double.parseDouble(objects[0]);
            double balance = Double.parseDouble(objects[1]);
            int dateCreated = Integer.parseInt(objects[2]);
            double withdrawLimit = Double.parseDouble(objects[3]);

            return new Account(accountNumber, balance, dateCreated, withdrawLimit);
        }
        return null; // Invalid data format
    }


}


