package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyClass {
    public static final int TIME_OUT = 8000;
    public static void main(String[] args){
        System.out.println("Hello World");

        String aa = getAccessToken();
        System.out.println(aa);
    }

    public static String getAccessToken(){
        System.out.println("start");
        String address = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx54e3c3b6848350b9&secret=2d0224d2c54e8c50ac0b003fbcf93148";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            System.out.println("before connection");
            InputStream in = connection.getInputStream();
            //将得到的输入流装换成String字符串
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, "utf-8"));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
