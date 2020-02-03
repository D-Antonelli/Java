package com.antonelli;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    private static Map<String, Basket> baskets = new LinkedHashMap<>();


    public static void main(String[] args) {

        Basket tom = new Basket("Tom");
        baskets.put(tom.getName(), tom);
        Basket alice = new Basket("Alice");
        baskets.put(alice.getName(), alice);
        Basket karla = new Basket("Karla");
        baskets.put(karla.getName(), karla);
        //Adding bread to the basket in available quantity

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        //Add 10 bread to Tom's basket
        tom.addToBasket(temp, 10);

        //Undeserved 5 breads
        tom.removeFromBasket(temp, 5);
        checkOut(tom);

        //Add 20 bread to Alice's basket
        alice.addToBasket(temp, 20);
        System.out.println(alice);

        //Check out the basket of Alice
        checkOut(alice);

        //Tom adds another 5 bread
        tom.addToBasket(temp, 5);

        //karla adds 20 bread to basket
        karla.addToBasket(temp, 20);
        System.out.println(stockList);
        checkOut(karla);


        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);
        karla.addToBasket(temp, 7);
        tom.addToBasket(temp, 1);
        System.out.println(stockList);

        temp = new StockItem("cake", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);
    }




    private static Basket checkOut(Basket basket) {
        if (basket != null) {
            System.out.println("\n******CHECK OUT PAGE********");
            System.out.println(basket + " is checking out. Please proceed to pay\n");
            Map<StockItem, Integer> basketList = basket.Items();
            for (Map.Entry<StockItem, Integer> item : basketList.entrySet()) {
                stockList.sellStock(item.getKey().getName(), item.getValue());
                basket.removeFromBasket(item.getKey(), item.getValue());
            }
            System.out.println("\n*****************CHECK OUT REPORT*******************");
            System.out.println(basket.getName() + "'basket has just checked out.\n");
            printBaskets(baskets);
            System.out.println(stockList);
            System.out.println("*********************************************");
            return basket;
        }
        return null;
    }

    private static void printBaskets(Map<String, Basket> baskets) {
        baskets.forEach((s, basket) -> {
             System.out.println("\n"+basket);
        });
    }
}
