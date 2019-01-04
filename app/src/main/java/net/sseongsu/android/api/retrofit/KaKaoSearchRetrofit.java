package net.sseongsu.android.api.retrofit;

import android.content.Context;

import net.sseongsu.android.api.KaKaoSearch;
import net.sseongsu.android.ui.kakaosearch.model.ImageSearchResult;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public final class KaKaoSearchRetrofit implements KaKaoSearch {

    @NonNull
    private Context context;

    @NonNull
    private KaKaoSearchApi kaKaoSearchApi;

    @NonNull
    private static MutableLiveData<ImageSearchResult> imageSearchResultLiveData = new MutableLiveData<>();

    public KaKaoSearchRetrofit(@NonNull Context context) {
        this.context = context;
        kaKaoSearchApi = RetrofitHelper.getRetrofit().create(KaKaoSearchApi.class);
    }

    @Override
    public LiveData<ImageSearchResult> searchImage(@NonNull String query) {
        kaKaoSearchApi.searchImage(query)
                .enqueue(new Callback<ImageSearchResponse>() {
                    @Override
                    public void onResponse(Call<ImageSearchResponse> call, final Response<ImageSearchResponse> response) {
                        imageSearchResultLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ImageSearchResponse> call, Throwable t) {
                        imageSearchResultLiveData.setValue(null);
                    }
                });
        return imageSearchResultLiveData;
    }
}
