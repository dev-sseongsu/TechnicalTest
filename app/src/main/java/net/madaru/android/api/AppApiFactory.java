package net.madaru.android.api;

import android.content.Context;

import net.madaru.android.api.retrofit.KaKaoSearchRetrofit;

import androidx.annotation.NonNull;

class AppApiFactory implements ApiFactory {

    @NonNull
    private Context context;

    AppApiFactory(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public KaKaoSearch createKaKaoImageSearchApi() {
        return new KaKaoSearchRetrofit(context);
    }
}
