package modules.scale;

import item.Item;

public interface IScale {
    boolean weighing(Item item);

    void calibrate();

    void activate();

    void deactivate();
}
