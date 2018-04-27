package io.pivotal.scdf.si.xmltojson.domain;

import java.util.List;

public class Order {

    private List<OrderItem> itemList;

    public Order() {
    }

    public Order(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemList=" + itemList +
                '}';
    }
}
