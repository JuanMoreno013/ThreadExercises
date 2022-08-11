package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LG extends Phone {


    public LG(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
