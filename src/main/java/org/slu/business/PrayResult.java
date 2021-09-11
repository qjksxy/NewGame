package org.slu.business;

public class PrayResult {
    public String rating;   //奖品等级
    public int count;       //奖品数量
    public String name;     //奖品名
    public String desc;     //奖品描述
    public int id;          //奖品ID

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PrayResult{" +
                "rating='" + rating + '\'' +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                '}';
    }
}
