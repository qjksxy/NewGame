package org.slu.business;

import org.slu.dao.UserChipDao;
import org.slu.dao.UserDao;
import org.slu.dao.UsernameDao;
import org.slu.pojo.User;
import org.slu.pojo.UserChip;
import org.slu.utils.DateUtil;
import org.slu.utils.RandomUtil;
import org.slu.utils.Text;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class MsgProcess {
    private static final String HELP = "help";
    private static final String SIGN = "sign";
    private static final String PRAY = "pray";
    private static final String PRAY_2 = "draw";
    private static final String ITEM = "item";
    private static final String SET_NAME = "setname";
    private static final String VERSION = "version";
    private static final String CHIP = "chip";

    /* 用于保存用户状态码  以方便用户进行指令缩写
        如用户输入hero指令查看角色之后  再输入序号就可以查看角色详情
        而不必输入hero+序号
    */
    private static Map<String, Integer> userStatus =
            new HashMap<>();

    public static String msgProcess(String[] msgs) {
        if (msgs.length < 2) {
            return "";
        }
        String returnMsg = "";
        switch (msgs[1]) {
            case HELP:
                returnMsg = Text.help;
                break;
            case SIGN:
                returnMsg = sign(msgs);
                break;
            case PRAY:
            case PRAY_2:
                returnMsg = pray(msgs);
                break;
            case VERSION:
                returnMsg = Text.version;
                break;
            case ITEM:
                returnMsg = item(msgs);
                break;
            case SET_NAME:
                returnMsg = setName(msgs);
                break;
            case CHIP:
                returnMsg = getChips(msgs);
                break;
        }
        return returnMsg;
    }
    private static String sign(String[] msgs) {
        String returnMsg = "";
        User user = UserDao.getUserByQqAcc(msgs[0]);
        Date nowDate = new Date(System.currentTimeMillis());
        Date date = new Date(user.getSignDate().getTime());
        int daysBetweenSignDateAndNow = DateUtil.getDaysBetweenTwoDates(date, nowDate);
        if (daysBetweenSignDateAndNow > 0) {
            int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hours == 23) {
                returnMsg = "才签到啊\n";
            }
            int copperGrowth = RandomUtil.getRandomInt(100) + 50;
            user.setGoldCoin(user.getGoldCoin() + 50);
            user.setCopperCoin(user.getCopperCoin() + copperGrowth);
            user.setSignDate(nowDate);
            user.setSignCount(user.getSignCount() + 1);
            returnMsg += "签到成功，金币+50，铜币+" + copperGrowth;
            if (daysBetweenSignDateAndNow == 1) {
                user.setContinueDay(user.getContinueDay() + 1);
                returnMsg += "\n连续签到" + user.getContinueDay() + "天";
            } else {
                user.setContinueDay(1);
            }
            returnMsg += "\n累计签到" + user.getSignCount() + "天";
            UserDao.updateUser(user);
        } else {
            returnMsg = "今天已经签到了哦";
        }
        return returnMsg;
    }

    private static String pray(String[] msgs) {
        User user = UserDao.getUserByQqAcc(msgs[0]);
        int prayCount = 10;
        if (msgs.length > 2) {
            try {
                prayCount = Integer.parseInt(msgs[2]);
                if(prayCount <= 0) {
                    return "...";
                } else if(prayCount > 50) {
                    return "梭哈需谨慎哦";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        if (user.getGoldCoin() >= prayCount * 10) {
            user.setGoldCoin(user.getGoldCoin() - prayCount * 10);
            UserDao.updateUser(user);
            PrayInfo prayInfo = new PrayInfo();
            prayInfo.prayCount = prayCount;
            prayInfo.qqAcc = msgs[0];
            return Pray.pray(prayInfo);
        } else {
            return "穷鬼，爬";
        }

    }

    public static String item(String[] msgs) {
        String returnMsg;
        User user = UserDao.getUserByQqAcc(msgs[0]);
        returnMsg = user.toString();
        return returnMsg;
    }

    public static String setName(String[] msgs) {
        String returnMsg = "";
        if(msgs.length < 3) {
            returnMsg = "失败";
        } else {
            if(msgs[2].length() > 25) {
                returnMsg = "小生记不得这许多名字";
            } else {
                UsernameDao.setUsername(msgs[0], msgs[2]);
                returnMsg = "成功";
            }
        }
        return returnMsg;
    }

    public static String getChips(String[] msgs) {
        String resMsg = "";
        UserChip[] userChips = UserChipDao.getUserChipsByQqAcc(msgs[0]);
        for (UserChip userChip : userChips) {
            resMsg += userChip.toString() + "\n";
        }
        return resMsg;
    }
}
