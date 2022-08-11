package task2.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Huawei extends Phone {

    public Huawei(String nameCellphone, String model) {
        super(nameCellphone, model);
    }
}
