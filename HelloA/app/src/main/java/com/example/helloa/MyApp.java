package com.example.helloa;

import android.app.Application;

import com.example.helloa.module.AppModule;
//需要导入包
import com.example.helloa.module.DaggerNetComponent;
import com.example.helloa.module.NetComponent;
import com.example.helloa.module.NetModule;

public class MyApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        // mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();------>
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
