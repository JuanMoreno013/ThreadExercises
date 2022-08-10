package task2.version1.storageProducts;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Getter
@ToString
public class StorageQueue {

    private final Queue<PhoneStorage> phoneStorages;

    private final int LIMIT_CAPACITY = 5;

    private final Map<Object, Integer> productsMap;


    public StorageQueue(Map<Object, Integer> productsMap) {
        this.productsMap = productsMap;
        phoneStorages = new LinkedList<>();
    }


    public synchronized void add(PhoneStorage phoneStorage) throws InterruptedException {

        while (isFull()) {
            wait();
        }

        if (phoneStorages.size() < LIMIT_CAPACITY) {
            phoneStorages.add(phoneStorage);
            notify();
        }

        Object keyPhone = phoneStorage.getPhone().getPhone();
        productsMap.replace(keyPhone, productsMap.get(keyPhone) + 1);
    }

    public synchronized int size() {
        return phoneStorages.size();
    }

    private boolean isFull() {
        return size() == LIMIT_CAPACITY;
    }


    public synchronized PhoneStorage peek() throws InterruptedException {

        while (phoneStorages.isEmpty()) {
            wait();
        }
        notify();
        return phoneStorages.peek();
    }

    public synchronized boolean isEmpty() {
        notify();
        return phoneStorages.isEmpty();
    }

    public synchronized void decreaseMapValue(PhoneStorage phoneStorage) {

        Object keyPhone = phoneStorage.getPhone().getPhone();
        productsMap.replace(keyPhone, productsMap.get(keyPhone) + 1);
    }

    public synchronized PhoneStorage poll() {
        while (phoneStorages.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        PhoneStorage phoneStorageElement = phoneStorages.poll();
        notify();
        return phoneStorageElement;
    }

}

