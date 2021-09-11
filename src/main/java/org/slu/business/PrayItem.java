package org.slu.business;

import lombok.Data;

/**
 * 奖池内物品类
 * 记录归属于哪一个奖池以及物品ID
 */
@Data
public class PrayItem {
    int id;
    int prayPool;
    String name;
    int weight;
}
