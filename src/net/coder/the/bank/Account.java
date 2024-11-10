package net.coder.the.bank;

import net.coder.the.util.PinIsTakenException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Account {

    private final String firstName;
    private final String surname;
    private final int pin;
    private long balance = 0;

    private static int numberOfAccounts = 0;

    private static final Set<Integer> PINS = new HashSet<>();

    List<String> TRANSACTION_HISTORY = new ArrayList<>();

    public Account(String firstName, String surname, int pin, int initialDeposit) {
        numberOfAccounts++;
        this.firstName = firstName;
        this.surname = surname;
        this.pin = pin;
        balance += initialDeposit;

        if (isPinTaken(pin)) {
            PINS.add(pin);
        } else {
            throw new PinIsTakenException("Try another pin");
        }

        TRANSACTION_HISTORY.add(firstName + " opened an account with " + initialDeposit);

        createAccountFile(this.firstName, this.surname, this.pin, this.balance);
    }

    private boolean isPinTaken(int pin) {
        if (!PINS.isEmpty()) {
            for (int pinned : PINS) {
                if (pin == pinned) {

                    return false;
                }
            }
        }

        return true;
    }

    public String getName() {
        return firstName + " " + surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getPin(String name) {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

    public static int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public void deposit(int amount) {
        balance += amount;

        String transaction = firstName + " deposited " + amount;

        TRANSACTION_HISTORY.add(transaction);

        createAccountFile(this.firstName, this.surname, this.pin, this.balance);

        System.out.println(transaction);
    }

    public void withdraw(int amount, int pin) {
        if (pin != this.pin) {
            System.out.println("Invalid pin!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds");
            return;
        }

        balance -= amount;

        String transaction = firstName + " withdrew " + amount;

        TRANSACTION_HISTORY.add(transaction);

        createAccountFile(this.firstName, this.surname, this.pin, this.balance);

        System.out.println(transaction);
    }

    private void createAccountFile(String firstName, String surname, int pin, long balance) {
        File file = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\" + firstName + " " + surname + ".acc");

        try (FileWriter fileWriter = new FileWriter(file)) {
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(firstName + " " + surname + "\r\n");
            writer.write(pin + "\r\n");
            writer.write(balance + "\r\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
