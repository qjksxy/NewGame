package org.slu.utils;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {
    static Random random = new Random();

    private RandomUtil() {
    }

    public static int getRandomInt(int limit) {
        return random.nextInt(limit);
    }

    public static double getRandomDouble() {
        return random.nextDouble();
    }

    public static boolean getRandomBool() {
        return random.nextBoolean();
    }
}
