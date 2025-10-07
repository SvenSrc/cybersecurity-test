package services.payment.method;

import lombok.extern.slf4j.Slf4j;
import services.payment.method.enums.PaymentMethods;

@Slf4j
public class PaymentFactory {
    public static IPaymentMethod paymentMethod(PaymentMethods method) {
        switch (method) {
            case PaymentMethods.CARD -> {
                return new CardPayment();
            }
            case PaymentMethods.CASH -> {
                return new CashPayment();
            }
            case PaymentMethods.MOBILE -> {
                return new MobilePayment();
            }
            default -> {
                log.info("Payment not possible as it is not supported");
                return null;
            }
        }
    }
}
