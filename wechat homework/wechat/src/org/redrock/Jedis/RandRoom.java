package org.redrock.Jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

import static org.redrock.Jedis.CreatRoom.Randnum;

public class RandRoom {
    public static synchronized  Map<String,String> randroom(Jedis jedis , Map<String,String> userroom ,String time){
        boolean flag = true;
        while (flag) {
            int roomid = Randnum(9999, 1001);
            String roomid1 = String.valueOf(roomid);
            Map<String, String> foundroom = jedis.hgetAll("i" + roomid1);
            if (foundroom.isEmpty()) {
                Map<String, String> room = new HashMap<>();
                room.put("username", userroom.get("username"));
                room.put("people", userroom.get("people"));
                room.put("time", time);
                room.put("wodi", userroom.get("wodi"));
                room.put("pingmin", userroom.get("pingmin"));
                room.put("wodinum", userroom.get("wodinum"));
                room.put("pingminnum", userroom.get("pingminnum"));
                userroom.put("time", time);
                userroom.put("roomid", roomid1);
                int people = Integer.parseInt(userroom.get("people"));
                int wodinum = Integer.parseInt(userroom.get("wodinum"));
                userroom = Addwodi.addwodi(userroom, people, wodinum);
                room = Addwodi.addtoroom(userroom,room);
                jedis.hmset("i" + roomid1, room);
                flag = false;
            } else {
                if (System.currentTimeMillis() - Long.parseLong(foundroom.get("time")) > 1800000) {
                    foundroom.put("username", userroom.get("username"));
                    foundroom.put("people", userroom.get("people"));
                    foundroom.put("time", time);
                    foundroom.put("wodi", userroom.get("wodi"));
                    foundroom.put("pingmin", userroom.get("pingmin"));
                    foundroom.put("wodinum", userroom.get("wodinum"));
                    foundroom.put("pingminnum", userroom.get("pingminnum"));
                    userroom.put("roomid", String.valueOf(roomid));
                    userroom.put("time", time);
                    int people = Integer.parseInt(userroom.get("people"));
                    int wodinum = Integer.parseInt(userroom.get("wodinum"));
                    userroom = Addwodi.addwodi(userroom, people, wodinum);
                    foundroom = Addwodi.addtoroom(userroom,foundroom);
                    jedis.hmset("i" + String.valueOf(roomid), foundroom);
                    jedis.del("RPerson:"+userroom.get("roomid"));
                    flag = false;
                } else {
                    continue;
                }
            }
        }
        return userroom;
    }
}
