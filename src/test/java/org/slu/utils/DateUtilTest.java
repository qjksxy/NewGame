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
        int HOURS13 = 46800000;
        int DAY = HOURS13 / 13 * 24;
        String[] msgs = {"834375570", "sign"};
        User user = UserDao.getUserByQqAcc(msgs[0]);
        Date date = new Date(System.currentTimeMillis() + DAY * 4);
        System.out.println(user.getSignDate().getTime());
        user.setSignDate(date);
        UserDao.updateUser(user);
    }


    public void timeTest() {
        int time = 46800000;
        Date nowDate = new Date(System.currentTimeMillis());
        User user1 = UserDao.getUserByQqAcc("865538395");
        Date date1 = user1.getSignDate();
        System.out.println(nowDate.getTime());
        System.out.println(date1.getTime() - time);
    }
}
