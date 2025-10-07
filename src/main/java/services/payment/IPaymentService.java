package services.payment;

import event.payment.FinishPaymentEvent;
import event.scan.ScanningEvent;

public interface IPaymentService {
    void pay(ScanningEvent event);

    void finishPayment(FinishPaymentEvent event);
}
