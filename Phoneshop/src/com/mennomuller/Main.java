package com.mennomuller;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Phoneshop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
//        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JTextField searchBox = new JTextField("Search");
        c.gridx = 0;
        c.gridy = 0;
        frame.add(searchBox, c);

        JLabel label = new JLabel("Brand:");
        c.gridx = 1;
        c.gridy = 0;
        frame.add(label, c);

        JTextField brandBox = new JTextField("lblBrand");
        brandBox.setEditable(false);
        c.gridx = 2;
        c.gridy = 0;
        frame.add(brandBox, c);

        label = new JLabel("Type:");
        c.gridx = 1;
        c.gridy = 1;
        frame.add(label, c);

        JTextField typeBox = new JTextField("lblType");
        typeBox.setEditable(false);
        c.gridx = 2;
        c.gridy = 1;
        frame.add(typeBox, c);

        label = new JLabel("Price:");
        c.gridx = 3;
        c.gridy = 0;
        frame.add(label, c);

        JTextField priceBox = new JTextField("lblPrice");
        priceBox.setEditable(false);
        c.gridx = 4;
        c.gridy = 0;
        frame.add(priceBox, c);

        label = new JLabel("Stock:");
        c.gridx = 3;
        c.gridy = 1;
        frame.add(label, c);

        JTextField stockBox = new JTextField("lblStock");
        stockBox.setEditable(false);
        c.gridx = 4;
        c.gridy = 1;
        frame.add(stockBox, c);

        label = new JLabel("Description:");
        c.gridx = 1;
        c.gridy = 2;
        frame.add(label, c);

        JTextField descriptionBox = new JTextField("lblDescription");
        descriptionBox.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 5;
        frame.add(descriptionBox, c);

        DefaultListModel listDisplayed = new DefaultListModel();
        listDisplayed.addElement("banana");
        listDisplayed.addElement("banana");
        listDisplayed.addElement("banana");
        listDisplayed.addElement("banana");
        JList resultList = new JList<String>(listDisplayed);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 3;
        frame.add(resultList, c);

        JButton exitButton = new JButton("Exit");
//        c.fill = GridBagConstraints.
        c.gridx = 5;
        c.gridy = 4;
        c.gridheight = 1;
        frame.add(exitButton, c);

        frame.setVisible(true);

        PhoneService phoneService = new PhoneService();
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            for (Phone phone : phoneService.getPhones()) {
                System.out.println(phone.id() + "   " + phone.brand() + " " + phone.model());
            }
            System.out.println((phoneService.getMaxId() + 1) + "   Afsluiten");
            System.out.println((phoneService.getMaxId() + 2) + "   Zoeken");
            choice = validatedInput(phoneService.getPhones().size() + 2, input);

            if (choice == phoneService.getMaxId() + 1) {
                System.out.println("Het programma wordt afgesloten.");
                break;
            } else if (choice == phoneService.getMaxId() + 2) {
                clearScreen();
                String searchTerm = input.next();
                List<Phone> results = phoneService.search(searchTerm);
                for (Phone phone : results) {
                    System.out.println(phone.id() + "   " + phone.brand() + " " + phone.model());
                }
                List<Integer> options = results.stream().mapToInt(Phone::id).boxed().collect(Collectors.toList());
                choice = validatedInput(options, input);
                Phone chosenPhone = phoneService.getPhone(choice);
                displayPhone(chosenPhone);
            } else {
                Phone chosenPhone = phoneService.getPhone(choice);
                displayPhone(chosenPhone);
            }
        }
    }

    static void displayPhone(Phone chosenPhone) {
        Scanner input = new Scanner(System.in);
        clearScreen();
        System.out.println(chosenPhone.brand() + " " + chosenPhone.model() + ", "
                + chosenPhone.price() + " euro (" + chosenPhone.taxFreePrice() + " euro)\n\n"
                + chosenPhone.description());
        input.next();
        clearScreen();
    }

    static int validatedInput(int upperBound, Scanner input) {
        int choice;
        while (true) {
            try {
                choice = input.nextInt();
                if (choice > upperBound || choice < 0) {
                    System.out.println("Please enter a valid number");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                input.next();
            }
        }
    }

    static int validatedInput(List<Integer> allowedValues, Scanner input) {
        int choice;

        while (true) {
            try {
                choice = input.nextInt();
                if (!allowedValues.contains(choice)) {
                    System.out.println("Please enter a valid number");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                input.next();
            }
        }
    }

    static void clearScreen() {
        System.out.println("\n".repeat(20));
    }


}
