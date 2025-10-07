package modules.checkout;

import item.Item;

public interface ISelfServiceCheckout {
    void initializeDatabase();

    void initializeCustomerShoppingList();

    void addToShoppingList(Item item);

    void activate();

    void deactivate();

    void idle();
}
