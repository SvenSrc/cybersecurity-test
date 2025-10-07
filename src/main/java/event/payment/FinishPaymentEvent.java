package event.payment;

import lombok.Data;
import services.payment.method.enums.PaymentMethods;

@Data
public class FinishPaymentEvent {
    private final PaymentMethods method;
}
