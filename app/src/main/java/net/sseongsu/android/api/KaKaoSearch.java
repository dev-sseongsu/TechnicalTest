package net.sseongsu.android.api;

import net.sseongsu.android.ui.kakaosearch.model.ImageSearchResult;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.lifecycle.LiveData;

public interface KaKaoSearch {

    @UiThread
    LiveData<ImageSearchResult> searchImage(@NonNull String search);
}
