package swl.bwie.com.myapplication1127;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import swl.bwie.com.myapplication1127.Bean.Bean;
import swl.bwie.com.myapplication1127.NetUtils.NetUtil;

public class MainActivity extends AppCompatActivity {

    private String lujing = "http://www.zhaoapi.cn/product/getProductDetail?pid=1";
    private Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源ID
        banner = findViewById(R.id.banner);
        //设置banner的样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Bean.SellerBean news = (Bean.SellerBean)path;
                ImageLoader.getInstance().displayImage(news.getIcon(),imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        initData();
    }
    private void initData() {
        NetUtil.yibu(lujing, Bean.class, new NetUtil.CallBack<Bean>() {
            @Override
            public void getdata(Bean o) {
                //设置图片集合
                banner.setImages(o.getData());
                //设置标题
                banner.setBannerTitles(initdots(o));
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
        });
    }

    private List<String> initdots(Bean o) {
        List<String> list=new ArrayList<>();
        for(Bean.SellerBean cc:o.getSeller()){
            list.add(cc.getName());
        }
        return list;
    }
}
