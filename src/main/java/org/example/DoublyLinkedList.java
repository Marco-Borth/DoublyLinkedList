package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class DoublyLinkedList {
    DoublyLinkedList.Node front; // front of list
    DoublyLinkedList.Node back; // front of list
    int node_count = 0;

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
            int rand_index = rand.nextInt(node_count+1) - 1;
            list.backInsert(list, rand_int);
        }

        return list;
    }

    // Method to insert a new node
    public DoublyLinkedList backInsert(DoublyLinkedList list, int data) {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

        // If the Linked List is empty,
        // then make the new node as front and back
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
        node_count++;
        return list;
    }

    public DoublyLinkedList frontInsert(DoublyLinkedList list, int data) {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

        // If the Linked List is empty,
        // then make the new node as front and back
        if (list.back == null) {
            list.front = new_node;
            list.back = new_node;
        } else {
            // Else insert and link the new node
            // to the list front.
            new_node.next = list.front;
            list.front.prev = new_node;
            list.front = new_node;
        }

        // Return the list by front
        node_count++;
        return list;
    }

    public DoublyLinkedList indexInsert(DoublyLinkedList list, int data, int index) {
        if (index >= 0 && index <= node_count+1) {

            // If the Linked List is empty,
            // then make the new node as front
            if(index == 0) {
                // Insert node at front if Index is 0.
                list.frontInsert(list,data);
            } else if (index == node_count+1) {
                // Insert node at back if Index equals the node_count.
                list.backInsert(list,data);
            } else {
                // Create a new node with given data
                DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

                DoublyLinkedList.Node element = list.front;

                if (index == 1) {
                    DoublyLinkedList.Node second2front = list.front;
                    second2front = second2front.next;

                    new_node.next = second2front;
                    new_node.prev = list.front;
                    front.next = new_node;
                    second2front.prev = new_node;
                } else if (index == node_count) {
                    DoublyLinkedList.Node second2back = list.back;
                    second2back = second2back.prev;

                    new_node.prev = second2back;
                    new_node.next = list.back;
                    back.prev = new_node;
                    second2back.next = new_node;
                } else {
                    int i = 1;
                    while ( i < index) {
                        if(element != null) {
                            element = element.next;
                        }
                        i++;
                    }

                    //  insert and link the new node
                    // to the list.
                    DoublyLinkedList.Node nextElement = element.next;
                    DoublyLinkedList.Node prevElement = element.next;

                    new_node.next = element.next;
                    new_node.prev = element;
                    element.next = new_node;
                    nextElement.prev = new_node;
                }

                // Return the list by front
                node_count++;
            }

        } else {
            // Throw Bounds Error!
        }

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
        System.out.print("Expected Length: "+node_count+"\n");
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
        System.out.print("Expected Length: "+node_count+"\n");
    }
}
