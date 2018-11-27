package com.bwie.shaowenlong.Netutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    private static NetUtil instance;
    private Gson gson;
    public NetUtil() {
        gson = new Gson();
    }

    public static NetUtil getInstance() {
        if (instance == null){
            instance = new NetUtil();
        }
        return instance;
    }
    //判断是否有网络
    public boolean isurl(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null&&activeNetworkInfo.isAvailable();
    }
    public interface Callback<T>{
        void onSuccess(T t);
    }
    @SuppressLint("StaticFieldLeak")
    public void getRequest(final String urlStr, final Class clazz, final Callback callback){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return getRequest(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callback.onSuccess(o);
            }
        }.execute(urlStr);
    }
    public <T> T getRequest(String urlStr,Class clazz){
        return (T) gson.fromJson(getRequest(urlStr),clazz);
    }

    public String getRequest(String urlStr){
        String result = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200){
                result = stream2String(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    private String stream2String(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        for (String tmp = br.readLine();tmp != null;tmp = br.readLine()){
            sb.append(tmp);
        }
        return sb.toString();
    }
}
