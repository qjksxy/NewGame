package org.slu.pojo;

public class Item {
    int itemID;
    String name;
    String desc;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ID:" + itemID +
                "\n" + name +
                '\n' + desc;
    }
}
