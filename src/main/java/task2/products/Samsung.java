package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Samsung extends Phone {

    public Samsung(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
