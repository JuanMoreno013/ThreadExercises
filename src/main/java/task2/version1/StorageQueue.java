package task2.version1;

import lombok.Getter;
import task2.version1.storageProducts.PhoneStorage;

import java.util.Arrays;

@Getter
public class StorageQueue {

    private volatile int amount;

    private PhoneStorage[] phoneStorages;

    private final int LIMIT_CAPACITY = 5;

    private int countIndex = 0;

    public StorageQueue() {
        phoneStorages = new PhoneStorage[10];
    }

    public synchronized void increaseAmount(){
        amount++;
    }

    public synchronized void decreaseAmount(){
        amount--;
    }

    public synchronized void add(PhoneStorage phoneStorage) throws InterruptedException {

        while (phoneStorages.length == 1){
            wait();
        }

        if (phoneStorages.length < LIMIT_CAPACITY + 1) {
            phoneStorages[countIndex] = phoneStorage;
            phoneStorages = Arrays.copyOf(phoneStorages, phoneStorages.length + 1);
        }

        countIndex++;
    }

    public int sizeGoods() {
        return phoneStorages.length - 1;
    }


    public synchronized void remove() {
        if (countIndex > 0) {
            phoneStorages = Arrays.copyOfRange(phoneStorages, 1, phoneStorages.length);
            countIndex--;
        }
    }

    public String toString() {
        return Arrays.toString(phoneStorages);

    }


//    public static void main(String[] args) {
//        StorageQueue storageItems = new StorageQueue();
//
//
//        storageItems.add(1);
//        storageItems.add(2);
//        storageItems.add(3);
//        storageItems.add(4);
//        storageItems.add(5);
////        storageItems.add(6);
////        storageItems.add();
////        storageItems.add();
//
//        System.out.println(storageItems.sizeGoods());
//
//        System.out.println(storageItems);
//
//        storageItems.remove();
//        storageItems.remove();
//        storageItems.remove();
//        storageItems.remove();
//
//        System.out.println(storageItems);
//
//        storageItems.add(1);
//        System.out.println(storageItems);
//        storageItems.add(3);
//        storageItems.remove();
//        storageItems.remove();
//        storageItems.remove();
////        storageItems.remove();
//        storageItems.remove();
//
////        storageItems.add(1);
//        storageItems.add(1);
//
//        System.out.println(storageItems);
//        System.out.println("Index " + storageItems.getSize());
//
//        int x =  storageItems.getSize();
//
//        System.out.println(storageItems);
//
//        System.out.println(x);
//        System.out.println(storageItems);
//
//
//    }


}

