package org.slu.business;

import org.slu.dao.UserChipDao;
import org.slu.pojo.UserChip;

public class PrayInfo {
    String qqAcc;       //抽卡用户
    int prayCount;      //抽卡次数

    public void getResults(PrayResult[] prayResults) {
        for (PrayResult prayResult : prayResults) {
            UserChip userChip = UserChipDao.getUserChip(this.qqAcc, prayResult.id);
            if (userChip == null) {
                userChip = new UserChip();
                userChip.setChipID(prayResult.id);
                userChip.setQqAcc(this.qqAcc);
                userChip.setChipCount(prayResult.count);
                userChip.setStars(0);
                UserChipDao.insertUserChip(userChip);
            } else {
                userChip.setChipCount(userChip.getChipCount() + prayResult.count);
                UserChipDao.updateUserChip(userChip);
            }
        }
    }
}
