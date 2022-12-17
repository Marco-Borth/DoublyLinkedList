package org.example;

import java.util.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoublyLinkedList {
    DoublyLinkedList.Node head; // head of list

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nWelcome to the Linked-List Terminal Program.");
        System.out.println("Here is a Doubly Linked-List Generate for your to start with.\n");

        /* Start with the empty list. */
        DoublyLinkedList list = new DoublyLinkedList();

        // Insert the values
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

            if (command.contentEquals("insert") ) {
                list.insertRandomNode(list);
                list.printList(list);
            }
        } while (!command.equals("q"));
    }

    public static void printCommands() {
        System.out.println("\nWhat would you like to do?");

        System.out.println("1. Insert a random Node to the string");
        System.out.println("Q. Press Q to quit");
    }

    // Linked list Node.
    // This inner class is made static
    // so that main() can access it
    class Node {

        int data;
        DoublyLinkedList.Node front;
        DoublyLinkedList.Node back;

        // Constructor
        Node(int d)
        {
            data = d;
            front = null;
            back = null;
        }
    }

    // Method to insert a new node
    public DoublyLinkedList insert(DoublyLinkedList list, int data)
    {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);


        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            DoublyLinkedList.Node last = list.head;
            while (last.front != null) {
                last = last.front;
            }

            // Insert the new_node at last node
            last.front = new_node;
        }

        // Return the list by head
        return list;
    }

    public DoublyLinkedList insertRandomNode(DoublyLinkedList list) {
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int rand_int = rand.nextInt(100);

        list.insert(list, rand_int);
        return list;
    }

    // Method to print the DoublyLinkedList.
    public void printList(DoublyLinkedList list)
    {
        DoublyLinkedList.Node currNode = list.head;
        int length = 0;

        System.out.print("DoublyLinkedList:\nFront ==> ");

        // Traverse through the DoublyLinkedList
        while (currNode != null) {
            if(length % 10 == 0) {
                System.out.print("\n\t");
            }

            // Print the data at current node
            System.out.print("\t" + currNode.data);

            // Go to next node
            currNode = currNode.front;
            length++;
        }
        System.out.print("\n<== Back\n");

        System.out.print("Length: "+length+"\n");
    }
}