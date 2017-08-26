package org.redrock.Jedis;

import java.util.Map;

public class IsRoot {
    public static String isroot(Map<String,String> result , int people , int roomid) {
        String wodiis;
        if (people > 3 && people <= 6) {
            wodiis = result.get("wrand") + "号";
        } else if (people > 6 && people <= 11) {
            wodiis = result.get("wrand1") + "号 , " + result.get("wrand2") + "号";
        } else if (people > 11 && people <= 15) {
            wodiis = result.get("wrand1") + "号  ," + result.get("wrand2") + "号 , " + result.get("wrand3") + "号";
        } else {
            wodiis = result.get("wrand1") + "号 , " + result.get("wrand2") + "号 , " + result.get("wrand3") + "号 , " + result.get("wrand4") + "号";
        }
        String res = "您是该房间的房主\n" +
                "\t房间号为 :     " + String.valueOf(roomid) + "\n" +
                "\t关键字为 :  \n\r\t\t平民:" + result.get("pingmin") + "\n\r\t\t卧底:" + result.get("wodi") + "\n" +
                "\t其中,卧底为 " + wodiis;
        return  res;
    }
}
