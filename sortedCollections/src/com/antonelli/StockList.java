package com.antonelli;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //If there is equal item on stock, update its quantity
            if (inStock != item) {
                item.adjustStock(inStock.getQuantityStock());
            }

            list.put(item.getName(), item);
            return item.getQuantityStock();
        }
        return 0;
    }


    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if ((inStock != null) && (inStock.getReservedStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);

    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nStock List\n");
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * (stockItem.getQuantityStock() + stockItem.getReservedStock());

            s.append(stockItem).append(". There are ").append(stockItem.getQuantityStock()).append(" in stock. ").append("There are ")
                    .append(stockItem.getReservedStock()).append(" reserved in other baskets. ").append("Value of items: ");
            s.append(String.format("%.2f", itemValue)).append("\n");
            totalCost += itemValue;

        }
        return s + "Total stock value " + totalCost;
    }
}
