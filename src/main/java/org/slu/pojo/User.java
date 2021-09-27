package org.slu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
public class User {
    String qqAcc;           // QQ号
    int goldCoin;           // 金币数
    int copperCoin;         // 铜币数
    Date signDate;          // 上次签到日期
    int continueDay;        // 持续签到天数
    int signCount;          // 累计签到天数
    int prayCount;          // 祈愿次数
    int currentPrayCount;   // 当前奖池祈愿次数

    public String getQqAcc() {
        return qqAcc;
    }

    public void setQqAcc(String qqAcc) {
        this.qqAcc = qqAcc;
    }

    public int getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(int goldCoin) {
        this.goldCoin = goldCoin;
    }

    public int getCopperCoin() {
        return copperCoin;
    }

    public void setCopperCoin(int copperCoin) {
        this.copperCoin = copperCoin;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public int getContinueDay() {
        return continueDay;
    }

    public void setContinueDay(int continueDay) {
        this.continueDay = continueDay;
    }

    public int getSignCount() {
        return signCount;
    }

    public void setSignCount(int signCount) {
        this.signCount = signCount;
    }

    public int getPrayCount() {
        return prayCount;
    }

    public void setPrayCount(int prayCount) {
        this.prayCount = prayCount;
    }

    public int getCurrentPrayCount() {
        return currentPrayCount;
    }

    public void setCurrentPrayCount(int currentPrayCount) {
        this.currentPrayCount = currentPrayCount;
    }

    @Override
    public String toString() {
        return  "金币：" + goldCoin +
                "\n铜币：" + copperCoin +
                "\n上次签到日期：" + signDate +
                "\n连续签到天数：" + continueDay +
                "\n累计签到天数：" + signCount +
                "\n累计抽卡次数：" + prayCount +
                "\n当前版本抽卡次数：" + currentPrayCount;
    }
}
