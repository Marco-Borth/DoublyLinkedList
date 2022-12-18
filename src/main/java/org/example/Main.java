package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nWelcome to the Linked-List Terminal Program.");
        System.out.println("Here is a Doubly Linked-List Generate for your to start with.\n");

        DoublyLinkedList list = new DoublyLinkedList();

        for(int i = 0; i < 10; i++) {
            list.insertRandomNode(list);
        }

        // Print the DoublyLinkedList
        list.printList(list);

        String command = null;

        do  {
            printCommands();
            command = br.readLine();
            System.out.println("You have entered: " + command);

            if (command.contains("insert") ) {
                if (command.contains("front")) {
                    int number = generateRandomNumber();
                    list.frontInsert(list, number);
                } else if (command.contains("back")) {
                    int number = generateRandomNumber();
                    list.backInsert(list, number);
                } else {
                    list.insertRandomNode(list);
                }

                list.printList(list);
            }

            if (command.contains("print") ) {
                if (command.contains("front to back") ||
                        command.contains("f2b")) {
                    list.printF2B(list);
                } else if (command.contains("back to front") ||
                        command.contains("b2f")) {
                    list.printB2F(list);
                } else {
                    list.printList(list);
                }
            }
        } while (!command.equals("q"));
    }

    public static void printCommands() {
        System.out.println("\nWhat would you like to do?");

        System.out.println("1. Insert a random Node to the string");
        System.out.println("2. Print all the Nodes in the list");
        System.out.println("Q. Press Q to quit");
    }

    public static int generateRandomNumber() {
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 1 to 99
        int rand_int = 1 + rand.nextInt(99);

        return rand_int;
    }
}