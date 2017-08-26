package org.redrock.Jedis;

import java.util.Map;

import static org.redrock.Jedis.CreatRoom.Randnum;

public class Addwodi {
    public static Map<String,String> addwodi(Map<String,String> userroom,int people,int wodinum) {
        if(people >3 && people <= 6) {
            int wrand = Randnum(people/wodinum,1);
            userroom.put("wrand",String.valueOf(wrand));
            userroom.put("rank","1");
        } else if (people > 6 && people <= 11) {
            int wrand1 = Randnum(people/wodinum,1);
            int wrand2 = Randnum((people/wodinum)+wodinum,(people/wodinum)+1);
            userroom.put("wrand1",String.valueOf(wrand1));
            userroom.put("wrand2",String.valueOf(wrand2));
            userroom.put("rank","2");
        } else if (people > 11 && people <= 15) {
            int wrand1 = Randnum(people/wodinum,1);
            int wrand2 = Randnum((people/wodinum)+wodinum,(people/wodinum)+1);
            int wrand3 = Randnum((people/wodinum)+2*wodinum,(people/wodinum)+wodinum+1);
            userroom.put("wrand1",String.valueOf(wrand1));
            userroom.put("wrand2",String.valueOf(wrand2));
            userroom.put("wrand3",String.valueOf(wrand3));
            userroom.put("rank","3");
        } else {
            int wrand1 = Randnum(people/wodinum,1);
            int wrand2 = Randnum((people/wodinum)+wodinum,(people/wodinum)+1);
            int wrand3 = Randnum((people/wodinum)+2*wodinum,(people/wodinum)+wodinum+1);
            int wrand4 = Randnum((people/wodinum)+3*wodinum,(people/wodinum)+2*wodinum+1);
            userroom.put("wrand1",String.valueOf(wrand1));
            userroom.put("wrand2",String.valueOf(wrand2));
            userroom.put("wrand3",String.valueOf(wrand3));
            userroom.put("wrand4",String.valueOf(wrand4));
            userroom.put("rank","4");
        }
        return userroom;
    }

    public static Map<String,String> addtoroom(Map<String,String> userroom ,Map<String,String> room) {
        if (userroom.get("rank").equals("1")) {
            room.put("wrand", userroom.get("wrand"));
        } else if (userroom.get("rank").equals("2")) {
            room.put("wrand1", userroom.get("wrand1"));
            room.put("wrand2", userroom.get("wrand2"));
        } else if (userroom.get("rank").equals("3")) {
            room.put("wrand1", userroom.get("wrand1"));
            room.put("wrand2", userroom.get("wrand2"));
            room.put("wrand3", userroom.get("wrand3"));
        } else {
            room.put("wrand1", userroom.get("wrand1"));
            room.put("wrand2", userroom.get("wrand2"));
            room.put("wrand3", userroom.get("wrand3"));
            room.put("wrand4", userroom.get("wrand4"));
        }
        return  room;
    }
}
