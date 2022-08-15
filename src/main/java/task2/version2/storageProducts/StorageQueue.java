package task2.version2.storageProducts;

import lombok.Getter;
import lombok.ToString;
import task2.products.Phone;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@ToString
public class StorageQueue<T> {

//    private final Queue<PhoneStorage> phoneStorageQueue;

    private final BlockingQueue<T> blockingQueue;
    private final int LIMIT_CAPACITY = 6;

    private final Map<Phone, Integer> productsMap;


    public StorageQueue(Map<Phone, Integer> productsMap) {
        this.productsMap = productsMap;
        this.blockingQueue = new LinkedBlockingQueue<>(LIMIT_CAPACITY);

    }

    public void put(T phoneStorage) throws InterruptedException {

        blockingQueue.put(phoneStorage);

        PhoneStorage newPhoneStorage = (PhoneStorage) phoneStorage;
        newPhoneStorage.getAmount().getAndIncrement();

        productsMap.replace(newPhoneStorage.getPhone(), newPhoneStorage.getAmount().get());
    }

    public T take() throws InterruptedException {

        T element = blockingQueue.take();

        PhoneStorage elementStorage = (PhoneStorage) element;
        elementStorage.getAmount().getAndDecrement();

        productsMap.replace(elementStorage.getPhone(), elementStorage.getAmount().get());

        return (T) elementStorage;
    }



}

