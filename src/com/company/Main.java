package com.company;


public class Main {

    public static void main(String[] args) {
        IntList numbers = new IntListImpl();
        numbers.add(16);
        numbers.add(12);
        numbers.add(20);
        numbers.add(35);
        numbers.add(43);
        numbers.add(57);
        numbers.add(3);
        numbers.add(1);
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i) + " ");
        }
        System.out.println();
        System.out.println(numbers.contains(35));


    }
}
