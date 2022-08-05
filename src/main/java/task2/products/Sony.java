package task2.products;

import lombok.Getter;

@Getter
public class Sony {

    private final String nameCellphone;
    private final String model;

    public Sony(String nameCellphone, String model) {
        this.nameCellphone = nameCellphone;
        this.model = model;
    }

}
