package com.antonelli;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;


    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            if ((item.adjustReservedStock(quantity) >= 0) && (item.adjustStock(-quantity) >= 0)) {
                list.put(item, inBasket + quantity);
                return inBasket;
            }
        }
        return -1;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            if ((quantity <= inBasket) && (item.adjustStock(quantity) >= 0)) {
                int newInBasket = inBasket - quantity;
                if (newInBasket > 0) {
                    list.put(item, inBasket - quantity);
                } else {
                    list.remove(item);
                }
                item.adjustReservedStock(-quantity);
            }
            return inBasket;
        }
        return 0;
    }


    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n"+this.getName()+ "'s basket contains " +
                list.size() + ((list.size() == 1) ? " item " + "\n" : " items" + "\n"));
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s.append(item.getKey()).append(". ").append(item.getValue()).append(" in basket\n");
            totalCost = item.getKey().getPrice() * item.getValue();
        }

        return s + "Total cost " + String.format("%.2f",totalCost);
    }
}
