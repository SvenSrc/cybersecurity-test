package modules.display;

import lombok.extern.slf4j.Slf4j;
import modules.checkout.SelfServiceCheckout;
import modules.enums.Status;

@Slf4j
public class Display implements IDisplay {
    private final SelfServiceCheckout selfServiceCheckout;
    private Status status;

    public Display(SelfServiceCheckout selfServiceCheckout) {
        log.info("Display has been initialized: {}", this);
        this.selfServiceCheckout = selfServiceCheckout;

        activate();
    }


    @Override
    public void showMessage(String message) {
        log.info("DISPLAY: {}", message);
    }

    @Override
    public void activateCheckout() {
        if (selfServiceCheckout.getStatus() != Status.Active) {
            selfServiceCheckout.activate();
        } else {
            log.info("Self-Service Checkout already active");
        }
    }

    @Override
    public void activate() {
        this.status = Status.Active;
        log.info("Display Status is set to: {}", this.status);

    }

    @Override
    public void deactiate() {
        this.status = Status.Inactive;
        log.info("Display Status is set to: {}", this.status);

    }
}
