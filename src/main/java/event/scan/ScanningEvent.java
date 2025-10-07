package event.scan;

import lombok.Data;

@Data
public class ScanningEvent {
    private final String name;
    private final double price;
    private final int amount;
}
