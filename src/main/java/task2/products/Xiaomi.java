package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Xiaomi extends Phone {

    public Xiaomi(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
