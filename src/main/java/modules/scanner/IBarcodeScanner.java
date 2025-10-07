package modules.scanner;

import item.Item;

public interface IBarcodeScanner {
    Item scanning(String barcode, int amount);

    void activate();

    void deactivate();
}
