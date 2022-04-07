package com.company;

public interface IntList {
    int add(int item);
    int add(int index, int item);
    int set(int index, int item);
    int removeByIndex(int index);
    int removeByValue(int item);
    boolean contains(int item);
    int indexOf(int item);
    int lastIndexOf(int item);
    int get(int index);
    boolean equals(IntList otherList);
    int size();
    boolean isEmpty();
    void clear();
    int[] toArray();



}
