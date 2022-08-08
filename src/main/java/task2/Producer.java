package task2;

import java.util.*;
import java.util.logging.Logger;


public class Producer implements Runnable {

    final Logger logger = Logger.getLogger("Producer");

    final Queue<Object> storeProducts;

    final int queueLimit = 7;

    private final Map<Object, Integer> productsMap;


    public Producer(final Queue<Object> storeProducts, final Map<Object, Integer> productsMap) {
        this.storeProducts = storeProducts;
        this.productsMap = productsMap;
    }


    public void increase() throws InterruptedException {
        synchronized (storeProducts) {

            while (storeProducts.size() == queueLimit) {
                logger.info("Limit reached, wait");
                storeProducts.wait();
            }
        }

        synchronized (storeProducts) {

            Object productToAdd = getProductRandom();

            storeProducts.add(productToAdd);
            productsMap.replace(productToAdd, productsMap.get(productToAdd) + 1);//getValue(productsMap)+1);
            Thread.sleep(1000);

            System.out.println(productsMap.get(productToAdd));
            System.out.println("Add product to Queue");

            storeProducts.notify();


        }

    }

    public Object getProductRandom() {

        Random randomNumber = new Random();

        int randomProductNumber = randomNumber.nextInt(10);

        return productsMap.keySet().toArray()[randomProductNumber];
    }

    @Override
    public void run() {
        synchronized (this) {
            try {

                increase();
                logger.info(Thread.currentThread().getName());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
