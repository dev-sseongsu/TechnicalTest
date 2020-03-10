package net.madaru.android.ui.kakaosearch.search;

import android.util.ArrayMap;

import net.madaru.android.ui.kakaosearch.model.ImageSearchResult;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

public final class SearchViewModel extends ViewModel {

    @Nullable
    private String searchQuery;

    private ArrayMap<String, ImageSearchResult> searchResultArrayMap = new ArrayMap<>();

    public void setSearchQuery(@NonNull String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Nullable
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setImageSearchResult(@NonNull String searchQuery,
                                     @NonNull ImageSearchResult imageSearchResult) {
        searchResultArrayMap.clear();
        searchResultArrayMap.put(searchQuery, imageSearchResult);
    }

    @Nullable
    public ImageSearchResult getImageSearchResult() {
        if (searchQuery == null) {
            return null;
        }

        return searchResultArrayMap.get(searchQuery);
    }
}
