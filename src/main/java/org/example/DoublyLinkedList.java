package org.example;

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
                } else  if(true) {
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

    public void swap(int Node1, int Node2) {
        if( (Node1 >= 0 && Node1 <= node_count)
            && (Node2 >= 0 && Node2 <= node_count) ) {
            DoublyLinkedList.Node element1 = getNode(Node1);
            DoublyLinkedList.Node element2 = getNode(Node2);

            String tempData = element2.data;
            element2.data = element1.data;
            element1.data = tempData;
        }
    }

    public Node getNode(int index) {
        if (index >= 0 && index <= node_count) {
            if(index == 0) {
                return front;
            } else if (index == node_count) {
                return back;
            } else {
                DoublyLinkedList.Node element;
                int i;

                if ( index < node_count/2 ) {
                    element = front;
                    i = 0;

                    while ( i < index) {
                        if(element != null) {
                            element = element.next;
                        }
                        i++;
                    }
                } else {
                    element = back;
                    i = node_count;

                    while ( i > index) {
                        if(element != null) {
                            element = element.prev;
                        }
                        i--;
                    }
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

    public void deleteAtIndex(int index) {
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

    public void deleteNode(String value) {
        if(front != null && back != null) {
            if(front == back) {
                delete_last();
            } else if (value == front.data) {
                delete_front();
            } else if (value == back.data) {
                delete_back();
            } else {
                DoublyLinkedList.Node element = front;

                while ( element.data != value) {
                    if(element != null) {
                        element = element.next;
                    }
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
        double radix = Math.round( Math.sqrt(Double.valueOf(node_count)) );

        String output = "\nFront ==>";

        // Traverse through the DoublyLinkedList
        while (currNode != null) {
            if(length % radix == 0) {
                output += "\n";
            }

            // Print the data at current node
            output += "\t" + currNode.data;

            // Go to next node
            currNode = currNode.next;
            length++;
        }
        output += "\n<== Back";
        return output;

        //System.out.print("Reporting Length: "+length+"\n");
        //System.out.print("Expected Length: "+node_count+"\n");
    }

    public String printB2F() {
        DoublyLinkedList.Node currNode = back;
        int length = 0;
        double radix = Math.round( Math.sqrt(Double.valueOf(node_count)) );

        String output = "\nBack ==>";

        // Traverse through the DoublyLinkedList
        while (currNode != null) {
            if(length % radix == 0) {
                output += "\n";
            }

            // Print the data at current node
            output += "\t" + currNode.data;

            // Go to previous node
            currNode = currNode.prev;
            length++;
        }
        output += "\n<== Front";
        return output;

        //System.out.print("Reporting Length: "+length+"\n");
        //System.out.print("Expected Length: "+node_count+"\n");
    }
}
