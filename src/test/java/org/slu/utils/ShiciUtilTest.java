package org.slu.utils;

import org.junit.Test;

public class ShiciUtilTest {
    @Test
    public void test() {
        String[] msgs = {"100", "200"};
        String shici = ShiciUtil.getShici(msgs);
        char[] chars = shici.toCharArray();
        char tchar;
        for (int i = 0; i < chars.length; i++) {
            int randomInt = RandomUtil.getRandomInt(chars.length);
            tchar = chars[randomInt];
            chars[randomInt] = chars[i];
            chars[i] = tchar;
        }
        for (char aChar : chars) {
            System.out.println(aChar);
        }
        System.out.println(shici);
    }
}
