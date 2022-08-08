package task2;

import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    final Logger logger = Logger.getLogger("Consumer");
    private final Map<Object, Integer> productsMap;
    private final Queue<Object> storeProducts;


    public Consumer(final Map<Object, Integer> productsMap, final Queue<Object> storeProducts) {
        this.productsMap = productsMap;
        this.storeProducts = storeProducts;
    }


    public void decrease() throws InterruptedException {
        synchronized (storeProducts) {

            while (storeProducts.isEmpty()) {
                logger.info("No items to handle");
                storeProducts.wait();
            }
        }

        synchronized (storeProducts) {

            Thread.sleep(5000);
            Object productToRemove = storeProducts.peek();

            productsMap.replace(productToRemove, productsMap.get(productToRemove) - 1);


            System.out.println(productsMap.get(productToRemove));
            System.out.println("Decrease product to Queue");
            storeProducts.remove(productToRemove);


            storeProducts.notify();
        }

    }


    @Override
    public void run() {
        synchronized (this) {
            try {
                {
                    decrease();
                    logger.info(Thread.currentThread().getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
