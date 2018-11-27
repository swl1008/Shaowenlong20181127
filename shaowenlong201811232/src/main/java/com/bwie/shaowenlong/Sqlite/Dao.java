package com.bwie.shaowenlong.Sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.shaowenlong.Bean.NewsResponse;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private static Dao instance;
    private SQLiteDatabase database;
    private Dao(Context context){
        SqliteHelper helper = new SqliteHelper(context);
        database = helper.getReadableDatabase();
    }

    public static Dao getInstance(Context context) {
        if (instance == null){
            instance = new Dao(context);
        }
        return instance;
    }

    public ArrayList<NewsResponse.DataBean> query(){
        Cursor cursor = database.rawQuery("select * from news", null);
        ArrayList<NewsResponse.DataBean> nd = new ArrayList<>();
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            NewsResponse.DataBean dataBean =  new NewsResponse.DataBean(title);
            nd.add(dataBean);
        }
        return nd;
    }
    public void add(List<NewsResponse.DataBean> data){
        for (int i = 0;i<data.size();i++){
            String title = data.get(i).getTitle();
            database.execSQL("insert into news(title) values(?)",new String[]{title});
        }
    }
}
