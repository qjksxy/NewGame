package org.slu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String qqAcc;           // QQ号
    int goldCoin;           // 金币数
    int copperCoin;         // 铜币数
    Date signDate;          // 上次签到日期
    int continueDay;        // 持续签到天数
    int signCount;          // 累计签到天数
    int prayCount;          // 祈愿次数
    int currentPrayCount;   // 当前奖池祈愿次数

}
