package database;

import item.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Database {
    Map<String, Item> database = new HashMap<>();

    public Map<String, Item> readData() {
        database.put("12345", new NormalItem("Chocolate", 1, 0.99));
        database.put("23456", new NormalItem("Phone", 0.5, 109));
        database.put("34567", new WeightedItem("Apples", 10, 0.5));
        database.put("45678", new WeightedItem("Bread", 2.3, 0.1));
        database.put("56789", new DiscountedItem("Laptop", 3, 250, 25));
        database.put("67891", new DiscountedItem("Butter", 0.5, 1.19, 50));
        database.put("78912", new AgeRestrictedItem("Beer", 1, 0.49, 16));
        database.put("89123", new AgeRestrictedItem("Vodka", 1, 1, 18));

//        database.put("12345", new Item("Chocolate", 1,0.99));
//        database.put("23456", new Item("Phone", 0.5, 109));
//        database.put("34567", new Item("Apples", 10, 0.5));
//        database.put("45678", new Item("Bread", 2.3, 0.1));
//        database.put("56789", new Item("Laptop", 3, 250, 25 ));
//        database.put("67891", new Item("Butter", 0.5, 1.19, 50 ));
//        database.put("78912", new Item("Beer", 1, 0.49, 16));
//        database.put("89123", new Item("Vodka", 1, 1, 18));

        log.info("Database has been initialized: {}", this.database);
        return database;
    }
}
