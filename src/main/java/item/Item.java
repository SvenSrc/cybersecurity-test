package item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Item {
    private String name;
    private double weight;

    //private double price;

    public abstract double accept(IArticleType articleType);
}
