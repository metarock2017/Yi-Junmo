package org.redrock.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 栈：后进先出(LIFO)容器
 * Created by wang on 2017/8/6.
 */
public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();

    public void push(T v) {storage.addFirst(v);}

    public T peek() {return storage.getFirst();}

    public T pop() {return storage.removeFirst();}

    public boolean empty() {return storage.isEmpty();}

    @Override
    public String toString() {return storage.toString();}

    public static void main(String[] args) {
        String test = "+d+-+-+e+R---+c+o+r---+k-+,-+e+h+ --+--+l+l--+!+!+o---";
        //作业：使用栈处理以上字符串，如果是"+"号将字母压入栈，而"-"将栈顶元素弹出并打印
        ArrayList<String> redrock = new ArrayList<>();
        Stack<String> stringStack = new Stack<>();
        for (int i = 0 ; i < test.length() ; i++) {
            if('+' == test.charAt(i)) {
                if ('-' ==test.charAt(i+1)) {
                    continue;
                } else {
                    stringStack.push(test.charAt(i+1)+"");
                }
            } else if ('-'==test.charAt(i)) {
                if ('+' == test.charAt(i-1))
                {
                    continue;
                }else {
                    redrock.add(stringStack.pop());
                }
            } else {
                continue;
            }
        }
        System.out.println(redrock);
    }
}
