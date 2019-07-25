package com.example.helloa.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.solver.Cache;
import dagger.Module;
import javax.inject.Singleton;
import dagger.Provides;


@Module
public class NetModule {
    String mBaseUrl;

    //Constructor needs one parameter to instantiate
    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    //Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    //Application reference must come from AppModule.class
    SharedPreferences providersSharedPreferdences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    /*
    @Provides
    @Singleton
    Cache provedeOkHttpCache(Application application) {
        int cacheSize = 10*1024*1024;//10MB
        Cache cache = new Cache(application.getCacheDir(),cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient();
        client.setCache(cache);
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    */
}
