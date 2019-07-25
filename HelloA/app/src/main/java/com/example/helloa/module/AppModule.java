package com.example.helloa.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

//provide Application' context
@Module
public class AppModule {
    Application mApplication;

    public  AppModule(Application application) {
        mApplication = application;
    }
    private AppModule() {

    }
    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
