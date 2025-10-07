package customer;

import item.Item;
import services.payment.method.enums.PaymentMethods;

public interface ICustomer {
    void touchScreen();

    Item scanItem(String barcode, int amount);

    boolean weighItem(Item item);

    void payment(PaymentMethods method);

    void initializeBarcodeList();

    void initializePaymentMethods();
}
