package services.payment;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import event.payment.FinishPaymentEvent;
import event.scan.ScanningEvent;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import modules.checkout.SelfServiceCheckout;
import services.payment.method.IPaymentMethod;
import services.payment.method.PaymentFactory;

@Getter
@Slf4j
public class PaymentService implements IPaymentService {
    private final AsyncEventBus eventBus;
    private final SelfServiceCheckout selfServiceCheckout;

    @Inject
    public PaymentService(AsyncEventBus eventBus,
                          SelfServiceCheckout selfServiceCheckout
    ) {
        this.eventBus = eventBus;
        this.selfServiceCheckout = selfServiceCheckout;

        log.info("eventBus (hashCode): {}", eventBus.hashCode());
        eventBus.register(this);
    }


    @Subscribe
    public void pay(ScanningEvent event) {
        for (int i = 0; i < event.getAmount(); i++) {
            double cost = selfServiceCheckout.getTotalSum();
            selfServiceCheckout.setTotalSum(event.getPrice() + cost);
            log.info("Current Total: {}$", selfServiceCheckout.getTotalSum());
        }
    }

    @Subscribe
    public void finishPayment(FinishPaymentEvent event) {
        IPaymentMethod paymentMethod = PaymentFactory.paymentMethod(event.getMethod());
        paymentMethod.processPayment(selfServiceCheckout.getTotalSum());
        log.info("Payment finished");
    }
}
