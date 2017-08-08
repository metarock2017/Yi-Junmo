package org.redrock.set;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wang on 2017/8/6.
 */
public class Parcel8 {
    public static void main(String[] args) {
        //Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        //Map<String,Integer> words = new HashMap<>();
        //这里的路径需要自己找一个文件替换
        System.out.println(new TextFile("D:\\java\\Parcel\\src\\org\\redrock\\1.txt", "\\w+").getData());
//        System.out.println(words);
//        Set<String> word = new TreeSet<String>();
        //作业：选择一个文件，利用Set容器的特性，计算该文件中有多少个不同的单词
    }
}
