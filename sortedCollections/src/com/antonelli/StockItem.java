package com.antonelli;

public class StockItem implements Comparable<StockItem> {

    private final String name;
    private double price;
    private int quantityStock = 0;
    private int reservedStock = 0;


    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;

    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }


    public int adjustStock(int quantity) {
        //quantity can be -1 to drop the number
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
            return this.quantityStock;
        }
        return -1;
    }

    public int adjustReservedStock(int quantity) {
        int newReservedStock = this.reservedStock + quantity;
        if (quantity <= this.quantityStock) {
            this.reservedStock = newReservedStock;
            return this.reservedStock;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (obj == this) {
            return true;
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        //first check if objects are equal
        if (this == o) {
            return 0;
        }

        //check if object is NOT null
        if (o != null) {
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }


    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}
