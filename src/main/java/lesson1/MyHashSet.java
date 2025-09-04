package lesson1;

public class MyHashSet {
    private Object[] storage;
    private int count;

    public MyHashSet() {
        storage = new Object[10];
        count = 0;
    }

    public boolean add(Object obj) {
        if (contains(obj)) {
            return false;
        }
        count += 1;
        if (count >= storage.length) {
            increaseStorage();
        }
        putData(obj);
        return true;
    }

    public boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        count -= 1;
        deleteData(obj);
        return true;
    }

    public boolean contains(Object obj) {
        int objHashCode = obj.hashCode();
        int bucket = Math.abs(objHashCode % storage.length);
        if (storage[bucket] == null) {
            return false;
        } else {
            Object[] innerArray = (Object[]) storage[bucket];
            for (int i = 0; i < innerArray.length; i++) {
                if (innerArray[i].equals(obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    private void increaseStorage() {
        Object[] oldStorage = storage;
        storage = new Object[storage.length * 2];
        for (int i = 0; i < oldStorage.length; i++) {
            if (oldStorage[i] == null) {
                continue;
            }
            Object[] innerArray = (Object[]) oldStorage[i];
            for (int j = 0; j < innerArray.length; j++) {
                putData(innerArray[j]);
            }
        }
    }

    private void putData(Object obj) {
        int objHashCode = obj.hashCode();
        int bucket = Math.abs(objHashCode % storage.length);

        if (storage[bucket] == null) {
            storage[bucket] = new Object[]{obj};
        } else {
            Object[] oldInnerArray = (Object[]) storage[bucket];
            Object[] newInnerArray = new Object[oldInnerArray.length + 1];
            int i = 0;
            while (i < oldInnerArray.length) {
                newInnerArray[i] = oldInnerArray[i];
                i++;
            }
            newInnerArray[i] = obj;
            storage[bucket] = newInnerArray;
        }
    }

    private void deleteData(Object obj) {
        int objHashCode = obj.hashCode();
        int bucket = Math.abs(objHashCode % storage.length);

        Object[] oldInnerArray = (Object[]) storage[bucket];
        Object[] newInnerArray;
        if (oldInnerArray.length <= 1) {
            storage[bucket] = null;
            return;
        } else {
            newInnerArray = new Object[oldInnerArray.length - 1];

            for (int i = 0; i < oldInnerArray.length; i++) {
                for (int j = 0; j < newInnerArray.length; j++) {
                    if (oldInnerArray[i].equals(obj)) {
                        i++;
                        continue;
                    } else {
                        newInnerArray[j] = oldInnerArray[i];
                    }
                }
            }
        }
        storage[bucket] = newInnerArray;
    }


    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        for (int i = 0; i < 1000000; i++) {
            myHashSet.add(i);
        }
        System.out.println(myHashSet.contains(1));
        System.out.println(myHashSet.contains(500000));
        System.out.println(myHashSet.contains(999999));
        System.out.println(myHashSet.contains("qwerty"));
        System.out.println(myHashSet.add("qwerty"));
        System.out.println(myHashSet.contains("qwerty"));
        System.out.println(myHashSet.remove("qwerty"));
        System.out.println(myHashSet.contains("qwerty"));
    }
}
