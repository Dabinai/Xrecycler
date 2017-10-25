package app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Dabin on 2017/10/25.
 */

public class MyApp extends Application{
    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance =this;
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(aDefault);
    }

    public static MyApp getInstance(){
        return mInstance;
    }
}
