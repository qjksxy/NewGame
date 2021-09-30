package org.slu.pojo;

import org.slu.business.PrayItem;
import org.slu.business.PrayPool;

public class UserChip {
    String qqAcc;
    int chipID;
    int chipCount;
    int stars;

    public String getQqAcc() {
        return qqAcc;
    }

    public void setQqAcc(String qqAcc) {
        this.qqAcc = qqAcc;
    }

    public int getChipID() {
        return chipID;
    }

    public void setChipID(int chipID) {
        this.chipID = chipID;
    }

    public int getChipCount() {
        return chipCount;
    }

    public void setChipCount(int chipCount) {
        this.chipCount = chipCount;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        PrayItem prayItem = (PrayItem) PrayPool.getApplicationContext().getBean("h" + chipID);
        String name = prayItem.getName();
        return name + "*" + chipCount;
    }
}
