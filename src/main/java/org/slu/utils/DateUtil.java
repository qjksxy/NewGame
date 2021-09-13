package org.slu.utils;

import org.slu.dao.UserDao;
import org.slu.pojo.User;

import java.sql.Date;

/**
 * Java真完蛋
 * 搞个Date类，连个算两个日期间的间隔天数的方法都没有
 * 还要我自己写
 * 顺便说一句，java.sql.Date 类没有直接生成当前时间对象的构造方法
 * 虽然有方便的实现
 * 但还是完蛋
 */
public class DateUtil {
    /**
     * 计算两个日期间的天数间隔
     * WARN:不可用于两个用毫秒数构造的对象
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 天数间隔，若第一个日期早于第二个日期，返回正数
     * 如，date1:2021-09-12，date2:2021-09-14，return:2
     */
    public static int getDaysBetweenTwoDates(Date date1, Date date2) {
        final int DAY = 86400000;
        int result;
        long time = date2.getTime() - date1.getTime();
        if(time >= 0) {
            if (time < DAY) {
                result = 0;
            } else {
                result = (int) (time / DAY);
            }
        } else {
            result = getDaysBetweenTwoDates(date2, date1) * -1;
        }
        return result;
    }
}
