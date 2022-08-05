package task2;

import java.util.*;

public class Producer implements Runnable {

    final Queue<Object> storeProducts;

    ManageProducts manageProducts = new ManageProducts();

    final int queueLimit = 7;

    private final Map<Object, Integer> productsMap;

    public Producer(final Queue<Object> storeProducts, final Map<Object, Integer> productsMap) {
        this.storeProducts = storeProducts;
        this.productsMap = productsMap;
    }


    public void increase() throws InterruptedException {
        synchronized (storeProducts) {

            while (storeProducts.size() == queueLimit) {

                storeProducts.wait();
            }
        }

        synchronized (storeProducts) {

            Object productToAdd = getProductRandom();

            storeProducts.add(productToAdd);
            productsMap.replace(productToAdd, productsMap.get(productToAdd) + 1);//getValue(productsMap)+1);
//            System.out.println(productsMap.get(productToAdd) );

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

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
