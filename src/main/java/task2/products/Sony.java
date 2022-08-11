package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Sony extends Phone {

    public Sony(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
