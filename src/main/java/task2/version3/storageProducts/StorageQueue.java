package task2.version3.storageProducts;

import lombok.Getter;
import lombok.ToString;
import task2.products.Phone;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class StorageQueue<T> {

    private final BlockingQueue<T> blockingQueue;
    private final int LIMIT_CAPACITY = 6;

    private final Map<Phone, AtomicInteger> productsMap;


    public StorageQueue(Map<Phone, AtomicInteger> productsMap) {
        this.productsMap = productsMap;
        this.blockingQueue = new LinkedBlockingQueue<>(LIMIT_CAPACITY);

    }

    public void put(T phoneStorage) throws InterruptedException {

        blockingQueue.put(phoneStorage);

        PhoneStorage newPhoneStorage = (PhoneStorage) phoneStorage;
        newPhoneStorage.getAmount().getAndIncrement();

        productsMap.replace(newPhoneStorage.getPhone(), newPhoneStorage.getAmount());
    }

    public T take() throws InterruptedException {

        T element = blockingQueue.take();

        PhoneStorage elementStorage = (PhoneStorage) element;
        elementStorage.getAmount().getAndDecrement();

        productsMap.replace(elementStorage.getPhone(), elementStorage.getAmount());

        return (T) elementStorage;
    }



}

