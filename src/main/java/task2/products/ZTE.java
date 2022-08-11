package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ZTE extends Phone {

    public ZTE(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
