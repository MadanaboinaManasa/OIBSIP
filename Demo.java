import java.util.*;

class User {
    private String userId;
    private int pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String userId, int pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }
	public int getPin(){
		return pin;
	}

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Deposit(amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Withdrawal(amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(User targetUser, double amount) {
        if (balance >= amount) {
            balance -= amount;
            targetUser.deposit(amount);
            transactionHistory.add(new Transfer(amount, targetUser.getUserId()));
        } else {
            System.out.println("Insufficient funds");
        }
    }
}

class Transaction {
    protected double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }
}

class Deposit extends Transaction {
    public Deposit(double amount) {
        super(amount);
    }
}

class Withdrawal extends Transaction {
    public Withdrawal(double amount) {
        super(amount);
    }
}

class Transfer extends Transaction {
    private String targetUserId;

    public Transfer(double amount, String targetUserId) {
        super(amount);
        this.targetUserId = targetUserId;
    }

    public String getTargetUserId() {
        return targetUserId;
    }
}

class ATMSystem {
    private User currentUser;

    public ATMSystem(User user) {
        this.currentUser = user;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User Name: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        int enteredPin = scanner.nextInt();

        if (enteredUserId.equals(currentUser.getUserId()) && enteredPin == currentUser.getPin()) {
            int choice;
            do {
                System.out.println("\nATM Functionalities:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Transaction History");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        currentUser.deposit(depositAmount);
                        System.out.println("Money deposited successfully");
                        break;

                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        currentUser.withdraw(withdrawAmount);
                        break;

                    case 3:
                        System.out.print("Enter target user ID for transfer: ");
                        String targetUserId = scanner.next();
                        User targetUser = new User(targetUserId, 0, 0); 
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        currentUser.transfer(targetUser, transferAmount);
                        break;

                    case 4:
                        displayTransactionHistory();
                        break;

                    case 5:
                        System.out.println("Quitting ATM. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }

        scanner.close();
    }

    private void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : currentUser.getTransactionHistory()) {
            if (transaction instanceof Deposit) {
                System.out.println("Deposit: $" + transaction.amount);
            } else if (transaction instanceof Withdrawal) {
                System.out.println("Withdrawal: $" + transaction.amount);
            } else if (transaction instanceof Transfer) {
                Transfer transfer = (Transfer) transaction;
                System.out.println("Transfer: $" + transfer.amount + " to " + transfer.getTargetUserId());
            }
        }
    }
}

public class Demo{
    public static void main(String args[]) {
        User user = new User("manasa", 1234, 1000.0);
        ATMSystem atmSystem = new ATMSystem(user);
        atmSystem.start();
    }
}