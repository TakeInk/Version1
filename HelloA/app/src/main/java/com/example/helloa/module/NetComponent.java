package com.example.helloa.module;

import com.example.helloa.MainActivity;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class,NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
