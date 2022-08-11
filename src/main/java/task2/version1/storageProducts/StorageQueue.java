package task2.version1.storageProducts;

import lombok.Getter;
import lombok.ToString;
import task2.products.Phone;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Getter
@ToString
public class StorageQueue {

    private final Queue<PhoneStorage> phoneStorageQueue;

    private final int LIMIT_CAPACITY = 5;

    private final Map<Phone, Integer> productsMap;


    public StorageQueue(Map<Phone, Integer> productsMap) {
        this.productsMap = productsMap;
        phoneStorageQueue = new LinkedList<>();
    }

    public void add(PhoneStorage phoneStorage) throws InterruptedException {

        synchronized (this){
            while (isFull()) {
                wait();
            }

            if (phoneStorageQueue.size() < LIMIT_CAPACITY) {
                phoneStorageQueue.add(phoneStorage);
                notify();
            }
        }

        Phone phoneMapKey = phoneStorage.getPhone();
        productsMap.putIfAbsent(phoneMapKey, phoneStorage.getAmount());
    }

    public synchronized int size() {
        return phoneStorageQueue.size();
    }

    private boolean isFull() {
        return size() == LIMIT_CAPACITY;
    }


    public synchronized PhoneStorage peek() throws InterruptedException {

        while (phoneStorageQueue.isEmpty()) {
            wait();
        }
        notify();
        return phoneStorageQueue.peek();
    }

    public synchronized boolean isEmpty() throws InterruptedException {
        while (phoneStorageQueue.isEmpty()){
            wait();
        }
        notify();
        return phoneStorageQueue.isEmpty();
    }

    public synchronized PhoneStorage poll() {

        while (phoneStorageQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        PhoneStorage phoneStorageElement = phoneStorageQueue.poll();
        productsMap.replace(phoneStorageElement.getPhone(), phoneStorageElement.getAmount());

        notify();
        return phoneStorageElement;
    }

}

