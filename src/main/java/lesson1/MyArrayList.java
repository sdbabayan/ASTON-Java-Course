package lesson1;

import java.util.Arrays;

public class MyArrayList {
    private Object[] array;

    public MyArrayList() {
        array = new Object[]{};
    }

    public MyArrayList(Object[] items) {
        array = items.clone();
    }

    public Object[] add(int index, Object item) {
        int newLength = array.length + 1;
        Object[] newArray = new Object[newLength];
        int i = 0;
        while (i < index) {
            newArray[i] = array[i];
            i++;
        }
        newArray[i] = item;
        while (i + 1 < newLength) {
            newArray[i + 1] = array[i];
            i++;
        }
        array = newArray;
        return array;
    }

    public Object[] addAll(int index, Object[] items) {
        int newLength = array.length + items.length;
        Object[] newArray = new Object[newLength];
        int i = 0;
        while (i < index) {
            newArray[i] = array[i];
            i++;
        }
        int j = 0;
        while (j < items.length) {
            newArray[i] = items[j];
            i++;
            j++;
        }
        while (i < newLength) {
            newArray[i] = array[i - items.length];
            i++;
        }
        array = newArray;
        return array;
    }

    public Object get(int index) {
        return array[index];
    }

    public Object[] remove(int index) {
        int newLength = array.length - 1;
        Object[] newArray = new Object[newLength];
        int i = 0;
        while (i < index) {
            newArray[i] = array[i];
            i++;
        }
        while (i < newLength) {
            newArray[i] = array[i + 1];
            i++;
        }
        array = newArray;
        return array;
    }

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println("Все элементы:");
        Arrays.stream(myArrayList.array).forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Добавляем 11 по индексу 3:");
        Arrays.stream(myArrayList.add(3, 11)).forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Добавляем массив {22.33.44} по индексу 5:");
        Arrays.stream(myArrayList.addAll(5, new Integer[]{22, 33, 44})).forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Удаляем значение по индексу 7:");
        Arrays.stream(myArrayList.remove(7)).forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Получаем значение по индексу 8:");
        System.out.println(myArrayList.get(8));
    }
}
