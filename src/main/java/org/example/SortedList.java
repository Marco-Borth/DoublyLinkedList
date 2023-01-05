package org.example;

import java.io.UnsupportedEncodingException;

public class SortedList extends DoublyLinkedList {
    String listOrder = "asc";

    DoublyLinkedList list;

    SortedList(DoublyLinkedList importList) { list = importList;  }

    SortedList(DoublyLinkedList importList, String order) {
        list = importList;
        listOrder = order;
    }

    public DoublyLinkedList flipListOrder() {

        for(int i = 0; i < list.node_count/2; i++) {
            list.swap(i, list.node_count - i);
        }

        return list;
    }

    private String NaNCheck(String value) {
        try {
            Integer.parseInt(value);
            return value;
        } catch (NumberFormatException ex) {
            return ("" + Integer.MAX_VALUE);
        }
    }

    private boolean isNumeric(String inputString) {
        if (inputString == null || inputString.length() == 0) {
            return false;
        } else {
            for (char c : inputString.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isListSorted(String listOrder) {

        if (list.front == null && list.back == null) {
            return false;
        } else if (list.node_count == 1) {
            return true;
        } else if ( listOrder.equals("asc") || listOrder.equals("desc") ) {
            for (int j = 1; j < list.node_count; j++) {
                String iTempdata = list.getNode(j - 1).data;
                String jTempdata = list.getNode(j).data;

                list.getNode(j - 1).data = NaNCheck(list.getNode(j - 1).data);
                list.getNode(j).data = NaNCheck(list.getNode(j).data);

                if( listOrder.equals("asc") && Integer.parseInt(list.getNode(j).data) < Integer.parseInt(list.getNode(j - 1).data) ) {
                    list.getNode(j - 1).data = iTempdata;
                    list.getNode(j).data = jTempdata;

                    return false;
                } else if ( listOrder.equals("desc") && Integer.parseInt(list.getNode(j).data) > Integer.parseInt(list.getNode(j - 1).data) ) {
                    list.getNode(j - 1).data = iTempdata;
                    list.getNode(j).data = jTempdata;

                    return false;
                }

                list.getNode(j - 1).data = iTempdata;
                list.getNode(j).data = jTempdata;
            }

            return true;
        }

        return false;
    }

    public DoublyLinkedList insertionSort() {

        if (listOrder.equals("desc")) {
            listOrder = "asc";
            list = insertionSort();
            list = flipListOrder();
            listOrder = "desc";
        } else if (listOrder.equals("asc")) {
            int nanCount = 0;

            for (int i = 0; i <= list.node_count - nanCount; i++) {
                if( !isNumeric(list.getNode(i).data) ) {
                    list.swap(i, list.node_count - nanCount);
                    nanCount++;
                }
            }

            for (int i = 1; i <= list.node_count - nanCount; i++) {
                if(i % 10 == 0) {
                    System.out.println("element at index [" + i + "] is being swapped.");
                }

                String key =  list.getNode(i).data;
                int j = i - 1;

                String keyTempdata = key;

                key = NaNCheck(list.getNode(i).data);

                while ( j >= 0 && Integer.parseInt(list.getNode(j).data) > Integer.parseInt(key) ) {
                    list.getNode(j + 1).data = list.getNode(j).data;
                    j = j - 1;
                }
                key = keyTempdata;
                list.getNode(j + 1).data = key;
            }

        } else {
            list = insertionSort();
        }
        return list;
    }

    public DoublyLinkedList selectionSort() throws UnsupportedEncodingException {
        // Use Selection Sort Algorithm to sort list in selected.
        if (listOrder.equals("desc")) {
            for (int i  = list.node_count; i >= 0; i--) {
                if(i % 10 == 0) {
                    System.out.println("element at index [" + i + "] is being swapped.");
                }

                int min_Node = i;
                for (int j = i; j >= 0; j--) {
                    String jTempdata = list.getNode(j).data;
                    String minTempdata = list.getNode(min_Node).data;
                    boolean isLower = false;

                    list.getNode(j).data = NaNCheck(list.getNode(j).data);
                    list.getNode(min_Node).data = NaNCheck(list.getNode(min_Node).data);

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
                if(i % 10 == 0) {
                    System.out.println("element at index [" + i + "] is being swapped.");
                }

                int max_Node = i;
                for (int j = i - 1; j >= 0; j--) {
                    String jTempdata = list.getNode(j).data;
                    String maxTempdata = list.getNode(max_Node).data;
                    boolean isHigher = false;

                    list.getNode(j).data = NaNCheck(list.getNode(j).data);
                    list.getNode(max_Node).data = NaNCheck(list.getNode(max_Node).data);

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
        } else {
            listOrder = "asc";
            list = selectionSort();
        }
        return list;
    }

    public DoublyLinkedList shakerSort() throws UnsupportedEncodingException {
        // Use Shaker Sort Algorithm to sort list in selected.
        if ( listOrder.equals("desc") || listOrder.equals("asc") ) {

            int i = 0;
            int maxlength = list.node_count;
            DoublyLinkedList newList = new DoublyLinkedList();

            while (list.front != null && list.back != null) {
                if(i % 10 == 0) {
                    System.out.println("elements at indexes [" + i + "] and [" + (maxlength - i) + "] have been sorted.");
                }

                int max_Node = 0;
                int min_Node = 0;

                if (list.node_count != 1) {}
                for (int j = 0; j <= list.node_count; j++) {
                    String jTempdata = list.getNode(j).data;
                    String maxTempdata = list.getNode(max_Node).data;
                    String minTempdata = list.getNode(min_Node).data;
                    boolean isHigher = false;
                    boolean isLower = false;

                    list.getNode(j).data = NaNCheck(list.getNode(j).data);
                    list.getNode(max_Node).data = NaNCheck(list.getNode(max_Node).data);
                    list.getNode(min_Node).data = NaNCheck(list.getNode(min_Node).data);

                    if ( Integer.parseInt(list.getNode(j).data)
                            > Integer.parseInt(list.getNode(max_Node).data) ) {
                        isHigher = true;
                    }

                    if ( Integer.parseInt(list.getNode(j).data)
                            < Integer.parseInt(list.getNode(min_Node).data) ) {
                        isLower = true;
                    }

                    list.getNode(j).data = jTempdata;
                    list.getNode(max_Node).data = maxTempdata;
                    list.getNode(min_Node).data = minTempdata;

                    if(isHigher) {
                        max_Node = j;
                    }

                    if(isLower) {
                        min_Node = j;
                    }
                }

                String maxTempdata = list.getNode(max_Node).data;
                String minTempdata = list.getNode(min_Node).data;
                //System.out.println("removing minNode: " + minTempdata);
                //System.out.println("removing maxNode: " + maxTempdata);

                if( listOrder.equals("asc") ) {
                    if(newList.front == null && newList.back == null) {
                        newList.push_front(minTempdata);

                        if( list.node_count != 1 ) {
                            newList.push_back(maxTempdata);
                        }
                    } else {
                        newList.insert(minTempdata, i);

                        if( list.node_count != 1 ) {
                            newList.insert(maxTempdata, newList.node_count - i);
                        }
                    }
                } else if ( listOrder.equals("desc") ) {
                    if(newList.front == null && newList.back == null) {
                        newList.push_front(maxTempdata);

                        if( list.node_count != 1 ) {
                            newList.push_back(minTempdata);
                        }
                    } else {
                        newList.insert(maxTempdata, i);

                        if( list.node_count != 1 ) {
                            newList.insert(minTempdata, newList.node_count - i);
                        }
                    }
                }
                //printList(newList, "");

                i++;

                list.deleteNode(minTempdata);
                list.deleteNode(maxTempdata);
            }

            list = newList;

        } else {
            listOrder = "asc";
            list = shakerSort();
        }

        return list;
    }

    public int partition(DoublyLinkedList list, int front, int back, boolean useMedian) throws UnsupportedEncodingException {
        String pivot = NaNCheck(list.getNode(back).data);

        if(useMedian) {
            int middle = (front + back)/2;

            pivot = NaNCheck(list.getNode(middle).data);
        }

        int i = (front - 1);

        /*
        if(i % 10 == 0) {
            System.out.println("element at index [" + i + "] is being swapped.");
        }
        */

        for (int j = front; j < back; j++) {
            String jTempdata = list.getNode(j).data;
            boolean isLower = false;

            list.getNode(j).data = NaNCheck(list.getNode(j).data);

            if ( Integer.parseInt(list.getNode(j).data)  <  Integer.parseInt(pivot) ) {
                isLower = true;
            }

            list.getNode(j).data = jTempdata;

            if(isLower) {
                i++;
                list.swap(i,j);
            }
        }

        list.swap(i + 1, back);
        return (i + 1);
    }
    public DoublyLinkedList quickSort(DoublyLinkedList list, int front, int back, boolean useMedian) throws UnsupportedEncodingException {
        if (listOrder.equals("desc")) {
            listOrder = "asc";
            list = quickSort(list, front, back, useMedian);
            list = flipListOrder();
            listOrder = "desc";
        } else if (listOrder.equals("asc")) {
            if (front < back) {

                if(front % 10 == 0 || back % 10 == 0 ) {
                    System.out.println("elements from indexes [" + front + "] to [" + back + "] are being partitioned.");
                }

                int pi = partition(list, front, back, useMedian);

                list = quickSort(list, front, pi - 1, useMedian);
                list = quickSort(list, pi + 1, back, useMedian);
            }
        } else {
            list = quickSort(list, front, back, useMedian);
        }
        return list;
    }

    public DoublyLinkedList merge(DoublyLinkedList list, int left, int middle, int right) throws UnsupportedEncodingException {

        int leftLength = middle - left + 1;
        int rightLength = right - middle;

        DoublyLinkedList leftSideList = new DoublyLinkedList();
        DoublyLinkedList rightSideList = new DoublyLinkedList();

        for (int i = 0; i < leftLength; ++i) {
            leftSideList.push_back(list.getNode(left+ i).data);
        }

        for (int j = 0; j < rightLength; ++j) {
            rightSideList.push_back(list.getNode(middle + 1 + j).data);
        }

        int i = 0;
        int j = 0;

        int k = left;
        while(i < leftLength && j < rightLength) {

            String iTempdata = leftSideList.getNode(i).data;
            String jTempdata = rightSideList.getNode(j).data;
            boolean isLower = false;

            leftSideList.getNode(i).data = NaNCheck(leftSideList.getNode(i).data);
            rightSideList.getNode(j).data = NaNCheck(rightSideList.getNode(j).data);

            if( Integer.parseInt(leftSideList.getNode(i).data) <= Integer.parseInt(rightSideList.getNode(j).data) ) {
                isLower = true;
            }

            leftSideList.getNode(i).data = iTempdata;
            rightSideList.getNode(j).data = jTempdata;

            if(isLower) {
                list.getNode(k).data = leftSideList.getNode(i).data;
                i++;
            } else {
                list.getNode(k).data = rightSideList.getNode(j).data;
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            list.getNode(k).data = leftSideList.getNode(i).data;
            i++;
            k++;
        }

        while (j < rightLength) {
            list.getNode(k).data = rightSideList.getNode(j).data;
            j++;
            k++;
        }

        return list;
    }

    public DoublyLinkedList mergeSort(DoublyLinkedList list, int left, int right) throws UnsupportedEncodingException {

        if (listOrder.equals("desc")) {

            listOrder = "asc";
            list = mergeSort(list, left, right);
            list = flipListOrder();
            listOrder = "desc";

        } else if (listOrder.equals("asc")) {

            if(left < right) {
                int middle = left + ( right - 1 ) / 2;

                mergeSort(list, left, middle);
                mergeSort(list, middle + 1, right);

                merge(list, left, middle, right);
            }

        } else {
            list = mergeSort(list, left, right);
        }
        return list;
    }
}
