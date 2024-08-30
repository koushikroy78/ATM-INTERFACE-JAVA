import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {
    private static double balance = 100000.00;
    private static String pin = "1234";
    private static ArrayList<String> transactionHistory = new ArrayList<>();
    private static String language = "ENGLISH";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM Machine Simulation!");
        chooseLanguage(scanner);
        
        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Account Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. PIN Change");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    checkBalance(scanner);
                    break;
                case 2:
                    withdrawCash(scanner);
                    break;
                case 3:
                    depositCash(scanner);
                    break;
                case 4:
                    changePIN(scanner);
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM Machine Simulation. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void chooseLanguage(Scanner scanner) {
        System.out.println("Please choose your language:");
        System.out.println("1. ENGLISH");
        System.out.println("2. HINDI");
        System.out.println("3. BENGALI");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                language = "ENGLISH";
                break;
            case 2:
                language = "HINDI";
                break;
            case 3:
                language = "BENGALI";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to ENGLISH.");
                language = "ENGLISH";
        }
        System.out.println("Language set to: " + language);
    }

    private static void checkBalance(Scanner scanner) {
        if (verifyPIN(scanner)) {
            System.out.println("Your current balance is: Rs." + balance);
            transactionHistory.add("Checked balance: Rs." + balance);
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    private static void withdrawCash(Scanner scanner) {
        if (verifyPIN(scanner)) {
            System.out.println("Enter amount to withdraw:");
            double amount = scanner.nextDouble();
            if (amount > balance) {
                System.out.println("Insufficient balance.");
            } else {
                balance -= amount;
                System.out.println("You have withdrawn: Rs." + amount);
                transactionHistory.add("Withdrew: Rs." + amount);
            }
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    private static void depositCash(Scanner scanner) {
        if (verifyPIN(scanner)) {
            System.out.println("Enter amount to deposit:");
            double amount = scanner.nextDouble();
            balance += amount;
            System.out.println("You have deposited: Rs." + amount);
            transactionHistory.add("Deposited: Rs." + amount);
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    private static void changePIN(Scanner scanner) {
        System.out.println("Enter current PIN:");
        String currentPIN = scanner.next();
        if (currentPIN.equals(pin)) {
            System.out.println("Enter New PIN:");
            pin = scanner.next();
            System.out.println("PIN successfully changed.");
            transactionHistory.add("Changed PIN");
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    private static void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private static boolean verifyPIN(Scanner scanner) {
        System.out.println("Enter your 4-digit PIN:");
        String enteredPIN = scanner.next();
        return enteredPIN.equals(pin);
    }
}
