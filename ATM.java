import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM extends JFrame {
    private BankAccount bankAccount;

    public ATM() {
        bankAccount = new BankAccount(1000.0); // Initialize with initial balance

        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);

        add(panel);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
                if (input != null && !input.isEmpty()) {
                    double amount = Double.parseDouble(input);
                    boolean success = bankAccount.withdraw(amount);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance!");
                    }
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to deposit:");
                if (input != null && !input.isEmpty()) {
                    double amount = Double.parseDouble(input);
                    bankAccount.deposit(amount);
                    JOptionPane.showMessageDialog(null, "Deposit successful!");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double balance = bankAccount.getBalance();
                JOptionPane.showMessageDialog(null, "Your balance is: " + balance);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM());
    }
}
