package org.redrock.Jedis;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.redrock.util.Const;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.StreamSupport;

public class CreatRoom {
    public static Map<String , String> creatroom(Map<String, String> userroom, Jedis jedis) {
        String time = String.valueOf(System.currentTimeMillis());
//        userroom.put("time",time);
        if(userroom.get("roomid") == null) {
            userroom = RandRoom.randroom(jedis,userroom,time);
            return userroom;
        }else{
            Map<String, String> foundroom = jedis.hgetAll("i" + userroom.get("roomid"));
            if (System.currentTimeMillis() - Long.parseLong(foundroom.get("time")) > 1800000) {
                userroom = RandRoom.randroom(jedis,userroom,time);
                jedis.del("RPerson:"+userroom.get("roomid"));
                return userroom;
            }else{
                foundroom.put("username", userroom.get("username"));
                foundroom.put("people", userroom.get("people"));
                foundroom.put("time", time);
                foundroom.put("wodi", userroom.get("wodi"));
                foundroom.put("pingmin", userroom.get("pingmin"));
                foundroom.put("wodinum", userroom.get("wodinum"));
                foundroom.put("pingminnum", userroom.get("pingminnum"));
                userroom.put("time", time);
                int people = Integer.parseInt(userroom.get("people"));
                int wodinum = Integer.parseInt(userroom.get("wodinum"));
                userroom = Addwodi.addwodi(userroom, people, wodinum);
                foundroom = Addwodi.addtoroom(userroom,foundroom);
                jedis.hmset("i" + userroom.get("roomid"), foundroom);
                jedis.del("RPerson:"+userroom.get("roomid"));//TODO: 要看你自己要不要把这玩意删除  网页后面最好再加一个判断
                return userroom;
            }
        }
    }
//     jedis.hmset("roomdital:"+userroom.get("username"),userroom);
    public static int Randnum(int max, int min) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

}
