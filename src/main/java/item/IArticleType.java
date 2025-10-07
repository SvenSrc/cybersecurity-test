package item;

public interface IArticleType {
    double calculate(NormalItem item);

    double calculate(WeightedItem item);

    double calculate(DiscountedItem item);

    double calculate(AgeRestrictedItem item);
}
