package org.redrock.util;

import javax.servlet.http.HttpServletRequest;
import org.redrock.util.aes.*;

import java.util.Map;


public class AuthProcess {
    public final static String Token = "0xFA";//公众平台上面自己填写的Token
    //换了记得改
    public final static String EncodingAESKey = "3Qf4pupVh6EOfzErvLKb9pCa35OMCGcMNjAgq4xPfqM";//公众平台上面自己填写的43位EncodingAESKey
    public final static String AppID = "wx1d34a57d34a06f05";//应用的appid（微信生成的）

    public static String  decryptMsg(HttpServletRequest request,Map<String,String> encryptMap) {
        // 微信加密签名
        //String sVerifyMsgSig = request.getParameter("signature");
        String msgSignature = request.getParameter("msg_signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(Token, EncodingAESKey, AppID);
            System.out.println("2\n");
            return pc.decryptMsg(msgSignature, timestamp, nonce, encryptMap.get("Encrypt"));
        } catch (AesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String  encryptMsg(HttpServletRequest request,String replyXml) {
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(Token, EncodingAESKey, AppID);
            return pc.encryptMsg(replyXml, timestamp, nonce);
        } catch (AesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}