package task2.version1;

import org.apache.log4j.BasicConfigurator;
import task2.products.*;
import task2.version1.storageProducts.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Store {
    private static final Map<Phone, Integer> productsMap = new HashMap<>();
    private static final StorageQueue storeQueue = new StorageQueue(productsMap);


    private static final Apple apple = new Apple("Apple", "Iphone 13");
    private static final Huawei huawei = new Huawei("Huawei", "Mate 40");
    private static final LG lg = new LG("LG", "LG Velvet");
    private static final Motorola motorola = new Motorola("Motorola", "Moto G9");
    private static final Oppo oppo = new Oppo("Oppo", "Prime Z1");
    private static final Samsung samsung = new Samsung("Samsung", "Samsung Galaxy S20");
    private static final Sony sony = new Sony("Sony", "Xperia 30");
    private static final Vivo vivo = new Vivo("Vivo", "Vivo Prime Z9");
    private static final Xiaomi xiaomi = new Xiaomi("Xiaomi", "Mi 2s");
    private static final ZTE zte = new ZTE("ZTE", "Ultimate ZTE 20");

    public static void initializeMapProducts() {
        int countProducts = 0;

        productsMap.put(apple, countProducts);
        productsMap.put(huawei, countProducts);
        productsMap.put(lg, countProducts);
        productsMap.put(motorola, countProducts);
        productsMap.put(oppo, countProducts);
        productsMap.put(samsung, countProducts);
        productsMap.put(sony, countProducts);
        productsMap.put(vivo, countProducts);
        productsMap.put(xiaomi, countProducts);
        productsMap.put(zte, countProducts);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        initializeMapProducts();

        PhoneStorage[] phoneStorages = new PhoneStorage[10];
        phoneStorages[0] = new PhoneStorage(apple);
        phoneStorages[1] = new PhoneStorage(huawei);
        phoneStorages[2] = new PhoneStorage(lg);
        phoneStorages[3] = new PhoneStorage(motorola);
        phoneStorages[4] = new PhoneStorage(oppo);
        phoneStorages[5] = new PhoneStorage(samsung);
        phoneStorages[6] = new PhoneStorage(sony);
        phoneStorages[7] = new PhoneStorage(vivo);
        phoneStorages[8] = new PhoneStorage(xiaomi);
        phoneStorages[9] = new PhoneStorage(zte);


        ExecutorService threadPoolProduce = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            threadPoolProduce.execute(new Producer(phoneStorages, storeQueue));


        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 8; i++)
            threadPool.execute(new Consumer(storeQueue));


//        productsMap.forEach((key, value) -> System.out.println("MAPAaaa " +key + " " + value));
    }


}
