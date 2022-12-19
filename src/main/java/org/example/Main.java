package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("\nWelcome to the Linked-List Terminal Program.");
        System.out.println("Here is a Doubly Linked-List Generate for your to start with.\n");

        for(int i = 0; i < 10; i++) {
            String input =  Integer.toString( generateRandomNumber() );
            list.push_back(input);
        }

        // Print the DoublyLinkedList
        printList(list,"");

        String command;

        do  {
            printCommands();
            command = br.readLine();
            System.out.println("You have entered: " + command);

            if (command.contains("insert") || command.contains("push") ) {
                String input;

                if (command.contains("random")) {
                    input =  Integer.toString( generateRandomNumber() );
                } else {
                    System.out.println("\nEnter string input:");
                    input = br.readLine();
                }



                if (command.contains("front")) {
                    list.push_front(input);
                } else if (command.contains("back")) {
                    list.push_back(input);
                } else if (command.contains("index")) {
                    System.out.println("\nEnter a valid index value?");
                    String index_input = br.readLine();
                    int index;

                    if(index_input.contains("any") || index_input.contains("random")) {
                        Random rand = new Random();

                        index = rand.nextInt(list.node_count + 1);
                    } else {
                        index = Integer.parseInt(br.readLine());
                    }

                    list.insert(input, index);
                } else {
                    Random rand = new Random();

                    int rand_index = rand.nextInt(list.node_count + 1);
                    list.insert(input, rand_index);
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
                } else if (command.contains("all")) {
                    list.clear();
                }

                printList(list,"");
            } else if (command.equals("pop")) {
                list.delete_back();
                printList(list,"");
            } else if (command.equals("clear")) {
                list.clear();
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
                String listOrder;
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
                if(listOrder.equals("forward")) {

                    fileData += list.printF2B();

                } else if(listOrder.equals("backwards")) {

                    fileData += list.printB2F();

                }

                // Step 2: Store byte content from string
                byte[] strToBytes = fileData.getBytes();

                // Step 3: Write into the file
                outputStream.write(strToBytes);

                // Print the success message (Optional)
                System.out.print("\nFile is created successfully with the content.\n");
            } else if (command.contains("write") || command.contains("load") ) {
                System.out.println("\nEnter a file name to save list data:");
                String fileName = br.readLine();

                list = loadListFromFile(list, fileName);
                printList(list,"");
            } else if (command.contains("sort")) {
                String listOrder = "asc";
                if ( command.contains("asc") || command.contains("ascending") ) {
                    listOrder = "asc";
                } else if ( command.contains("desc") || command.contains("descending") ) {
                    listOrder = "desc";
                } else if ( command.contains("reverse") ) {
                    listOrder = "reverse";
                }

                // Use Selection Sort Algorithm to sort list in selected.
                if (listOrder.equals("desc")) {
                    list = selectionSort(list, "desc");
                } else if (listOrder.equals("reverse")) {
                    list = selectionSort(list, "reverse");
                } else {
                    list = selectionSort(list, "asc");
                }

                printList(list,"");
            } else if (command.contains("reset") || command.contains("generate") || command.contains("new list")) {
                System.out.println("\nEnter how many nodes the new list will have:");
                int newLength = Integer.parseInt(br.readLine());

                for(int i = 0; i < newLength; i++) {
                    String input =  Integer.toString( generateRandomNumber() );
                    list.push_back(input);
                }

                System.out.println("\nSUCCESS! A new Doubly Linked-List has been generated:\n");
                printList(list,"");
            }
        } while (!command.equals("q") || !command.equals("quit"));
    }

    public static void printCommands() {
        System.out.println("\nWhat would you like to do?");

        System.out.println("1. Insert a random Node to the list.");
        System.out.println("2. Delete a Node from the list.");
        System.out.println("3. Print all the Nodes in the list.");
        System.out.println("4. Save list data to a file.");
        System.out.println("5. Write list data from a file.");
        System.out.println("6. Sort list data.");
        System.out.println("7. Generate a new Random list.");
        System.out.println("Q. Press Q to quit.");
    }

    public static DoublyLinkedList loadListFromFile(DoublyLinkedList list, String fileName) throws IOException {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] fileNodes = data.split("\t");

        //System.out.print("\n" + numValues.length +"\n");
        list.clear();
        int index = 0;

        for (int i = 1; i < fileNodes.length; i++) {
            //System.out.print(numValues[i] + " ");
            //if(fileNodes[i] != null) {}
            if(!fileNodes[i].equals("\0") ) {
                if(fileNodes[i].contains("\n")) {
                    String[] splitStr = fileNodes[i] .split("\n");
                    for (int j = 0; j < splitStr.length; j++) {
                        if( !splitStr[j].contains("<== Back") ) {
                            list.push_back( splitStr[j] );
                        }
                    }
                } else {
                    list.push_back( fileNodes[i] );
                }

                index++;
            }

            /*
            if(index % 10 == 0) {
                System.out.print("\n");
            }
            */
        }

        System.out.println("\nFile data has been saved.\n");
        return list;
    }

    public static DoublyLinkedList selectionSort(DoublyLinkedList list, String listOrder) throws UnsupportedEncodingException {
        // Use Selection Sort Algorithm to sort list in selected.
        if (listOrder.equals("desc")) {

            for (int i  = list.node_count; i >= 0; i--) {
                if(i % 100 == 0) {
                    System.out.println("element at index [" + i + "] has being swapped.");
                }

                int min_Node = i;
                for (int j = i; j >= 0; j--) {
                    String jTempdata = list.getNode(j).data;
                    String minTempdata = list.getNode(min_Node).data;
                    boolean isLower = false;

                    try {
                        Integer.parseInt(list.getNode(j).data);
                    } catch (NumberFormatException ex) {
                        list.getNode(j).data = "" + Integer.MAX_VALUE;
                    }

                    try {
                        Integer.parseInt(list.getNode(min_Node).data);
                    } catch (NumberFormatException ex) {
                        list.getNode(min_Node).data = "" + Integer.MAX_VALUE;
                    }

                    if ( Integer.parseInt(list.getNode(j).data)
                            < Integer.parseInt(list.getNode(min_Node).data) ) {
                        isLower = true;
                    }

                    list.getNode(j).data = jTempdata;
                    list.getNode(min_Node).data = minTempdata;

                    if(isLower) {
                        min_Node = j;
                    }
                }

                // swap max element with nth element in list.
                list.swap(min_Node, i);


            }

        } else if (listOrder.equals("asc")) {

            for (int i  = list.node_count; i >= 0; i--) {
                if(i % 100 == 0) {
                    System.out.println("element at index [" + i + "] is being swapped.");
                }

                int max_Node = i;
                for (int j = i - 1; j >= 0; j--) {
                    String jTempdata = list.getNode(j).data;
                    String maxTempdata = list.getNode(max_Node).data;
                    boolean isHigher = false;

                    try {
                        Integer.parseInt(list.getNode(j).data);
                    } catch (NumberFormatException ex) {
                        list.getNode(j).data = "" + Integer.MAX_VALUE;
                    }

                    try {
                        Integer.parseInt(list.getNode(max_Node).data);
                    } catch (NumberFormatException ex) {
                        list.getNode(max_Node).data = "" + Integer.MAX_VALUE;
                    }

                    if ( Integer.parseInt(list.getNode(j).data)
                            > Integer.parseInt(list.getNode(max_Node).data) ) {
                        isHigher = true;
                    }

                    list.getNode(j).data = jTempdata;
                    list.getNode(max_Node).data = maxTempdata;

                    if(isHigher) {
                        max_Node = j;
                    }
                }

                // swap max element with nth element in list.
                list.swap(max_Node, i);


            }

        } else if (listOrder.equals("reverse")) {
            // Reverse the order of the nodes sorted or otherwise.
            DoublyLinkedList newlist = new DoublyLinkedList();

            for(int i = 0; i < list.node_count; i++) {
                newlist.push_front(list.getNode(i).data);
            }

            list = newlist;
        } else {
            list = selectionSort(list, listOrder);
        }

        return list;
    }

    public static void printList(DoublyLinkedList list, String order) {
        String listData = "DoublyLinkedList:\n";
        if(order.equals("f2b")) {
            listData += list.printF2B() +"\n";
            System.out.print(listData);
        } else if(order.equals("b2f")) {
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