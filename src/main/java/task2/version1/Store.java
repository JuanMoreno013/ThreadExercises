package task2.version1;

import task2.version1.storageProducts.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Store {


    private static final ManageProductsMap MANAGE_PRODUCTS_MAP = new ManageProductsMap();
    private static final Map<Object, Integer> productsMap = MANAGE_PRODUCTS_MAP.initializeProducts();
    private static final StorageQueue storeProducts = new StorageQueue(productsMap);

    public static void main(String[] args) {


        PhoneStorage[] storages = new PhoneStorage[10];
        storages[0] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[0]));
        storages[1] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[1]));
        storages[2] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[2]));
        storages[3] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[3]));
        storages[4] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[4]));
        storages[5] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[5]));
        storages[6] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[6]));
        storages[7] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[7]));
        storages[8] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[8]));
        storages[9] = new PhoneStorage(new Phone(productsMap.keySet().toArray()[9]));
        Storage storage = new Storage(storages);

        ExecutorService threadPoolProduce = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++)
            threadPoolProduce.execute(new Producer(storeProducts, storage));


        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 8; i++)
            threadPool.execute(new Consumer(storeProducts));

    }


}
