package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Vivo extends Phone {

    public Vivo(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
