package net.sseongsu.android.api.retrofit;

import net.sseongsu.android.BuildConfig;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHelper {

    private static final String BASE_URL = "https://dapi.kakao.com/v2/";
    private static final String KAKAO_REST_API_KEY = "KakaoAK 0e2dce46c2845d1236c9cd3ef9087e12";

    private final Retrofit retrofit;

    private RetrofitHelper() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new retrofit2.Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Authorization", KAKAO_REST_API_KEY);
            return chain.proceed(requestBuilder.build());
        }
    }

    public static Retrofit getRetrofit() {
        return SingletonHolder.INSTANCE.retrofit;
    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

}
