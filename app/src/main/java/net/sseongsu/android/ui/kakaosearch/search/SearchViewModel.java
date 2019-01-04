package net.sseongsu.android.ui.kakaosearch.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public final class SearchViewModel extends ViewModel {

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
