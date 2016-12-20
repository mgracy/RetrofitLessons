package com.mgx.retrofitlesson1.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by glmgracy on 16/12/20.
 */

public class HttpUtil {

    public static final int TIMEOUT = 8000;

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    //创建一个url对象
                    URL url = new URL(address);
                    //通过url对象获取HttpURLConnection实例
                    connection = (HttpURLConnection) url.openConnection();
                    //设置Http的请求所使用的方法为GET方法
                    connection.setRequestMethod("GET");
                    //设置连接超时，读取超时的毫秒数
                    connection.setConnectTimeout(TIMEOUT);
                    connection.setReadTimeout(TIMEOUT);
                    //获取服务器返回的输入
                    InputStream in = connection.getInputStream();
                    //将得到的输入流装换成String字符串
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    LogUtil.i("HTTPUtil(Thread Id is " + Thread.currentThread().getId(),
                            "------------------>" + response.toString());
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e){

                }finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
