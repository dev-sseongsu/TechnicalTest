package net.madaru.android;

import android.app.Application;

import com.naver.maps.map.NaverMapSdk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("asy830evo1"));

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                }
            });
        }
    }
}
