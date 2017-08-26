package org.redrock.Jedis;

import java.util.Map;

public class Find {
    public static String find(Map<String ,String> room , String id) {
        int people = Integer.parseInt(room.get("people"));
        String res = "p";
        if(people >3 && people <= 6) {
            if(room.get("wrand").equals(id)){
                res = "w";
            }else{
                res = "p";
            }
        } else if (people > 6 && people <= 11) {
            if(room.get("wrand1").equals(id)||room.get("wrand2").equals(id)){
                res = "w";
            }else{
                res = "p";
            }
        } else if (people > 11 && people <= 15) {
            if(room.get("wrand1").equals(id)||room.get("wrand2").equals(id)||room.get("wrand3").equals(id)){
                res = "w";
            }else{
                res = "p";
            }
        } else {
            if(room.get("wrand1").equals(id)||room.get("wrand2").equals(id)||room.get("wrand3").equals(id)||room.get("wrand4").equals(id)){
                res = "w";
            }else{
                res = "p";
            }
        }
        return res;
    }
}
