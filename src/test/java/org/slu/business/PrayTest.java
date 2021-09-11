package org.slu.business;

import org.junit.Test;

import java.lang.reflect.Array;

public class PrayTest {
    @Test
    public void test() {
        PrayInfo prayInfo = new PrayInfo();
        prayInfo.prayCount = 10;
        String pray = Pray.pray(prayInfo);
        System.out.println(pray);
    }

    @Test
    public void getPoolItemsTest() {
        PrayItem[] prayItems = PrayPool.getPoolItems(0, "");
        System.out.println(prayItems.length);
        for (int i = 0; i < prayItems.length; i++) {
            System.out.println(prayItems[i]);
        }
    }
}
