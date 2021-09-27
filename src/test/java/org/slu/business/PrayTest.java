package org.slu.business;

import org.junit.Test;

public class PrayTest {

    public void test() {
        PrayInfo prayInfo = new PrayInfo();
        prayInfo.prayCount = 10;
        String pray = Pray.pray(prayInfo);
        System.out.println(pray);
    }


    public void getPoolItemsTest() {
        PrayItem[] prayItems = PrayPool.getPoolItems(0, "");
        System.out.println(prayItems.length);
        for (PrayItem prayItem : prayItems) {
            System.out.println(prayItem);
        }
    }


}
