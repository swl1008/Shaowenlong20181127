package com.bwie.shaowenlong;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.bwie.shaowenlong.Adapter.DownAdapter;
import com.bwie.shaowenlong.Fragment.LeftFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.left_drawer,new LeftFragment())
                    .commit();
            initView();
        }
    }
    private void initView(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.contents);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        viewPager.setAdapter(new DownAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.bottom_indicator);
        tabLayout.setupWithViewPager(viewPager);


    }
    public void showPage(int position){
        viewPager.setCurrentItem(position);
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
