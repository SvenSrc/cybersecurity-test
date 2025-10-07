package item;

public class WeightedItem extends Item {
    private final double pricerPerKg;
    private final double weight;

    public WeightedItem(String name, double weight, double pricerPerKg) {
        super(name, weight);
        this.pricerPerKg = pricerPerKg;
        this.weight = weight;
    }

    public double getTotalPrice() {
        return pricerPerKg * weight;
    }

    @Override
    public double accept(IArticleType articleType) {
        return articleType.calculate(this);
    }
}
