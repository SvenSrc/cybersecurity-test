package services.payment.method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobilePayment implements IPaymentMethod {
    @Override
    public void processPayment(double amount) {
        log.info("Processing through Mobile Payment: {}$", amount);
    }
}
