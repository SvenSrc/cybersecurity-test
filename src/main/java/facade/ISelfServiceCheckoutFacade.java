package facade;

import item.Item;
import services.payment.method.enums.PaymentMethods;

public interface ISelfServiceCheckoutFacade {
    void scanned(Item item);

    void finishPayment(PaymentMethods method);
}
