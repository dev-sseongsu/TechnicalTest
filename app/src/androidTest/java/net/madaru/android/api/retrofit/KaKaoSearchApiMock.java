package net.madaru.android.api.retrofit;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class KaKaoSearchApiMock implements KaKaoSearchApi {

    private final BehaviorDelegate<KaKaoSearchApi> delegate;
    private final Gson gson;

    KaKaoSearchApiMock(BehaviorDelegate<KaKaoSearchApi> delegate) {
        this.delegate = delegate;
        gson = new Gson();
    }

    @Override
    public Call<ImageSearchResponse> searchImage(@NonNull String query) {
        ImageSearchResponse imageSearchResponse = gson.fromJson(KaoKaoSearchApiTest.RESPONSE, ImageSearchResponse.class);
        return delegate.returningResponse(imageSearchResponse).searchImage(query);
    }

    @Override
    public Call<ImageSearchResponse> searchImage(@NonNull String query, @Nullable String sort, @Nullable int page, @Nullable int size) {
        ImageSearchResponse imageSearchResponse = gson.fromJson(KaoKaoSearchApiTest.RESPONSE, ImageSearchResponse.class);
        return delegate.returningResponse(imageSearchResponse).searchImage(query, sort, page, size);
    }
}
