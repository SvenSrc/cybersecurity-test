package services.scan;

import item.Item;

public interface IScanService {
    void scanned(Item item, int amount);
}
