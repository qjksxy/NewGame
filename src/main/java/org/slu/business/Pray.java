package org.slu.business;

import org.slu.utils.RandomUtil;

/**
 * 祈愿
 */
public class Pray {
    // 奖池：S、A、B、C
    public static final int PRAY_POOL_S = 0;
    public static final int PRAY_POOL_A = 1;
    public static final int PRAY_POOL_B = 2;
    public static final int PRAY_POOL_C = 3;

    /**
     * 获取祈愿结果信息
     *
     * @param prayInfo
     * @return 抽卡结果
     */
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
     * 奖品数量：在1~3范围内随机生成
     *
     * @param prayInfo 进行祈愿的必要信息，其中至少包括账号以及祈愿次数
     * @return 返回祈愿的结果
     */
    public static PrayResult[] getPrayResults(PrayInfo prayInfo) {
        PrayResult[] prayResults = new PrayResult[prayInfo.prayCount];
        for (int i = 0; i < prayInfo.prayCount; i++) {
            int prayPool = getPrayPool(prayInfo);
            int itemCount = getItemCount(3);
            PrayResult prayResult = getPrayItem(prayPool, prayInfo.qqAcc);
            prayResult.setCount(itemCount);
            prayResults[i] = prayResult;
        }
        return prayResults;
    }

    /**
     * 获取奖池
     * 基础概率为：S池10%，A池20%，B池30%，C池40%
     *
     * @param prayInfo 包含用户信息，仓检用
     * @return 枚举值
     * Pray.PRAY_POOL_S 奖池S
     * Pray.PRAY_POOL_A 奖池A
     * Pray.PRAY_POOL_B 奖池B
     * Pray.PRAY_POOL_C 奖池C
     */
    public static int getPrayPool(PrayInfo prayInfo) {
        int randomInt = RandomUtil.getRandomInt(100);
        if (randomInt < 10) {
            return PRAY_POOL_S;
        } else if (randomInt < 30) {
            return PRAY_POOL_A;
        } else if (randomInt < 60) {
            return PRAY_POOL_B;
        } else {
            return PRAY_POOL_C;
        }
    }

    /**
     * 获取祈愿奖品
     * 从奖品池中随机返回一个奖品
     *
     * @param prayPool 奖品池
     * @return
     */
    public static PrayResult getPrayItem(int prayPool, String qqAcc) {
        PrayResult prayResult = new PrayResult();
        PrayItem prayItem = PrayPool.getItemFromPool(prayPool, qqAcc);
        prayResult.setRating(prayItem.prayPool+"");
        prayResult.setId(prayItem.getId());
        prayResult.setName(prayItem.getName());
        return prayResult;
    }

    /**
     * 获取奖品数量
     * 从1~limit中随机生成数量返回
     *
     * @param limit 生成奖品数量上限
     * @return 奖品数量
     */
    public static int getItemCount(int limit) {
        return RandomUtil.getRandomInt(limit) + 1;
    }

}
