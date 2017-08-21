package org.redrock.misson1;


import org.redrock.misson1.jdbc.resultset.User;
import org.redrock.misson1.util.B64Util;
import org.redrock.misson1.util.CheckUtil;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        String aa = "我是gg";
        byte[] byteArray = B64Util.strToByteArray(aa);
        String a = B64Util.encode(byteArray);
        String bb = "我不是蒋天星";
        System.out.println(CheckUtil.CheckText(bb));
        User user = new User();
        if(user == null){
            System.out.println("gg");
        }else{
            System.out.println("??");
        }
        List<String> weus = new ArrayList<>();
        weus.add("222");
        weus.add("2223");
        weus.add("2224");
        weus.add("2225");
        weus.add("2226");
        weus.add("2227");
        weus.add("2228");
        System.out.println(weus.subList(9,12)+"           "+weus.size());

    }



}
