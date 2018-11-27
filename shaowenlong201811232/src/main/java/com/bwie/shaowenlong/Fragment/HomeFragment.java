package com.bwie.shaowenlong.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.shaowenlong.Adapter.NewsAdapter;
import com.bwie.shaowenlong.Bean.NewsResponse;
import com.bwie.shaowenlong.Netutils.NetUtil;
import com.bwie.shaowenlong.R;
import com.bwie.shaowenlong.Sqlite.Dao;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private PullToRefreshListView plist;
    private int page;
    private String path = "http://www.xieast.com/api/news/news.php?page=";
    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        page = 1;
        plist = view.findViewById(R.id.pulllist);
        adapter = new NewsAdapter(getActivity());
        plist.setAdapter(adapter);
        initPull();
        initData();
        return view;
    }

    public void initPull() {
        plist.setMode(PullToRefreshBase.Mode.BOTH);
        plist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }

        });

    }

    public void initData() {
        NetUtil.getInstance().getRequest(path + page, NewsResponse.class, new NetUtil.Callback<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse newsResponse) {

                if (newsResponse == null) {
                    Toast.makeText(getActivity(), "请求错误", Toast.LENGTH_SHORT).show();
                    ArrayList<NewsResponse.DataBean> query = Dao.getInstance(getActivity()).query();
                    adapter.addDatas(query);

                }
                else {
                    List<NewsResponse.DataBean> data = newsResponse.getData();
                    adapter.setDatas(data);


                    Dao.getInstance(getActivity()).add(data);

                }

                page++;
                
                plist.onRefreshComplete();

            }
        });

    }
}
