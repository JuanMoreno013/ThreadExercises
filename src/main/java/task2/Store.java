package task2;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Store {

    private static final Queue<Object> storeProducts = new LinkedList<>();
    private static final ManageProducts manageProducts = new ManageProducts();
    private static final Map<Object, Integer> productsMap = manageProducts.initializeProducts();


    public static void main(String[] args) {


        ExecutorService threadPoolProduce = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 15; i++) {
            threadPoolProduce.execute(new Producer(storeProducts, productsMap));
        }


        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Consumer(productsMap, storeProducts));
        }
    }

}
