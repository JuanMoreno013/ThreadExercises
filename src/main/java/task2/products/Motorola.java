package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Motorola extends Phone {

    public Motorola(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
