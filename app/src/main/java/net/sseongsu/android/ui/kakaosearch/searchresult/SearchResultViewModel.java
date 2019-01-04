package net.sseongsu.android.ui.kakaosearch.searchresult;

import net.sseongsu.android.ui.common.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public final class SearchResultViewModel extends BaseViewModel {

    private MutableLiveData<String> searchQueryLiveData = new MutableLiveData<>();

    public void observeSearchQuery(@NonNull LifecycleOwner lifecycleOwner,
                                   @NonNull Observer<String> observer) {
        searchQueryLiveData.observe(lifecycleOwner, observer);
    }

    public void setSearchQuery(@NonNull CharSequence searchQuery) {
        searchQueryLiveData.setValue(searchQuery.toString());
    }

    @Nullable
    public String getSearchQuery() {
        return searchQueryLiveData.getValue();
    }
}
