package org.slu.pojo;

import org.junit.Test;
import org.slu.dao.UserDao;

import java.sql.Date;

public class UserTest {
    @Test
    public void getUserTest() {
        User user = UserDao.getUserByQqAcc("1234");
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println(user);
        }
    }

    @Test
    public void test() {
        User user = UserDao.getUserByQqAcc("865538395");
        Date nowDate = new Date(System.currentTimeMillis());


        Date date = user.getSignDate();

        if (date.before(nowDate)) {
            user.setGoldCoin(user.getGoldCoin() + 50);
            user.setSignDate(nowDate);
            if (date.compareTo(nowDate) == -1) {
                user.setContinueDay(user.getContinueDay() + 1);
                //
            } else {
                user.setContinueDay(0);
            }

            UserDao.updateUser(user);
        } else {

        }
    }

    @Test
    public void dateTest() {
        User user = UserDao.getUserByQqAcc("2937025459");
        Date nowDate = new Date(System.currentTimeMillis());
        user.setSignDate(nowDate);
        UserDao.updateUser(user);
    }
}
