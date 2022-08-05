package task2;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Store {

    //    int queueLimit = 7;
    private static final Queue<Object> storeProducts = new LinkedList<>();
    private static final ManageProducts manageProducts = new ManageProducts();
    private static final Map<Object, Integer> productsMap = manageProducts.initializeProducts();


    public static void main(String[] args) {


        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Producer(storeProducts, productsMap));
//            Thread producerThread = new Thread(new Producer(storeProducts, productsMap)); //
//            producerThread.start();
        }

//        for (int i = 0; i < 10; i++) {
//            Thread consumerThread = new Thread(new Consumer()); //
//        }
    }

}
