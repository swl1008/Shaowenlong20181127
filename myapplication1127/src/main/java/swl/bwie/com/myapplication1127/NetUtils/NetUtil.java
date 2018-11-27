package swl.bwie.com.myapplication1127.NetUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    //接口回调
    public interface CallBack<T>{
        void getdata(T t);
    }
    //异步执行
    public static void yibu(String lujing, final Class clazz, final CallBack callBack){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return NetUtil.jiexi(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callBack.getdata(o);
            }
        }.execute(lujing);
    }
    //网络解析
    public static <T> T jiexi(String lujing,Class clazz){
        T t= (T) new Gson().fromJson(NetUtil.geturl(lujing),clazz);
        return t;
    }
    //网络请求
    public static String geturl(String lujing){
        String result="";
        try {
            URL url=new URL(lujing);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            if (urlConnection.getResponseCode()==200){
                result=NetUtil.zifu(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String zifu(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader buff=new BufferedReader(inputStreamReader);
        StringBuilder builder=new StringBuilder();
        for (String imp=buff.readLine();imp!=null;imp=buff.readLine()){
            builder.append(imp);
        }
        return builder.toString();
    }
}
