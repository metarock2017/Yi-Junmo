package org.redrock.Jedis;

import org.redrock.util.Const;
import redis.clients.jedis.Jedis;

import javax.xml.bind.Element;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    public static int id = 1;
    public static Map<String, String> PutRoomJedis(Map<String,String> userroom) {
        Jedis jedis = new Jedis(Const.ip);
        if(jedis.hgetAll("roomdital:"+userroom.get("username")).isEmpty()){
            userroom = CreatRoom.creatroom(userroom,jedis);
            jedis.hmset("roomdital:"+userroom.get("username"),userroom);
            return userroom;
        }else{
            Map<String,String> o_userroom = jedis.hgetAll("roomdital:"+userroom.get("username"));
            userroom.put("roomid",o_userroom.get("roomid"));
            userroom = CreatRoom.creatroom(userroom,jedis);
            jedis.hmset("roomdital:"+userroom.get("username"),userroom);
            return userroom;
        }

    }

    public static synchronized  String  JoinRoom(String username, int roomid) {
        Jedis jedis = new Jedis(Const.ip);
        Map<String,String > room = jedis.hgetAll("i"+String.valueOf(roomid));
        if(room.isEmpty()){
            String  res = "房间不存在 请确认之后再进行查询";
            return res;
        }else {
            long time = Long.parseLong(room.get("time"));
            if (System.currentTimeMillis() - time > 1800000) {
                String res = "输入的房间已过期  请重新创建新的房间";
                return res;
            } else {
                if (room.get("username").equals(username)) {
                    Map<String, String> result = jedis.hgetAll("roomdital:" + username);
                    int people1 = Integer.parseInt(result.get("people"));
                    String res = IsRoot.isroot(result,people1,roomid);
                    return res;
                } else {
                    Map<String ,String> res = jedis.hgetAll("RPerson:"+String.valueOf(roomid));
                    if(res.isEmpty()){
                        res.put("1",username);
                        jedis.hmset("RPerson:"+String.valueOf(roomid),res);
                        String res1 = Find.find(room,"1");
                        if(res1.equals("w")){
                            res1 = room.get("wodi");
                        }else{
                            res1 = room.get("pingmin");
                        }
                        String res2 = "恭喜您成功加入游戏\n "+
                                "\t房号:  "+ String.valueOf(roomid)+"\n"+
                                "\t词语:  "+ res1 +"\n"+
                                "\t你是:  " + "1 号" + "\n"+
                                "\t配置:  "+ room.get("wodinum")+" 个卧底 ,"+ room.get("pingminnum")+"个平民\n"+
                                "可输入6以查看惩罚帮助";
                        return res2;
                    }else{
                        int people = Integer.parseInt(room.get("people"));
                        if(res.size() == people) {
                            int nid = Getuser.getuser(username,res);
                            if( nid > 0){
                                String res1 = Find.find(room, String.valueOf(nid));
                                if (res1.equals("w")) {
                                    res1 = room.get("wodi");
                                } else {
                                    res1 = room.get("pingmin");
                                }
                                String res2 = "您已经加入了该房间\n " +
                                        "\t房号:  " + String.valueOf(roomid) + "\n" +
                                        "\t词语:  " + res1 + "\n" +
                                        "\t你是:  " + nid + " 号" + "\n" +
                                        "\t配置:  " + room.get("wodinum") + " 个卧底 ," + room.get("pingminnum") + "个平民\n"+
                                        "可输入6以查看惩罚帮助";
                                return res2;
                            }else {
                                String res1 = "很抱歉,房间人数已满";
                                return res1;
                            }
                        }else{
                            int nid = Getuser.getuser(username,res);
                            if(nid > 0){
                                String res1 = Find.find(room, String.valueOf(nid));
                                if (res1.equals("w")) {
                                    res1 = room.get("wodi");
                                } else {
                                    res1 = room.get("pingmin");
                                }
                                String res2 = "您已经加入了该房间\n " +
                                        "\t房号:  " + String.valueOf(roomid) + "\n" +
                                        "\t词语:  " + res1 + "\n" +
                                        "\t你是:  " + nid + " 号" + "\n" +
                                        "\t配置:  " + room.get("wodinum") + " 个卧底 ," + room.get("pingminnum") + "个平民\n"+
                                        "可输入6以查看惩罚帮助";
                                return res2;
                            }else {
                                String id = String.valueOf(res.size() + 1);
                                res.put(id, username);
                                jedis.hmset("RPerson:" + String.valueOf(roomid), res);
                                String res1 = Find.find(room, id);
                                if (res1.equals("w")) {
                                    res1 = room.get("wodi");
                                } else {
                                    res1 = room.get("pingmin");
                                }
                                String res2 = "恭喜您成功加入游戏\n " +
                                        "\t房号:  " + String.valueOf(roomid) + "\n" +
                                        "\t词语:  " + res1 + "\n" +
                                        "\t你是:  " + id + " 号" + "\n" +
                                        "\t配置:  " + room.get("wodinum") + " 个卧底 ," + room.get("pingminnum") + "个平民\n"+
                                        "可输入6以查看惩罚帮助";
                                return res2;
                            }
                        }
                    }
                }
            }
        }
    }

    public static String isme() {
        Jedis jedis = new Jedis(Const.ip);
        jedis.flushAll();
        String res = "删库成功";
        return res;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

}
