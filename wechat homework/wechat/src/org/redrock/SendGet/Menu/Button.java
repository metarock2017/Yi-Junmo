package org.redrock.SendGet.Menu;

import org.redrock.SendGet.*;
import org.redrock.util.CurlUtil;
import org.redrock.util.Support;
import org.redrock.util.StringUtil;
//import lombok.Getter;
//import lombok.Setter;
//import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jx on 2017/7/22.
 */
public class Button {
//    button	    是	             一级菜单数组，个数应为1~3个
//    sub_button	否	             二级菜单数组，个数应为1~5个
//    type	        是	             菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
//    name      	是	             菜单标题，不超过16个字节，子菜单不超过60个字节
//    key	click等点击类型必须	     菜单KEY值，用于消息接口推送，不超过128字节
//    url	view miniprogram类型必须  网页链接，用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。
//    media_id	media_id类型和view_limited类型必须	  调用新增永久素材接口返回的合法media_id
//    appid      miniprogram类型必须	 小程序的appid（仅认证公众号可配置）
//    pagepath   miniprogram类型必须	 小程序的页面路径


    public String name;
    public String type;
    public String key;
    public String url;
    public String mediaId;
    public String appId;
    public String pagepath;
    public List<Button> subButton;
    public int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<org.redrock.SendGet.Menu.Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<org.redrock.SendGet.Menu.Button> subButton) {
        this.subButton = subButton;
    }



    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        if (!StringUtil.isBlank(name)) jsonObject.put("name", name);
        if (!StringUtil.isBlank(type)) jsonObject.put("type", type);
        if (!StringUtil.isBlank(key)) jsonObject.put("key", key);
        if (!StringUtil.isBlank(url)) jsonObject.put("url", url);
        if (!StringUtil.isBlank(mediaId)) jsonObject.put("media_id", mediaId);
        if (!StringUtil.isBlank(appId)) jsonObject.put("appid", appId);
        if (!StringUtil.isBlank(pagepath)) jsonObject.put("pagepath", pagepath);
        if (subButton != null && subButton.size() > 0) jsonObject.put("sub_button", getSubButtonJSONArray());
        return jsonObject;
    }

    private JSONArray getSubButtonJSONArray() {
        JSONArray array = null;
        if (subButton != null && subButton.size() > 0) {
            array = new JSONArray();
            for (Button button : subButton) {
                array.put(button.toJson());
            }
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        Button b1 = new Button();
        b1.setType("scancode_waitmsg");
        b1.setName("扫码带提示");
        b1.setKey("rselfmenu_0_0");
        Button b2 = new Button();
        b2.setType("scancode_push");
        b2.setName("扫码推事件");
        b2.setKey("rselfmenu_0_1");
        Button m1 = new Button();
        m1.setName("扫码");
        List<Button> m1SubButs = new ArrayList<>();
        m1SubButs.add(b1);
        m1SubButs.add(b2);
        m1.setSubButton(m1SubButs);
        JSONObject object = m1.toJson();
        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        array.put(object);
        data.put("button", array);
//        Object data = JSON.toJSON(m1);
        System.out.println(data);
        CurlUtil Url = new CurlUtil();
        String  token =  Url.getContent("http://localhost:8080/eGlhb21vbW8=.jsp",null,"GET");
        String accessToken = token;
        String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        URL url = new URL(path);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        connection.getOutputStream(), "UTF-8"
                )
        );
        writer.write(data.toString());
        writer.flush();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream(), "UTF-8"
                )
        );
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        writer.close();
        System.out.println(builder.toString());
    }

    public void addButton(Button button) {
        if (subButton == null) {
            subButton = new ArrayList<>();
        }
        subButton.add(button);
    }
}
