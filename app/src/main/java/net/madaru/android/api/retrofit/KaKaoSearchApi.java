package net.madaru.android.api.retrofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KaKaoSearchApi {

    @GET("/v2/search/image")
    Call<ImageSearchResponse> searchImage(@NonNull @Query("query") String query);

    @GET("/v2/search/image")
    Call<ImageSearchResponse> searchImage(@NonNull @Query("query") String query,
                                          @Nullable @Query("sort") String sort,
                                          @Nullable @Query("page") int page,
                                          @Nullable @Query("size") int size);


}
