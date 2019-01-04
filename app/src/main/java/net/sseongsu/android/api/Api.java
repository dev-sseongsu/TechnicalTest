package net.sseongsu.android.api;

import android.content.Context;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;

public final class Api {

    private static Api INSTANCE;

    private ApiFactory apiFactory;
    private KaKaoSearch kaKaoSearch;

    private Api(@NonNull ApiFactory apiFactory) {
        this.apiFactory = apiFactory;
        kaKaoSearch = apiFactory.createKaKaoImageSearchApi();
    }

    @AnyThread
    public static Api get(@NonNull Context context) {

        if (INSTANCE == null) {
            synchronized (Api.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Api(new AppApiFactory(context.getApplicationContext()));
                }
            }
        }

        return INSTANCE;
    }

    public KaKaoSearch getKaKaoSearch() {
        return kaKaoSearch;
    }
}
