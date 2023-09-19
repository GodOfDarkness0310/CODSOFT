import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;
    private String password;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 1000.0; // Initialize balance to zero
        this.password = null; // Initially, there's no password set
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

public class ATM {
    private Map<String, BankAccount> accounts;
    private String loggedInAccount; // Store the currently logged-in account
    private Scanner scanner; // Declare a single Scanner for input

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in); // Initialize the Scanner
        this.loggedInAccount = null; // Initially, no one is logged in
    }

    public void clearConsole() {
        // Clear the console screen
        try {
            Thread.sleep(1050);
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final IOException | InterruptedException e) {
            // Handle exceptions
        }
    }

    public void login(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            // Account doesn't exist, create a new one
            account = new BankAccount(accountNumber);
            accounts.put(accountNumber, account);
        }

        String password = account.getPassword();

        if (password == null) {
            // Password is not set, ask the user to set it
            System.out.println("Please set a password for your account (Warning: Password cannot be changed later): ");
            String newPassword = scanner.nextLine();
            account.setPassword(newPassword);
            System.out.println("Password set successfully.");
        } else {
            // Password is already set, ask for it to log in
            System.out.println("Enter your password:");
            String enteredPassword = scanner.nextLine();

            if (!enteredPassword.equals(password)) {
                // Incorrect password, login failed
                System.out.println("Incorrect password. Login failed.");
                return; // Exit the method without logging in
            }
        }

        // Display the logged-in message
        //System.out.println("Logged in to Account Number: " + accountNumber);
        loggedInAccount = accountNumber;

        // Now, you can proceed with other ATM operations (withdraw, deposit, checkBalance)
        // Implement those methods here.
    }

    // Method to check and display the balance
    public void checkBalance() {
        BankAccount account = accounts.get(loggedInAccount);

        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println("Account Balance: $" + account.getBalance());
        }
    }

    public void withdraw() {
        BankAccount account = accounts.get(loggedInAccount);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Enter the amount to withdraw:");
        double amount = scanner.nextDouble();

        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + account.getBalance());
        }
    }

    public void deposit() {
        BankAccount account = accounts.get(loggedInAccount);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Enter the amount to deposit:");
        double amount = scanner.nextDouble();

        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }

    public void logOut() {
        loggedInAccount = null;
        System.out.println("Logged out successfully.");
    }

    public void displayMenu() {
        clearConsole(); // Clear the console
        System.out.println("Welcome to the ATM!");

        if (loggedInAccount == null) {
            System.out.println("Please enter your account number:");
            String accountNumber = scanner.nextLine();
            login(accountNumber);
        }

        if (loggedInAccount != null) {
            System.out.println("Logged in to Account Number: " + loggedInAccount);
            System.out.println("Choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Log Out");
            System.out.println("5. Exit");

            System.out.print("Select an option (1/2/3/4/5): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    logOut();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close(); // Close the Scanner before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        while (true) {
            atm.displayMenu();
        }
    }
}
