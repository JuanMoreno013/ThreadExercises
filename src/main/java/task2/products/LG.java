package task2.products;

import lombok.Getter;

@Getter
public class LG {

    private final String nameCellphone;
    private final String model;

    public LG(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }
}
