package io.pivotal.scdf.si.xmltojson.repository;

import io.pivotal.scdf.si.xmltojson.domain.Item;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ItemRepository {
    private List<Item> items = Arrays.asList(new Item("0321200683",6.99F,"Sushi Combo Platter"),new Item("1590596439",4.55F,"Sake"),new Item("1590596440",3.25F,"Red Wine Glass"));

    public Item findById(String id){
        Optional<Item> item = items.stream().filter(unit -> unit.getId().equals(id)).findFirst();
        if(item.isPresent())
            return item.get();
        return new Item();

    }
}
