package com.bwie.shaowenlong.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.shaowenlong.R;

public class LeftMenuAdapter extends BaseAdapter {

    private String[] menus = new String[]{
            "首页","找人","未登录"
    };
    private Context context;

    public LeftMenuAdapter(Context context) {
        this.context = context;
    }
    private final int ITEM_COUNT = 2;
    private final int IMAGE_TYPE = 0;
    private final int TEXT_TYPE = 1;

    @Override
    public int getCount() {
        return menus.length+1;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?IMAGE_TYPE:TEXT_TYPE;
    }

    @Override
    public String getItem(int position) {
        if (position == 0){
            return null;
        }
        return menus[position-1];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(getItemViewType(position)==IMAGE_TYPE? R.layout.fragment_left_image:R.layout.fragment_left_item,parent,false);
            vh = new ViewHolder(convertView);
        }
        if (getItemViewType(position) == TEXT_TYPE){
            vh.bind(getItem(position));
        }
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(View convertView) {
            imageView = convertView.findViewById(R.id.menu_image);
            textView = convertView.findViewById(R.id.menu_text);
            convertView.setTag(this);
        }
        public void bind(String item){
            textView.setText(item);
        }
    }
}
