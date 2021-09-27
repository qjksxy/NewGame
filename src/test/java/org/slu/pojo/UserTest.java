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
        User user = new User();
        user.setSignDate(new Date(978278400000L));
        user.setQqAcc("123490");
        System.out.println(user.toString());
    }

}
