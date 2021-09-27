package org.slu.business;

import org.slu.utils.RandomUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;

/**
 * 奖池类
 */
public class PrayPool {
    private PrayPool() {}

    private static List<String> poolS = new LinkedList<>();
    private static List<String> poolA = new LinkedList<>();
    private static List<String> poolB = new LinkedList<>();
    private static List<String> poolC = new LinkedList<>();

    private static ApplicationContext applicationContext =
            new FileSystemXmlApplicationContext("prayItem.xml");
    static {
        poolS.add("test0");
        poolS.add("test1");
        poolS.add("test2");
        poolA.add("test3");
        poolA.add("test4");
        poolA.add("test5");
        poolB.add("test6");
        poolB.add("test7");
        poolB.add("test8");
        poolB.add("test9");
        poolC.add("test10");
        poolC.add("test11");
    }

    public static void initPool() {
        applicationContext = new FileSystemXmlApplicationContext("praytest.xml");
    }

    public static List<String> getPoolByInt(int prayPool) {
        List<String> prayPoolList = null;
        switch (prayPool) {
            case Pray.PRAY_POOL_S:
                prayPoolList = poolS;   break;
            case Pray.PRAY_POOL_A:
                prayPoolList = poolA;   break;
            case Pray.PRAY_POOL_B:
                prayPoolList = poolB;   break;
            case Pray.PRAY_POOL_C:
                prayPoolList = poolC;   break;
        }
        return prayPoolList;
    }

    public boolean putItemIntoPool(int prayPool, String itemName) {
        List<String> prayPoolList = getPoolByInt(prayPool);
        if(prayPoolList == null) {
            return false;
        }
        return prayPoolList.add(itemName);
    }

    public boolean removeItemFromPool (int prayPool, String itemName) {
        List<String> prayPoolList = getPoolByInt(prayPool);
        if(prayPoolList == null) {
            return false;
        }
        return prayPoolList.remove(itemName);
    }

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
        List<String> prayPoolList = PrayPool.getPoolByInt(prayPool);
        PrayItem[] prayItems = new PrayItem[prayPoolList.size()];
        for (int i=0; i<prayPoolList.size(); i++) {
            PrayItem prayItem = (PrayItem) applicationContext.getBean(prayPoolList.get(i));
            prayItems[i] = prayItem;
        }
        return prayItems;
    }
}
