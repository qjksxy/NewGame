package org.slu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShiciUtil {
    public static Map<String, String> shiciMap = new LinkedHashMap<>();

    public static StringBuffer getShiciQue(String[] msgs) {
        String shici = ShiciUtil.getShici(msgs);
        char[] chars = shici.toCharArray();
        char tchar;
        for (int i = 0; i < chars.length; i++) {
            int randomInt = RandomUtil.getRandomInt(chars.length);
            tchar = chars[randomInt];
            chars[randomInt] = chars[i];
            chars[i] = tchar;
        }
        StringBuffer stringBuffer = new StringBuffer(20);
        for (char aChar : chars) {
            stringBuffer.append(aChar);
        }
        return stringBuffer;
    }
    public static String getShiciJson() throws IOException {
        String urlStr = "https://v1.jinrishici.com/all.json";
        URL serverUrl = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //必须设置false，否则会自动redirect到重定向后的地址
        conn.setInstanceFollowRedirects(false);
        conn.connect();
        String result = getReturn(conn);
        return result;
    }

    public static String getShici(String[] msgs) {
        String shiciJson = "";
        String shici = "";
        try {
            shiciJson = getShiciJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = shiciJson.split("\"");
        if(split.length >= 4) {
            shici = split[3];
        } else {
            shici = "咱老北京人儿，每天除了喝，就是玩，没别哒";
        }
        if (shiciMap.containsKey(msgs[0])) {
            shiciMap.remove(msgs[0]);
        }
        shiciMap.put(msgs[0], shici);
        return shici;
    }

    /*请求url获取返回的内容*/
    public static String getReturn(HttpURLConnection connection) throws IOException {
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        }
    }

    public static String getAnswer(String[] msgs) {
        String res;
        if (shiciMap.containsKey(msgs[0])) {
            res = shiciMap.get(msgs[0]);
            shiciMap.remove(msgs[0]);
        } else {
            res = "NULL";
        }
        return res;
    }

    public static final int RIGHT = 1;
    public static final int WRONG = 0;
    public static final int ERROR = 2;
    public static int checkAnswer(String[] msgs) {
        int res;
        if (shiciMap.containsKey(msgs[0])) {
            String shici = shiciMap.get(msgs[0]);
            if (shici.equals(msgs[2])) {
                res = RIGHT;
                shiciMap.remove(msgs[0]);
            } else {
                res = WRONG;
            }
        } else {
            res = ERROR;
        }
        return res;
    }
}
