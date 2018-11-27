package com.bwie.shaowenlong.App;

import android.app.Application;
import android.graphics.Bitmap;

import com.bwie.shaowenlong.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(
                new ImageLoaderConfiguration.Builder(this)
                        .diskCacheSize(50*1024*1024)
                        .memoryCacheSizePercentage(13)
                        .defaultDisplayImageOptions(
                                new DisplayImageOptions.Builder()
                                        .cacheOnDisk(true)
                                        .cacheInMemory(true)
                                        .bitmapConfig(Bitmap.Config.RGB_565)
                                        .showImageOnLoading(R.mipmap.ic_launcher)
                                        .showImageOnFail(R.mipmap.ic_launcher)
                                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                                        .build()
                        )
                        .build()
        );
    }
}
