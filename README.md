# A Basic Shopping Application

This is a simple Java code where you can add, delete, reserve and check out these items in the shopping basket. You can also set the stock list by addition and removal. 

Classes consist of:

  - StockItem.js  
  - StockList.js
  - Basket.js
  - Main.js
  - MainChallenge.js
  

# Introduction

In Udemy's Java Programming Masterclass by Tim Butchalka [here](https://www.udemy.com/course/java-the-complete-java-developer-course/), this is an assignment which requires modification of the main code provided by instructor to implement each step in the following:

- Modify the program so that adding items to the shopping basket doesn't
    actually reduce the stock count but, instead, reserves the requested
    number of items.

- You will need to add a "reserved" field to the StockItem class to store the
    number of items reserved.

- Items can continue to be added to the basket, but it should not be possible to
    reserve more than the available stock of any item. An item's available stock
    is the stock count less the reserved amount.

- The stock count for each item is reduced when a basket is checked out, at which
    point all reserved items in the basket have their actual stock count reduced.

- Once checkout is complete, the contents of the basket are cleared.
- It should also be possible to "unreserve" items that were added to the basket
    by mistake.
- The program should prevent any attempt to unreserve more items than were
    reserved for a basket.
- Add code to Main that will unreserve items after they have been added to the basket,
    as well as unreserving items that have not been added to make sure that the code
    can cope with invalid requests like that.
- After checking out the baskets, display the full stock list and the contents of each
    basket that you created.

### Tech

- Java
- IntelliJ

### I Learned How To

- Find bugs and errors 
- Implement immutable classes, Maps, Collections in Java
- Make modifications in a given code to add a feature 

### What I Did

- Created reservedStock field to StockItem class with adjustReservedStock() method where I can set reserved stock quantity.

```    public int adjustReservedStock(int quantity) {
        int newReservedStock = this.reservedStock + quantity;
        if (quantity <= this.quantityStock) {
            this.reservedStock = newReservedStock;
            return this.reservedStock;
        }
        return -1;
    }
```
- When items are added to the basket, they are passed to reserved stock. While in basket, items are reserved and held by customer. If desired, items can be unreserved and then,  added back to original stock count.
```
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
```
- Wrote check out method where the basket can be checked out. When done, reserved stock and item stock count reduced. After checking out, stock list and each basket contents are displayed.

```    private static Basket checkOut(Basket basket) {
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
```
- Added print baskets function.

```
    private static void printBaskets(Map<String, Basket> baskets) {
        baskets.forEach((s, basket) -> {
             System.out.println("\n"+basket);
        });
    }
```
- Bugs fixed: Customer can not add items unavailable in stock.
- Customer can not unreserve items that are unavailable in basket.
- Customer can not unreserve more items than available in basket.
- Customer can only add items on stock. Items hold by other customer basket can not be re-reserved.


Thank you for reading! 
