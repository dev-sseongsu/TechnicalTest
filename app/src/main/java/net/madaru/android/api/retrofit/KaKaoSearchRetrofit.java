package net.madaru.android.api.retrofit;

import android.content.Context;

import net.madaru.android.api.KaKaoSearch;
import net.madaru.android.ui.kakaosearch.model.ImageSearchResult;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class KaKaoSearchRetrofit implements KaKaoSearch {

    @NonNull
    private Context context;

    @NonNull
    private KaKaoSearchApi kaKaoSearchApi;

    @NonNull
    private static MutableLiveData<ImageSearchResult> imageSearchResultLiveData = new MutableLiveData<>();

    public KaKaoSearchRetrofit(@NonNull Context context) {
        this.context = context;
        kaKaoSearchApi = KaKaoRetrofitHelper.getRetrofit().create(KaKaoSearchApi.class);
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

    @Override
    public LiveData<ImageSearchResult> searchImage(@NonNull Request request) {
        if (request.getQuery() == null) {
            imageSearchResultLiveData.setValue(null);
            return imageSearchResultLiveData;
        }

        kaKaoSearchApi.searchImage(request.getQuery(),
                request.getSortType().name(),
                request.getPageNum(),
                request.getDocumentSize())
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
