package org.redrock.mission2.util;

import java.io.*;
import java.net.*;
import java.util.Map;

public class CurlUtil {
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static String getContent(String strUrl, Map<String, String[]> params, String method) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        HttpURLConnection conn = null;
        String paramStr = null;
        String result = null;
        if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry i : params.entrySet()) {
                try {
                    sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paramStr = sb.toString();
            }
            if (method== null || method.equals("") || method.equalsIgnoreCase("GET")) {
                strUrl = strUrl + "?" + paramStr;
            }
        }
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setRequestProperty("User-agent", userAgent);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (!(method == null || method.equals("")) && method.equalsIgnoreCase("POST")) {
                conn.setRequestMethod("POST");
                if (!(paramStr == null || paramStr.equals(""))) {
                    conn.setDoOutput(true);
                    writer = new BufferedWriter(
                            new OutputStreamWriter(
                                    conn.getOutputStream(), "UTF-8"
                            )
                    );
                    writer.write(paramStr);
                    writer.flush();
                }
            }
            reader = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream(), "UTF-8"
                    )
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String postData(String strUrl, String data) {
        String result = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connection.getOutputStream(), "UTF-8"
                    )
            );
            writer.write(data);
            writer.flush();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(), "UTF-8"
                    )
            );
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            writer.close();
            connection.disconnect();
            result = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
