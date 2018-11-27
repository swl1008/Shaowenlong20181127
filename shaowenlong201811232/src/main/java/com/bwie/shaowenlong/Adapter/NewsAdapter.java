package com.bwie.shaowenlong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.shaowenlong.Bean.NewsResponse;
import com.bwie.shaowenlong.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<NewsResponse.DataBean> list;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public NewsResponse.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private final int ITEM_COUNT = 2;

    @Override
    public int getViewTypeCount() {
        return ITEM_COUNT;
    }
    private final int ICON_ONE = 0;
    private final int ICON_THREE = 1;

    @Override
    public int getItemViewType(int position) {
        String s2 = list.get(position).getThumbnail_pic_s02();
        String s3 = list.get(position).getThumbnail_pic_s03();
        if (s2 == null || s3 == null){
            return ICON_ONE;
        }else{
            return ICON_THREE;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (getItemViewType(position) == ICON_ONE){
            OneViewHolder one;
            if (convertView == null){
                convertView = View.inflate(context,R.layout.item_icon_one,null);
                one = new OneViewHolder();
                one.title = convertView.findViewById(R.id.title);
                one.imageView1 = convertView.findViewById(R.id.image1);
                convertView.setTag(one);
            }else{
                one = (OneViewHolder) convertView.getTag();
            }
            one.title.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),one.imageView1);
        }else if(getItemViewType(position) == ICON_THREE){
            ThreeViewHolder three;
            if (convertView == null){
                convertView = View.inflate(context,R.layout.item_icon_three,null);
                three = new ThreeViewHolder();
                three.title = convertView.findViewById(R.id.title);
                three.imageView1 = convertView.findViewById(R.id.image1);
                three.imageView2 = convertView.findViewById(R.id.image2);
                three.imageView3 = convertView.findViewById(R.id.image3);
                convertView.setTag(three);
            }else{
                three = (ThreeViewHolder) convertView.getTag();
            }

            three.title.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),three.imageView1);
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(),three.imageView2);
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s03(),three.imageView3);
        }

        return convertView;
    }

    public void setDatas(List<NewsResponse.DataBean> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    public void addDatas(ArrayList<NewsResponse.DataBean> data) {
        this.list = data;
        notifyDataSetChanged();
    }
    class OneViewHolder{
        TextView title;
        ImageView imageView1;
    }
    class ThreeViewHolder{
        TextView title;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
    }


}
