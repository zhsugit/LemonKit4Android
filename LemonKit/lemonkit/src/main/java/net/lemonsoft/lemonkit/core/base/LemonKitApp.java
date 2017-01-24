package net.lemonsoft.lemonkit.core.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * LemonKit - 应用程序
 * Created by LiuRi on 2016/12/28.
 */

public class LemonKitApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LemonKit.instance().init(this);
        Fresco.initialize(this);
    }
}
