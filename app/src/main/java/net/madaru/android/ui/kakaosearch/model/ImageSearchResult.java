package net.madaru.android.ui.kakaosearch.model;

import java.util.List;

import androidx.annotation.NonNull;

public interface ImageSearchResult {

    @NonNull
    List<ImageDocument> getDocuments();
    @NonNull
    ImageSearchResultMetaData getMetaData();
}
