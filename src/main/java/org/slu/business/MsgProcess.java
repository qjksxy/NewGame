package org.slu.business;

import org.slu.utils.Text;


public class MsgProcess {
    public static final String SIGN = "sign";
    public static final String PRAY = "pray";
    public static final String DRAW = "draw";
    private static final String VERSION = "version";

    public static String msgProcess(String msg) {
        String returnMsg = "";
        String[] msgs = msg.split(" ");
        switch (msgs[1]) {
            case SIGN:
                returnMsg = sign();
                break;
            case PRAY:
            case DRAW:
                returnMsg = pray(msgs);
                break;
            case VERSION:
                returnMsg = Text.version;
        }
        return returnMsg;
    }

    private static String sign() {
        return "程序没写好呢，签个der";
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
