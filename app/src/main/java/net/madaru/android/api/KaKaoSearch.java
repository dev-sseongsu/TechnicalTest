package net.madaru.android.api;

import android.text.TextUtils;

import net.madaru.android.ui.kakaosearch.model.ImageSearchResult;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.lifecycle.LiveData;

public interface KaKaoSearch {

    @UiThread
    LiveData<ImageSearchResult> searchImage(@NonNull String query);

    @UiThread
    LiveData<ImageSearchResult> searchImage(@NonNull Request request);

    class Request {
        @Nullable
        String query;
        @Nullable
        SortType sortType;
        @IntRange(from = 1, to = 50)
        int pageNum;
        @IntRange(from = 1, to = 80)
        int documentSize;

        @Nullable
        public String getQuery() {
            return query;
        }

        @NonNull
        public SortType getSortType() {
            if (sortType == null) {
                return SortType.accuracy;
            }
            return sortType;
        }

        @IntRange(from = 1, to = 50)
        public int getPageNum() {
            return pageNum == 0 ? 1 : pageNum;
        }

        @IntRange(from = 1, to = 80)
        public int getDocumentSize() {
            return documentSize == 0 ? 80 : pageNum;
        }

        private Request(Builder builder) {
            if (TextUtils.isEmpty(builder.query)) {
                throw new RuntimeException("There is no query");
            }
            query = builder.query;
            sortType = builder.sortType;
            pageNum = builder.pageNum;
            documentSize = builder.documentSize;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static final class Builder {
            @Nullable
            private String query;
            @Nullable
            private SortType sortType;
            @IntRange(from = 1, to = 50)
            private int pageNum;
            @IntRange(from = 1, to = 80)
            private int documentSize;

            private Builder() {
            }

            public Builder setQuery(@NonNull String query) {
                this.query = query;
                return this;
            }

            public Builder setSortType(@NonNull SortType sortType) {
                this.sortType = sortType;
                return this;
            }

            public Builder setPageNum(@IntRange(from = 0, to = 50) int pageNum) {
                this.pageNum = pageNum;
                return this;
            }

            public Builder setDocumentSize(@IntRange(from = 0, to = 80) int documentSize) {
                this.documentSize = documentSize;
                return this;
            }

            public Request build() {
                return new Request(this);
            }
        }
    }

    enum SortType {
        accuracy,
        recency
    }
}
