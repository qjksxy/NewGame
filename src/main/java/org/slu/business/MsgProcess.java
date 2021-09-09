package org.slu.business;

import org.slu.dao.DemoDao;
import org.slu.pojo.Demo;

import java.util.List;

public class MsgProcess {
    public static String msgProcess(String msg) {
        String returnMsg = "";
        String[] msgs = msg.split(" ");
        if (msgs[0].equals("insert")) {
            try {
                int goldCoin = Integer.parseInt(msgs[2]);
                DemoDao.insertDemo(msgs[1], goldCoin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgs[0].equals("select")) {
            List<Demo> demoList = DemoDao.getDemoList();
            for (Demo demo : demoList) {
                returnMsg += demo.toString();
            }
        }
        return returnMsg;
    }
}
