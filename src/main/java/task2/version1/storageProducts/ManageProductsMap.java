package task2.version1.storageProducts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import task2.products.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@ToString
public class ManageProductsMap {

    private Map<Object, Integer> productsMap = new HashMap<>();

    private final Apple apple = new Apple("Apple", "Iphone 13");
    private final Huawei huawei = new Huawei("Huawei", "Mate 40");
    private final LG lg = new LG("LG", "LG Velvet");
    private final Motorola motorola = new Motorola("Motorola", "Moto G9");
    private final Oppo oppo = new Oppo("Oppo", "Prime Z1");
    private final Samsung samsung = new Samsung("Samsung", "Samsung Galaxy S20");
    private final Sony sony = new Sony("Sony", "Xperia 30");
    private final Vivo vivo = new Vivo("Vivo", "Vivo Prime Z9");
    private final Xiaomi xiaomi = new Xiaomi("Xiaomi", "Mi 2s");
    private final ZTE zte = new ZTE("ZTE", "Ultimate ZTE 20");

    private volatile int countProducts = 0;

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
