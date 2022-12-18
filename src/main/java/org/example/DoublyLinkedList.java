package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class DoublyLinkedList {
    DoublyLinkedList.Node front; // front of list
    DoublyLinkedList.Node back; // front of list
    int listLength = 0;

    // Doubly Linked list Node.
    class Node {

        int data;
        DoublyLinkedList.Node next;
        DoublyLinkedList.Node prev;

        // Constructor
        Node(int d)
        {
            data = d;
            next = null;
            prev = null;
        }
    }

    public DoublyLinkedList insertRandomNode(DoublyLinkedList list) {
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 1 to 99
        int rand_int = 1 + rand.nextInt(99);

        if (list.front != null) {
            list.backInsert(list, rand_int);
        } else{
            int rand_index = rand.nextInt(listLength);
            list.backInsert(list, rand_int);
        }

        return list;
    }

    // Method to insert a new node
    public DoublyLinkedList backInsert(DoublyLinkedList list, int data)
    {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

        // If the Linked List is empty,
        // then make the new node as front
        if (list.front == null) {
            list.front = new_node;
            list.back = new_node;
        } else {
            // Else insert and link the new node
            // to the list back.
            new_node.prev = list.back;
            list.back.next = new_node;
            list.back = new_node;
        }

        // Return the list by front
        listLength++;
        return list;
    }

    public DoublyLinkedList frontInsert(DoublyLinkedList list, int data) {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

        // If the Linked List is empty,
        // then make the new node as back
        if (list.back == null) {
            list.back = new_node;
            list.front = new_node;
        } else {
            // Else insert and link the new node
            // to the list front.
            new_node.next = list.front;
            list.front.prev = new_node;
            list.front = new_node;
        }

        // Return the list by front
        listLength++;
        return list;
    }

    // Method to print the DoublyLinkedList.
    public void printList(DoublyLinkedList list) {
        list.printF2B(list);
    }

    public void printF2B(DoublyLinkedList list)
    {
        DoublyLinkedList.Node currNode = list.front;
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
            currNode = currNode.next;
            length++;
        }
        System.out.print("\n<== Back\n");

        System.out.print("Length: "+length+"\n");
    }

    public void printB2F(DoublyLinkedList list) {
        DoublyLinkedList.Node currNode = list.back;
        int length = 0;

        System.out.print("DoublyLinkedList:\nBack ==> ");

        // Traverse through the DoublyLinkedList
        while (currNode != null) {
            if(length % 10 == 0) {
                System.out.print("\n\t");
            }

            // Print the data at current node
            System.out.print("\t" + currNode.data);

            // Go to previous node
            currNode = currNode.prev;
            length++;
        }
        System.out.print("\n<== Front\n");

        System.out.print("Length: "+length+"\n");
    }
}
