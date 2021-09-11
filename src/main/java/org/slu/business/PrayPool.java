package org.slu.business;

import org.slu.utils.RandomUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 奖池类
 */
public class PrayPool {
    private PrayPool() {}

    private static int[] defaultPoolS = {0, 1, 2};
    private static ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("pray-pool-item.xml");

    public static PrayItem getItemFromPool(int prayPool, String qqAcc) {
        PrayItem resItem = null;
        PrayItem[] prayItems = PrayPool.getPoolItems(prayPool, qqAcc);
        int sumWeight = 0;
        for (PrayItem prayItem : prayItems) {
            sumWeight += prayItem.weight;
        }
        int randomInt = RandomUtil.getRandomInt(sumWeight);
        for (PrayItem prayItem : prayItems) {
            if(randomInt < prayItem.weight) {
                resItem = prayItem;
                break;
            } else {
                randomInt -= prayItem.weight;
            }
        }
        return resItem;
    }

    public static PrayItem[] getPoolItems(int prayPool, String qqAcc) {
        PrayItem[] prayItems = new PrayItem[defaultPoolS.length];
        for (int i = 0; i < defaultPoolS.length; i++) {
            PrayItem prayItem = (PrayItem) applicationContext.getBean("test" + defaultPoolS[i]);
            prayItems[i] = prayItem;
        }
        return prayItems;
    }
}
