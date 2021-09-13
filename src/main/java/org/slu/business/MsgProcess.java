package org.slu.business;

import org.slu.dao.UserDao;
import org.slu.pojo.User;
import org.slu.utils.RandomUtil;
import org.slu.utils.Text;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


public class MsgProcess {
    public static final String SIGN = "sign";
    public static final String PRAY = "pray";
    public static final String PRAY_2 = "draw";
    private static final String VERSION = "version";

    /* 用于保存用户状态码  以方便用户进行指令缩写
        如用户输入hero指令查看角色之后  再输入序号就可以查看角色详情
        而不必输入hero+序号
    */
    private static Map<String, Integer> userStatus =
            new HashMap<>();

    public static String msgProcess(String msg) {
        String returnMsg = "";
        String[] msgs = msg.split(" ");
        switch (msgs[1]) {
            case SIGN:
                returnMsg = sign(msgs);
                break;
            case PRAY:
            case PRAY_2:
                returnMsg = pray(msgs);
                break;
            case VERSION:
                returnMsg = Text.version;
        }
        return returnMsg;
    }

    private static String sign(String[] msgs) {
        String returnMsg;
        User user = UserDao.getUserByQqAcc(msgs[0]);
        Date nowDate = new Date(System.currentTimeMillis());
        Date date = user.getSignDate();
        if (date.before(nowDate)) {
            int copperGrowth = RandomUtil.getRandomInt(100) + 50;
            user.setGoldCoin(user.getGoldCoin() + 50);
            user.setCopperCoin(user.getCopperCoin() + copperGrowth);
            user.setSignDate(nowDate);
            user.setSignCount(user.getSignCount() + 1);
            returnMsg = "签到成功，金币+50，铜币+" + copperGrowth;
            if (date.compareTo(nowDate) == -1) {
                user.setContinueDay(user.getContinueDay() + 1);
                returnMsg += "连续签到第" + user.getContinueDay() + "天";
            } else {
                user.setContinueDay(0);
            }
            UserDao.updateUser(user);
        } else {
            returnMsg = "今天已经签到了哦";
        }
        return returnMsg;
    }

    private static String pray(String[] msgs) {
        int prayCount = 10;
        if (msgs.length > 2) {
            try {
                prayCount = Integer.parseInt(msgs[2]);
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        PrayInfo prayInfo = new PrayInfo();
        prayInfo.prayCount = prayCount;
        return Pray.pray(prayInfo);
    }
}
