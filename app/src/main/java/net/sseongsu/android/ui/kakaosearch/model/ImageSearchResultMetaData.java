package net.sseongsu.android.ui.kakaosearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public final class ImageSearchResultMetaData implements Parcelable {

    @Nullable
    private Boolean isEnd;
    @Nullable
    private Integer pageableCount;
    @Nullable
    private Integer totalCount;

    private ImageSearchResultMetaData(Builder builder) {
        isEnd = builder.isEnd;
        pageableCount = builder.pageableCount;
        totalCount = builder.totalCount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Boolean isEnd;
        private Integer pageableCount;
        private Integer totalCount;

        private Builder() {
        }

        public Builder setIsEnd(Boolean isEnd) {
            this.isEnd = isEnd;
            return this;
        }

        public Builder setPageableCount(Integer pageableCount) {
            this.pageableCount = pageableCount;
            return this;
        }

        public Builder setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public ImageSearchResultMetaData build() {
            return new ImageSearchResultMetaData(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.isEnd);
        dest.writeValue(this.pageableCount);
        dest.writeValue(this.totalCount);
    }

    protected ImageSearchResultMetaData(Parcel in) {
        this.isEnd = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.pageableCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ImageSearchResultMetaData> CREATOR = new Parcelable.Creator<ImageSearchResultMetaData>() {
        @Override
        public ImageSearchResultMetaData createFromParcel(Parcel source) {
            return new ImageSearchResultMetaData(source);
        }

        @Override
        public ImageSearchResultMetaData[] newArray(int size) {
            return new ImageSearchResultMetaData[size];
        }
    };
}
