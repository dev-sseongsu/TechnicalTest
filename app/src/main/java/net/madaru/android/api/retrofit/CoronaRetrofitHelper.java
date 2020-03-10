package net.madaru.android.api.retrofit;

import androidx.annotation.NonNull;

import net.madaru.android.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final class CoronaRetrofitHelper {

    private static final String BASE_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/";
    private static final String KAKAO_REST_API_KEY = "KakaoAK 0e2dce46c2845d1236c9cd3ef9087e12";

    private final Retrofit retrofit;

    private CoronaRetrofitHelper() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
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

    static Retrofit getRetrofit() {
        return SingletonHolder.INSTANCE.retrofit;
    }

    private static class SingletonHolder {
        private static final CoronaRetrofitHelper INSTANCE = new CoronaRetrofitHelper();
    }

}
