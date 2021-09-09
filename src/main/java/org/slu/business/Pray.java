package org.slu.business;

import org.slu.utils.RandomUtil;

/**
 * 祈愿
 */
public class Pray {
    public static String pray(PrayInfo prayInfo) {
        String returnStr = "";
        PrayResult[] prayResults;
        prayResults = Pray.getPrayResults(prayInfo);
        for (PrayResult prayResult : prayResults) {
            returnStr += prayResult.toString() + "\n";
        }
        return returnStr;
    }

    /**
     * 祈愿实现
     * 首先随机出本次祈愿的奖励等级，其次在该奖励池中选取具体奖励，
     * 最后获取奖励数量。通过奖励等级、奖励内容、奖励数量生成PrayResult
     * @param prayInfo 祈愿的必要信息，其中至少包括账号以及祈愿次数
     * @return 返回祈愿的结果
     */
    public static PrayResult[] getPrayResults(PrayInfo prayInfo) {
        PrayResult[] prayResults = new PrayResult[prayInfo.prayNum];
        return prayResults;
    }
}
