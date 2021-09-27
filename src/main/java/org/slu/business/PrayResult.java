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

    private void getRate() {
        if(this.rating.equals("0")) {
            this.rating = "S";
        } else if(this.rating.equals("1")) {
            this.rating = "A";
        } else if(this.rating.equals("2")) {
            this.rating = "B";
        } else if(this.rating.equals("3")) {
            this.rating = "C";
        }
    }

    @Override
    public String toString() {
        this.getRate();
        String str = "";
        str = this.rating + "·" + this.name + "*" + this.count;
        return str;
    }
}
