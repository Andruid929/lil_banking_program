package net.coder.the;

import net.coder.the.bank.Account;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Banking {

    public static void main(String[] args) {

        Account andrew = new Account("Andrew", "Jones", 2023, 40_000);

        Account linda = new Account("Linda", "Park", 2024, 340_000);

        Account james = new Account("James", "Olsen", 2345, 590_000);

        andrew.deposit(20_000);
        System.out.println(andrew.getBalance());
        System.out.println(andrew.getBalance());

        linda.deposit(60_000);
        System.out.println(linda.getBalance());

        List<String> names = Arrays.asList(andrew.getName(), linda.getName(), james.getName());

        for (String name : names) {
            System.out.println("\n" + readAccFile(name));
        }

    }

    private static String readAccFile(String name) {
        StringBuilder text = new StringBuilder();

        try (FileReader reader = new FileReader("C:\\Users\\Andrew Jones\\Desktop\\" + name + ".acc")) {
            Scanner scanner = new Scanner(reader);

            String firstName = scanner.next();
            String surname = scanner.next();
            int pin = scanner.nextInt();
            int balance = scanner.nextInt();

            text.append("First name\t: ").append(firstName).append("\n")
                .append("surname\t\t: ").append(surname).append("\n")
                .append("Pin\t\t\t: ").append(pin).append("\n")
                .append("Balance\t\t: ").append(balance).append("\n");
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return text.toString();
    }

}
