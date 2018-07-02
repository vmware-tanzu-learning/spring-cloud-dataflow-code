package io.pivotal.scdf.si.xmltojson.domain;

public class Item {
    private String id;
    private Float price = -1F;
    private String description = "WRONG ITEM";

    public Item() {
    }

    public Item(String id, Float price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
