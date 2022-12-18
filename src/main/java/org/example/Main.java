package org.example;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("\nWelcome to the Linked-List Terminal Program.");
        System.out.println("Here is a Doubly Linked-List Generate for your to start with.\n");

        for(int i = 0; i < 10; i++) {
            list.insertRandomNode(list);
        }

        // Print the DoublyLinkedList
        printList(list,"");

        String command = null;

        do  {
            printCommands();
            command = br.readLine();
            System.out.println("You have entered: " + command);

            if (command.contains("insert") || command.contains("push") ) {
                if (command.contains("front")) {
                    int number = generateRandomNumber();
                    list.push_front(number);
                } else if (command.contains("back")) {
                    int number = generateRandomNumber();
                    list.push_back(number);
                } else if (command.contains("index")) {
                    System.out.println("\nEnter a valid index value?");
                    int index = Integer.parseInt(br.readLine());
                    int number = generateRandomNumber();
                    list.insert(number, index);
                } else {
                    list.insertRandomNode(list);
                }

                printList(list,"");
            } else if (command.contains("delete") || command.contains("remove") ) {
                if (command.contains("front")) {
                    list.delete_front();
                } else if (command.contains("back")) {
                    list.delete_back();
                } else if (command.contains("index")) {
                    System.out.println("\nEnter a valid index value?");
                    int index = Integer.parseInt(br.readLine());
                    list.delete(index);
                }

                printList(list,"");
            } else if (command.equals("pop")) {
                list.delete_back();
                printList(list,"");
            } else if (command.contains("print") ) {
                if (command.contains("front to back")
                        || command.contains("f2b")) {
                    printList(list,"f2b");
                } else if (command.contains("back to front")
                        || command.contains("b2f")) {
                    printList(list,"b2f");
                } else {
                    printList(list,"");
                }
            } else if (command.contains("save")) {
                String listOrder = "forward";
                if ( command.contains("forward")
                        || command.contains("forwards")
                        || command.contains("front to back")
                        || command.contains("f2b") ) {
                    listOrder = "forward";
                } else if( command.contains("backwards")
                        || command.contains("backward")
                        || command.contains("back to front")
                        || command.contains("b2f") ) {
                    listOrder = "backwards";
                } else {
                    listOrder = "forward";
                }

                System.out.println("\nEnter a file name to save list data:");

                // Step 1:  Create an object of FileOutputStream
                String fileName = br.readLine();
                FileOutputStream outputStream = new FileOutputStream(fileName);

                String fileData = "Filename: " + fileName + "\n\nDoublyLinkedList data:\n";
                if(listOrder == "forward") {

                    fileData += list.printF2B();

                } else if(listOrder == "backwards") {

                    fileData += list.printB2F();
                    
                }

                // Step 2: Store byte content from string
                byte[] strToBytes = fileData.getBytes();

                // Step 3: Write into the file
                outputStream.write(strToBytes);

                // Print the success message (Optional)
                System.out.print("\nFile is created successfully with the content.\n");
            }
        } while (!command.equals("q") || !command.equals("quit"));
    }

    public static void printCommands() {
        System.out.println("\nWhat would you like to do?");

        System.out.println("1. Insert a random Node to the list.");
        System.out.println("2. Delete a Node from the list.");
        System.out.println("3. Print all the Nodes in the list.");
        System.out.println("4. Save list data to a file.");
        System.out.println("Q. Press Q to quit.");
    }

    public static void printList(DoublyLinkedList list, String order) {
        String listData = "DoublyLinkedList:\n";
        if(order == "f2b") {
            listData += list.printF2B() +"\n";
            System.out.print(listData);
        } else if(order == "b2f") {
            listData += list.printB2F() +"\n";
            System.out.print(listData);
        } else {
            listData += list.printList() +"\n";
            System.out.print(listData);
        }
    }

    public static int generateRandomNumber() {
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 1 to 99
        int rand_int = 1 + rand.nextInt(98);

        return rand_int;
    }
}