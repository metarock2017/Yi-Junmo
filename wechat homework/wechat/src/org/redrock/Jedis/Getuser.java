package org.redrock.Jedis;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class Getuser {
    public static int getuser(String username,Map<String,String> RPerson) {
        int all = RPerson.size();
        for (int i = 1; i <= all ; i++) {
            String id = String.valueOf(i);
            String res = RPerson.get(id);
            if(res.equals(username)){
                return i;
            }
        }
        return 0;
    }
}
