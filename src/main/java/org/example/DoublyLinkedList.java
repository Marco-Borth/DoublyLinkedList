package org.example;

import java.util.Random;

public class DoublyLinkedList {
    DoublyLinkedList.Node front; // front of list
    DoublyLinkedList.Node back; // front of list
    int node_count = 0;

    // Doubly Linked list Node.
    class Node {

        String data;
        DoublyLinkedList.Node next;
        DoublyLinkedList.Node prev;

        // Constructor
        Node(String d)
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
        String rand_int = Integer.toString( 1 + rand.nextInt(99) ) ;

        if (list.front != null) {
            list.push_front( rand_int);
        } else{
            int rand_index = rand.nextInt(node_count+1);
            list.insert(rand_int, rand_index);
        }

        return list;
    }

    // Method to insert a new node
    public void push_back(String data) {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

        // If the Linked List is empty,
        // then make the new node as front and back
        if (front == null) {
            front = new_node;
            back = new_node;
        } else {

            // Else insert and link the new node
            // to the list back.
            new_node.prev = back;
            back.next = new_node;
            back = new_node;
        }

        // increment node count.
        node_count++;
    }

    public void push_front(String data) {
        // Create a new node with given data
        DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

        // If the Linked List is empty,
        // then make the new node as front and back
        if (back == null) {
            front = new_node;
            back = new_node;
        } else {
            // Else insert and link the new node
            // to the list front.
            new_node.next = front;
            front.prev = new_node;
            front = new_node;
        }

        // increment node count.
        node_count++;
    }

    public void insert(String data, int index) {
        if (index >= 0 && index <= node_count+1) {

            // If the Linked List is empty,
            // then make the new node as front
            if(index == 0) {
                // Insert node at front if Index is 0.
                push_front(data);
            } else if (index == node_count+1) {
                // Insert node at back if Index equals the node_count.
                push_back(data);
            } else {
                // Create a new node with given data
                DoublyLinkedList.Node new_node = new DoublyLinkedList.Node(data);

                if (index == 1) {
                    DoublyLinkedList.Node second2front = front;
                    second2front = second2front.next;

                    new_node.next = second2front;
                    new_node.prev = front;
                    front.next = new_node;
                    second2front.prev = new_node;
                } else if (index == node_count) {
                    DoublyLinkedList.Node second2back = back;
                    second2back = second2back.prev;

                    new_node.prev = second2back;
                    new_node.next = back;
                    back.prev = new_node;
                    second2back.next = new_node;
                } else {
                    DoublyLinkedList.Node element = front;

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

                    new_node.next = element.next;
                    new_node.prev = element;
                    element.next = new_node;
                    nextElement.prev = new_node;
                }

                // increment node count.
                node_count++;
            }

        } else {
            // Throw Bounds Error!
        }
    }

    public Node getNode(int index) {
        if (index >= 0 && index <= node_count) {
            if(index == 0) {
                return front;
            } else if (index == node_count) {
                return back;
            } else {
                DoublyLinkedList.Node element = front;

                int i = 0;
                while ( i < index) {
                    if(element != null) {
                        element = element.next;
                    }
                    i++;
                }

                return element;
            }
        }
        return null;
    }

    public void clear() {
        delete_last();
    }

    private void delete_last() {
        front = null;
        back = null;
        node_count = 0;
    }

    public void delete_front() {
        if(front != null && back != null) {
            if(front == back) {
                delete_last();
            } else {
                DoublyLinkedList.Node element = front;

                element = element.next;
                element.prev = null;
                front.next = null;
                front = element;

                node_count--;
            }
        }
    }

    public void delete_back() {
        if(front != null && back != null) {
            if(front == back) {
                delete_last();
            } else {
                DoublyLinkedList.Node element = back;

                element = element.prev;
                element.next = null;
                back.next = null;
                back = element;

                node_count--;
            }
        }
    }

    public void delete(int index) {
        if(front != null && back != null) {
            if(front == back) {
                delete_last();
            } else if (index == 0) {
                delete_front();
            } else if (index == node_count) {
                delete_back();
            } else if (index == 1) {
                DoublyLinkedList.Node element = front;
                element = element.next;

                DoublyLinkedList.Node nextElement = element.next;
                DoublyLinkedList.Node prevElement = element.prev;

                prevElement.next = nextElement;
                nextElement.prev = prevElement;

                node_count--;
            } else if (index == node_count - 1) {
                DoublyLinkedList.Node element = back;
                element = element.prev;

                DoublyLinkedList.Node nextElement = element.next;
                DoublyLinkedList.Node prevElement = element.prev;

                prevElement.next = nextElement;
                nextElement.prev = prevElement;

                node_count--;
            } else {
                DoublyLinkedList.Node element = front;

                int i = 1;
                while ( i <= index) {
                    if(element != null) {
                        element = element.next;
                    }
                    i++;
                }

                //  insert and link the new node
                // to the list.
                DoublyLinkedList.Node nextElement = element.next;
                DoublyLinkedList.Node prevElement = element.prev;

                prevElement.next = nextElement;
                nextElement.prev = prevElement;

                node_count--;
            }
        }
    }

    // Method to print the DoublyLinkedList.
    public String printList() {
        return printF2B();
    }

    public String printF2B()
    {
        DoublyLinkedList.Node currNode = front;
        int length = 0;

        String output = "\tFront ==>";

        // Traverse through the DoublyLinkedList
        while (currNode != null) {
            if(length % 10 == 0) {
                output += "\n\t\t";
            }

            // Print the data at current node
            output += "\t" + currNode.data;

            // Go to next node
            currNode = currNode.next;
            length++;
        }
        output += "\n\t<== Back";
        return output;

        //System.out.print("Reporting Length: "+length+"\n");
        //System.out.print("Expected Length: "+node_count+"\n");
    }

    public String printB2F() {
        DoublyLinkedList.Node currNode = back;
        int length = 0;

        String output = "\tBack ==>";

        // Traverse through the DoublyLinkedList
        while (currNode != null) {
            if(length % 10 == 0) {
                output += "\n\t\t";
            }

            // Print the data at current node
            output += "\t" + currNode.data;

            // Go to previous node
            currNode = currNode.prev;
            length++;
        }
        output += "\n\t<== Front";
        return output;

        //System.out.print("Reporting Length: "+length+"\n");
        //System.out.print("Expected Length: "+node_count+"\n");
    }
}
