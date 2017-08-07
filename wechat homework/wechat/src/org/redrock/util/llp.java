package org.redrock.util;

        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;
        import org.xml.sax.SAXException;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;
        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
public class llp {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("score.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Element element = document.getDocumentElement();
        NodeList list = element.getChildNodes();
        Map< String , String > dic = new HashMap();
        dic.put("课类", "course");
        dic.put("学号","StuNo");
        dic.put("姓名","name");
        dic.put("班级","class");
        dic.put("学期","grade");
        dic.put("课程号","course-number");
        dic.put("课程名","course-name");
        dic.put("考试性质","status");
        dic.put("成绩","score");
        int allscore = 0;
        int allclass = 0;
        for( int i = 0 ; i < list.getLength() ; i++) {
            Node node = list.item(i);
            String name = node.getNodeName();
            if (name.equals("data")) {
                NodeList items = node.getChildNodes();
                List<Map<String, String>> data = new ArrayList<>();
                for (int j = 0; j < items.getLength(); j++) {
                    Node item = items.item(j);
                    NodeList itemList = item.getChildNodes();
                    Map<String, String> attrs = new HashMap<>();
                    for (int k = 0; k < itemList.getLength(); k++) {
                        Node attr = itemList.item(k);
                        String attrName = attr.getNodeName();
                        String attrValue = attr.getTextContent();
                        attrs.put(dic.get(attrName), attrValue);
                        if(attrName.equals("成绩")) {
                            allscore+=Integer.parseInt(attrValue);
                            allclass++;
                        }
                    }
                    //System.out.println(attrs.get("course-name")+":"+attrs.get("score")+"              allscore"+allscore);
                    data.add(attrs);
                }
                int res = allscore / allclass;
                System.out.println("平均分:"+res);
                if(res >= 70 && res <= 100) {
                    System.out.println("llp is a good student");
                }else if(res >= 60) {
                    System.out.println("llp is a qualified student");
                }else if( res > 0 && res < 60) {
                    System.out.println("llp is a bad student");
                }else {
                    System.out.println(("have some error"));
                }
            }
        }
    }
}
