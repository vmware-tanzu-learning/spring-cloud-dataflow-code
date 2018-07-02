package io.pivotal.scdf.si.xmltojson.service;

import io.pivotal.scdf.si.xmltojson.domain.Item;
import io.pivotal.scdf.si.xmltojson.domain.OrderItem;
import io.pivotal.scdf.si.xmltojson.repository.ItemRepository;

public class ItemPriceFixer {

    ItemRepository repo;
    public ItemPriceFixer(ItemRepository repo){
        this.repo = repo;
    }

    public OrderItem process(OrderItem orderItem){
        Item item = this.repo.findById(orderItem.getId());
        orderItem.setUnitPrice(item.getPrice());
        orderItem.setDescription(item.getDescription());
        return orderItem;
    }
}
