package task2;

import lombok.Getter;
import lombok.Setter;
import task2.products.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class ManageProducts {

    private Map<Object, Integer> productsMap = new HashMap<>();

    private Apple apple = new Apple("Apple", "Iphone 13");
    private Huawei huawei = new Huawei("Huawei", "Mate 40");
    private LG lg = new LG("LG", "LG Velvet");
    private Motorola motorola = new Motorola("Motorola", "Moto G9");
    private Oppo oppo = new Oppo("Oppo", "Prime Z1");
    private Samsung samsung = new Samsung("Samsung", "Samsung Galaxy S20");
    private Sony sony = new Sony("Sony", "Xperia 30");
    private Vivo vivo = new Vivo("Vivo", "Vivo Prime Z9");
    private Xiaomi xiaomi = new Xiaomi("Xiaomi", "Mi 2s");
    private ZTE zte = new ZTE("ZTE", "Ultimate ZTE 20");


    private volatile int countProducts = 0;
    private String typeProduct;


    public Map<Object, Integer> initializeProducts() {


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

        return productsMap;
    }

}
