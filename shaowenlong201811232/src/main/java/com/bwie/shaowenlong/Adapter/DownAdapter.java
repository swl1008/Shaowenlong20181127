package com.bwie.shaowenlong.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwie.shaowenlong.Fragment.FindFragment;
import com.bwie.shaowenlong.Fragment.HomeFragment;
import com.bwie.shaowenlong.Fragment.NotFragment;

public class DownAdapter extends FragmentPagerAdapter {
    private String[] menus = new String[]{
            "首页","找人","未登录"
    };
    public DownAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();
            case 1:
                return new FindFragment();
            case 2:
                return new NotFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return menus[position];
    }

    @Override
    public int getCount() {
        return menus.length;
    }
}
