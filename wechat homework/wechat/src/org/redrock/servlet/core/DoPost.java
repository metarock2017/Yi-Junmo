package org.redrock.servlet.core;

import org.redrock.Jedis.Controller;
import org.redrock.util.AuthProcess;
import org.redrock.util.Const;
import org.redrock.util.Support;
import org.redrock.util.aes.AesException;
import org.redrock.util.aes.WXBizMsgCrypt;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class DoPost {
    public static void DoPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            //解析xml
            Map<String, String[]> params = request.getParameterMap();
            String encodingAesKey = Const.EncodingAESKey;
            String token = Const.Token;
            String appId = Const.AppId;
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String msgSignature = request.getParameter("msg_signature");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            request.getInputStream(), "UTF-8"
                    )
            );
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String encryptMsg = builder.toString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder1 = factory.newDocumentBuilder();
            Document document = builder1.parse(
                    new InputSource(
                            new StringReader(encryptMsg)
                    )
            );
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getChildNodes();
            Map<String, String> result = new HashMap<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (!node.getNodeName().equals("#text")) {
                    String nName = node.getNodeName();
                    String nValue = node.getTextContent();
                    result.put(nName, nValue);
                }
            }
            //检查参数是否加密
            String encrypt_type =request.getParameter("encrypt_type");

            if (encrypt_type!=null && !encrypt_type.equals("raw") && encrypt_type.equals("aes") ) {
                //加密情况
                String toUserName = result.get("ToUserName");
                String encrypt = result.get("Encrypt");
                String format = "<xml><ToUserName><![CDATA[%s]]></ToUserName><Encrypt><![CDATA[%s]]></Encrypt></xml>";
                String fromXML = String.format(format, toUserName, encrypt);
                WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
                String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
                String result3 = result2.replace("\n", "");
                //重新解析xml
                DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder2 = factory1.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(result3));
                Document document1 = builder2.parse(is);
                Element rootNode = document1.getDocumentElement();
                NodeList items = rootNode.getChildNodes();
                Map<String, String> nMap = new HashMap<>();
                for (int i = 0; i < items.getLength(); i++) {
                    Node item = items.item(i);
                    String iName = item.getNodeName();
                    String value = item.getTextContent();
                    nMap.put(iName, value);
                }
                String Content = nMap.get("Content");
                if (Content.equals("1")) {
                    String res = Support.SentRequest(nMap,"hello");
                    //加密发送
                    String res1 = AuthProcess.encryptMsg(request, res);
                    response.getWriter().println(res1);
                } else {
                    //不是1的时候要做的
                }
            } else {
                //未加密情况
                String Content = result.get("Content");
                String MsgType = result.get("MsgType");
                String Event = result.get("Event");
                String EventKey = result.get("EventKey");
                if (Content!=null) {
                    if(Content.matches("[0-9]+")) {
                        int content = Integer.parseInt(Content);
                        if (content == 1) {
                            String res = Support.SentRequest(result, "hello");
                            response.getWriter().println(res);
                        }else if (content == 6) {
                            String res = Support.SentRequest(result, "游戏惩罚:\n" +
                                    "1.背一位异性绕场一周\n" +
                                    "2.唱青藏高原最后一句\n" +
                                    "3.做一个大家都满意的鬼脸\n" +
                                    "4.抱一位异性直到下一轮真心话大冒险结束\n" +
                                    "5.像一位异性表白3分钟\n" +
                                    "6.与一位异性十指相扣，对视10秒\n" +
                                    "7.邀请一位异性为你唱情歌，或邀请一位异性与你情歌对唱\n" +
                                    "8.做自己最性感、最妩媚的表情或动作\n" +
                                    "9.吃下每个人为你夹得菜（如果是辣椒……）\n" +
                                    "10.跳草裙舞、脱衣舞（反正是冬天）\n" +
                                    "11.蹲在凳子上作便秘状\n" +
                                    "12.亲***（这个人可以事先指定），或者亲一位异性，部位不限\n" +
                                    "13.神情的吻墙10秒\n" +
                                    "14. 跪地求婚状：如果我不向你求婚，我会后悔一辈子，因为你是我的惟一。（理察·基尔致朱丽叶·罗伯茨（在电影《逃跑的新娘》中））\n" +
                                    "15. 大喊 燃烧吧，小宇宙～\n" +
                                    "参考 : <a href="+"\"https://zhidao.baidu.com/question/1366831288517867099.html\""+">惩罚提示</a>" );
                            response.getWriter().println(res);
                        } else if((content < 6 && content > 1 ) || (content > 6 && content < 1000)) {
                            String res = Support.SentRequest(result, "游戏规则:\n"+
                                    "游戏有卧底和平民2种身份。\n" +
                                    "游戏根据在场人数大部分玩家拿到同一词语，其余玩家拿到与之相关的另一词语。\n" +
                                    "每人每轮用一句话描述自己拿到的词语，既不能让卧底察觉，也要给同伴以暗示。\n" +
                                    "每轮描述完毕，所有在场的人投票选出怀疑谁是卧底，得票最多的人出局。\n" +
                                    "若卧底全部出局，则游戏结束。若卧底未全部出局，游戏继续。并反复此流程。\n" +
                                    "若卧底撑到最后一轮（剩余总人数小于卧底初始人数的二倍时），则卧底获胜，反之，则平民胜利。");
                            response.getWriter().println(res);
                        }else {
                            if (content > 1000) {
                                String FromUserName = result.get("FromUserName");
                                String res = Controller.JoinRoom(FromUserName,content);
//                                    String res = Support.SentRequest(result, "祝您游戏愉快!");
                                res = Support.SentRequest(result,res);
                                response.getWriter().println(res);
                            } else {
                                String res = Support.SentRequest(result, "房间号码有误!");
                                response.getWriter().println(res);
                            }
                        }
                    } else if(Content.equals("换")){
                            //TODO:换词
                        String res = Support.SentRequest(result, "更换失败!");
                        response.getWriter().println(res);
                    } else {
                        String res = Support.SentRequest(result, "Emmmmm GG  你进入了神奇的空间!(其实里面什么都没有)\r\n\r\n 快输入房间号加入房间或者点击创建房间来开始一场紧张而刺激的游戏吧!");
                        response.getWriter().println(res);
                    }
                } else if (MsgType.equals("event")){
                    if (Event.equals("CLICK")){
                        if (EventKey.equals("V1001_GOOD")) {
                            String res = Support.SentRequest(result, "Thank you!");
                            response.getWriter().println(res);
                        } else if (EventKey.equals("V1001_Start")) {
                            String url = "\"http://momonet.s3.natapp.cc/creat?id=";
                            String FromUserName = result.get("FromUserName");//TODO:混淆下
                            String res = Support.SentRequest(result, "请点击下面的链接来创建房间:\n •  <a href="+url+FromUserName+"\""+">点我创建/更新房间</a>");
                            response.getWriter().println(res);
                        } else if (EventKey.equals("V1001_Join")) {
                            String res = Support.SentRequest(result, "请在文本框直接输入法官获取的房间号");
                            response.getWriter().println(res);
                        } else if (EventKey.equals("V1001_Exit")) {
                            String res = Support.SentRequest(result, "房间创建时间开始30分钟后自动退出 不需要直接操作\n 期间可加入其他房间");
                            response.getWriter().println(res);
                        }
                    } else if(Event.equals("subscribe")) {
                        String res = Support.SentRequest(result, "感谢订阅~~~");
                        response.getWriter().println(res);
                    }
                }else {
                    String res = Support.SentRequest(result,"QAQ Sorry, 我暂时还不能识别这些复杂的指令!");
                    response.getWriter().println(res);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (AesException e) {
            e.printStackTrace();
        }
    }
}
