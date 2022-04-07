package com.company;

import com.company.exceptions.CheckIndexException;
import com.company.exceptions.ElementIsNullException;
import com.company.exceptions.ElementNotFoundException;

public class IntListImpl implements IntList {

    private int[] myIntList = new int[10];
    private int size = 0;

    @Override
    public int add(int item) {
        myIntList[size] = item;
        size++;
        grow();
        return item;
    }

    @Override
    public int add(int index, int item) {
        checkIndex(index);
        grow();
        for (int i = size - 1; i >= index; i--) {
            myIntList[i + 1] = myIntList[i];
        }
        myIntList[index] = item;
        size++;
        return item;
    }

    @Override
    public int set(int index, int item) {
        checkIndex(index);
        myIntList[index] = item;
        return item;
    }

    @Override
    public int removeByIndex(int index) {
        checkIndex(index);
        int result = myIntList[index];
        for (int i = index; i < size; i++) {
            myIntList[i] = myIntList[i + 1];
        }
        size--;
        return result;
    }

    @Override
    public int removeByValue(int item) {
        int a = -1;
        for (int i = 0; i < size; i++) {
            if (myIntList[i] == item) {
                a = i;
                break;
            }
        }
        if (a != -1) {
            removeByIndex(a);
        } else {
            throw new ElementNotFoundException("Такое число отсутствует в списке");
        }
        return item;
    }

    @Override
    public boolean contains(int item) {
        quickSort(myIntList, 0, size - 1);
        return binarySearch(item);
    }

    @Override
    public int indexOf(int item) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (myIntList[i] == item) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(int item) {
        int result = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (myIntList[i] == item) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public int get(int index) {
        checkIndex(index);
        return myIntList[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList == null) {
            throw new ElementIsNullException("В качестве параметра передан пустой элемент");
        }
        boolean result = true;
        if (this.size != otherList.size()) {
            result = false;
        } else {
            for (int i = 0; i < size; i++) {
                if (this.get(i) != otherList.get(i)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        if (size != 0) {
            result = false;
        }
        return result;
    }

    @Override
    public void clear() {
        myIntList = new int[10];
        size = 0;
    }

    @Override
    public int[] toArray() {
        int[] newList = new int[this.size];
        for (int i = 0; i < newList.length; i++) {
            newList[i] = this.get(i);
        }
        return newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > myIntList.length - 1) {
            throw new CheckIndexException("Индекс выходит за пределы листа");
        }
        if (index > size - 1) {
            throw new CheckIndexException("Элемент по указанному индексу отсутствует");
        }
    }

    private void grow() {
        if (size == myIntList.length) {
            int[] newIntList = new int[(int) (myIntList.length * 1.5)];
            for (int i = 0; i < myIntList.length; i++) {
                newIntList[i] = myIntList[i];
            }
            myIntList = newIntList;
        }
    }

    private void swapElements(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private boolean binarySearch(int item) {
        int min = 0;
        int max = this.size - 1;
        while (min <= max) {
            int middle = (min + max) / 2;
            if (item == this.get(middle)) {
                return true;
            }
            if (item < this.get(middle)) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return false;
    }
}
