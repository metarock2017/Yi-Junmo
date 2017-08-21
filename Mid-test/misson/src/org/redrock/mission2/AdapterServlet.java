package org.redrock.mission2;


import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.deploy.net.HttpResponse;
import org.redrock.mission2.util.CurlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet(name = "Adapter" , value = "/adapter")
public class AdapterServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String params = sb.toString();
        String url = "http://172.20.2.52:84/userpostservice.asmx?op=GetUserPost";
        //String res = CurlUtil.getContent(url, params,"POST");
        System.out.println(params);

    }

    public static void main(String[] args) throws UnirestException {
        HttpResponse <String>response = Unirest.post("http://172.20.2.52:84/GetUserPostTypes")
                .header("content-type", "text/xml; charset=utf-8")
                .header("cache-control", "no-cache")
                .body("\n" +
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                        "  <soap:Body>\n" +
                        "    <GetUserPost xmlns=\"http://172.20.2.52:84/\">\n" +
                        "      <postId>string</postId>\n" +
                        "    </GetUserPost>\n" +
                        "  </soap:Body>\n" +
                        "</soap:Envelope>")
                .asString();
                System.out.println(response);

    }


}
