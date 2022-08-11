package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Oppo extends Phone {

    public Oppo(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
