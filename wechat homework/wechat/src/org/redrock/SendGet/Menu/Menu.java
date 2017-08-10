package org.redrock.SendGet.Menu;

//import lombok.Getter;
//import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.redrock.util.CurlUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Menu {

    public static void main(String[] args) {
        Menu menu = new Menu();

        Button button1 = new Button();
        button1.setType("click");
        button1.setName("谁是卧底");

        Button button2 = new Button();
        button2.setType("click");
        button2.setName("创建房间开始游戏");
        button2.setKey("V1001_Start");

        Button button6 = new Button();
        button6.setType("click");
        button6.setName("加入已有房间");
        button6.setKey("V1001_Join");

        Button button7 = new Button();
        button7.setType("click");
        button7.setName("退出房间");
        button7.setKey("V1001_Exit");

        Button button3 = new Button();
        button3.setName("菜单");

        Button button4 = new Button();
        button4.setType("view");
        button4.setName("搜索怎么玩");
        button4.setUrl("https://www.zhihu.com/question/22647241");

        Button button5 = new Button();
        button5.setType("click");
        button5.setName("赞我们一下");
        button5.setKey("V1001_GOOD");


        button1.addButton(button2);
        button1.addButton(button6);
        button1.addButton(button7);
        button3.addButton(button4);
        button3.addButton(button5);
        menu.addButton(button1);
        menu.addButton(button3);

        String result = updateMenu(menu);
        System.out.println(result);
    }

    public static String updateMenu(Menu menu) {
        CurlUtil url = new CurlUtil();
        String  token =  url.getContent("http://localhost:8080/123.jsp",null,"GET");
        String accessToken = token;
        System.out.println(accessToken);
        String url1 = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        String menuData = menu.toJson();
        System.out.println(menuData);
        return CurlUtil.postData(url1, menuData);
    }

    private List<Button> button;
    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }


    public String toJson() {
        JSONArray buttonData = new JSONArray();
        JSONObject menuData = new JSONObject();
        for (int i = 0; i < button.size(); i++) {
            Button b = button.get(i);
            buttonData.put(i, b.toJson());
        }
        menuData.put("button", buttonData);
        return menuData.toString();
    }

    public void addButton(Button button) {
        if (this.button == null) {
            this.button = new ArrayList<>();
        }
        this.button.add(button);
    }
}

