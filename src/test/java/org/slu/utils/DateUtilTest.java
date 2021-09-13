package org.slu.utils;

import org.junit.Test;
import org.slu.dao.UserDao;
import org.slu.pojo.User;

import java.sql.Date;

public class DateUtilTest {

    public void test() {
        int day = 86400000;
        Date nowDate = new Date(System.currentTimeMillis());
        System.out.println(nowDate.getTime());

        User user1 = UserDao.getUserByQqAcc("865538395");
        Date date1 = user1.getSignDate();
        System.out.println(date1.getTime());

        User user2 = UserDao.getUserByQqAcc("3313445307");
        Date date2 = user2.getSignDate();
        System.out.println(date2.getTime());

        System.out.println(date1.getTime() - date2.getTime());
    }


    public void test2() {
        Date nowDate = new Date(System.currentTimeMillis());
        User user1 = UserDao.getUserByQqAcc("865538395");
        Date date1 = user1.getSignDate();
        User user2 = UserDao.getUserByQqAcc("2153708970");
        Date date2 = user2.getSignDate();
        int daysBetweenTwoDates = DateUtil.getDaysBetweenTwoDates(date1, nowDate);
        System.out.println(daysBetweenTwoDates);
    }
}
