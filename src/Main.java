import java.util.*;

public class Main {
    public static void main(String[] args){
        DBConnector.initDBConnection();
        Scanner sc= new Scanner(System.in);
        Random rand = new Random();

        System.out.println("+----------------------------+");
        System.out.println("| Welcome to IKLC Bank       |");
        System.out.println("+----------------------------+");
        System.out.println("| 1. Register Account        |");
        System.out.println("| 2. Transfer                |");
        System.out.println("| 3. Pay In                  |");
        System.out.println("| 4. Withdraw                |");
        System.out.println("| 5. Balance Check           |");
        System.out.println("| 6. Transaction History     |");
        System.out.println("+----------------------------+");
        System.out.print("| Choose : ");
        int n = sc.nextInt();
        System.out.println("+------------+");

        switch (n) {
            case 1 -> {
                System.out.println("+------------------+");
                System.out.println("| Register Account |");
                System.out.println("+------------------+");
                System.out.println("Input your data below!");
                System.out.println();
                System.out.print("Name          : ");
                sc.nextLine();
                String customer = sc.nextLine();
                System.out.print("Mother Name   : ");
                String mother = sc.nextLine();
                System.out.print("First Deposit : ");
                double deposit = sc.nextDouble();

                int number = rand.nextInt(900000) + 100000;

                Register register = new Register();
                register.setCustomerName(customer);
                register.setMotherName(mother);
                register.setAccountNumber(number);
                register.setBalance(deposit);
                register.insertDataRegister();
                register.addAccount(register);

                System.out.println("Account Created");
            }
            case 2 -> {
                Account.loadAccount();
                Transfer transfer = new Transfer();
                System.out.println("+-----------------+");
                System.out.println("|  Transfer Menu  |");
                System.out.println("+-----------------+");
                System.out.println("Please fill some information below !");

                System.out.print("Account Number : ");
                int customer_account = sc.nextInt();
                System.out.println("(Your current balance is " + String.format("%,.2f",transfer.getBalance(customer_account)) +" )");
                System.out.println();
                System.out.print("Recipient's Account Number : ");
                int recipient_account = sc.nextInt();
                double transfer_amount = 0.0;
                boolean validInput = false;

                    while (!validInput) {
                        try {
                            System.out.print("Transfer amount : ");
                            transfer_amount = sc.nextDouble();
                            validInput = true;
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.next();
                        }
                    }

                boolean customerFound = false;
                boolean recipientFound = false;

                for (Account account : Account.accountList) {
                    if (account.accountNumber == customer_account) {
                        customerFound = true;
                    }
                    if (account.accountNumber == recipient_account) {
                        recipientFound = true;
                    }
                }

                if (customerFound && recipientFound) {
                    transfer.transfer(customer_account, recipient_account, transfer_amount);
                } else {
                    System.out.println("One or more accounts not found.");
                }
            }
            case 3 -> {
                Account.loadAccount();
                PayIn pay = new PayIn();
                System.out.println("+-----------------+");
                System.out.println("|   Pay In Menu   |");
                System.out.println("+-----------------+");
                System.out.println("Please fill some information below !");
                System.out.println();
                System.out.print("Account Number : ");
                int customer_account = sc.nextInt();
                double transfer_amount = 0.0;
                boolean validInput = false;

                    while (!validInput) {
                        try {
                            System.out.print("Transfer amount : ");
                            transfer_amount = sc.nextDouble();
                            validInput = true;
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.next();
                        }
                    }

                boolean customerFound = false;

                    for (Account account : Account.accountList) {
                        if (account.accountNumber == customer_account) {
                            customerFound = true;
                            pay.pay(customer_account, transfer_amount);
                        }
                    }
                    if (!customerFound) {
                        System.out.println("Account not found.");
                    }
            }
            case 4 -> {
                Account.loadAccount();
                Withdraw withdraw = new Withdraw();
                System.out.println("+-------------------+");
                System.out.println("|   Withdraw Menu   |");
                System.out.println("+-------------------+");
                System.out.println("Please fill some information below !");
                System.out.println();
                System.out.print("Account Number : ");
                int customer_account = sc.nextInt();
                double withdraw_amount = 0.0;
                boolean validInput = false;

                while (!validInput) {
                    try {
                        System.out.print("Withdraw amount : ");
                        withdraw_amount = sc.nextDouble();
                        validInput = true;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.next();
                    }
                }

                boolean customerFound = false;

                    for (Account account : Account.accountList) {
                        if (account.accountNumber == customer_account) {
                            customerFound = true;
                            withdraw.withdraw(customer_account, withdraw_amount);
                        }
                    }
                    if (!customerFound) {
                        System.out.println("Account not found.");
                    }
            }
            case 5 -> {
                Account.loadAccount();
                Withdraw balance = new Withdraw();
                System.out.println("+-------------------+");
                System.out.println("|   Check Balance   |");
                System.out.println("+-------------------+");
                System.out.println("Please fill some information below !");
                System.out.println();
                System.out.print("Account Number  : ");
                int customer_account = sc.nextInt();

                boolean customerFound = false;

                    for (Account account : Account.accountList) {
                        if (account.accountNumber == customer_account) {
                            customerFound = true;
                            System.out.println("Customer Account : " + customer_account);
                            System.out.println("Balance          : " + String.format("%,.2f", balance.getBalance(customer_account)));
                        }
                    }
                    if (!customerFound) {
                        System.out.println("Account not found.");
                    }
            }
            case 6 -> {
                Account.loadAccount();
                History history = new History();
                System.out.println("+-------------------------+");
                System.out.println("|   Transaction History   |");
                System.out.println("+-------------------------+");
                System.out.println("Please fill some information below !");
                System.out.println();
                System.out.print("Account Number  : ");
                int customer_account = sc.nextInt();
                boolean customerFound = false;

                    for (Account account : Account.accountList) {
                        if (account.accountNumber == customer_account) {
                            customerFound = true;
                            history.showHistory(customer_account);
                        }
                    }
                    if (!customerFound) {
                        System.out.println("Account not found.");
                    }
            }
            default -> throw new IllegalStateException("Unexpected value: " + n);
        }
    }
}