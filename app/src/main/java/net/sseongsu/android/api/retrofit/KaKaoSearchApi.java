package net.sseongsu.android.api.retrofit;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KaKaoSearchApi {

    @GET("/v2/search/image")
    Call<ImageSearchResponse> searchImage(@NonNull @Query("query") String search);

}
