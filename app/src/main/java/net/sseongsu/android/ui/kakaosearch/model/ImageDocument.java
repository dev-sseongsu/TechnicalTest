package net.sseongsu.android.ui.kakaosearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ImageDocument implements Parcelable {

    @Nullable
    private String collection;
    @Nullable
    private String datetime;
    @Nullable
    private String displaySiteName;
    @Nullable
    private String docUrl;
    @Nullable
    private Integer height;
    @Nullable
    private String imageUrl;
    @Nullable
    private String thumbnailUrl;
    @Nullable
    private Integer width;

    @Nullable
    public String getCollection() {
        return collection;
    }

    @Nullable
    public String getDatetime() {
        return datetime;
    }

    @Nullable
    public String getDisplaySiteName() {
        return displaySiteName;
    }

    @Nullable
    public String getDocUrl() {
        return docUrl;
    }

    @Nullable
    public Integer getHeight() {
        return height;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @Nullable
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Nullable
    public Integer getWidth() {
        return width;
    }

    private ImageDocument(Builder builder) {
        collection = builder.collection;
        datetime = builder.datetime;
        displaySiteName = builder.displaySiteName;
        docUrl = builder.docUrl;
        height = builder.height;
        imageUrl = builder.imageUrl;
        thumbnailUrl = builder.thumbnailUrl;
        width = builder.width;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        @Nullable
        private String collection;
        @Nullable
        private String datetime;
        @Nullable
        private String displaySiteName;
        @Nullable
        private String docUrl;
        @Nullable
        private Integer height;
        @Nullable
        private String imageUrl;
        @Nullable
        private String thumbnailUrl;
        @Nullable
        private Integer width;

        private Builder() {
        }

        public Builder setCollection(@NonNull String collection) {
            this.collection = collection;
            return this;
        }

        public Builder setDatetime(@NonNull String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder setDisplaySiteName(@NonNull String displaySiteName) {
            this.displaySiteName = displaySiteName;
            return this;
        }

        public Builder setDocUrl(@NonNull String docUrl) {
            this.docUrl = docUrl;
            return this;
        }

        public Builder setHeight(@NonNull Integer height) {
            this.height = height;
            return this;
        }

        public Builder setImageUrl(@NonNull String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setThumbnailUrl(@NonNull String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder setWidth(@NonNull Integer width) {
            this.width = width;
            return this;
        }

        public ImageDocument build() {
            return new ImageDocument(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.collection);
        dest.writeString(this.datetime);
        dest.writeString(this.displaySiteName);
        dest.writeString(this.docUrl);
        dest.writeValue(this.height);
        dest.writeString(this.imageUrl);
        dest.writeString(this.thumbnailUrl);
        dest.writeValue(this.width);
    }

    protected ImageDocument(Parcel in) {
        this.collection = in.readString();
        this.datetime = in.readString();
        this.displaySiteName = in.readString();
        this.docUrl = in.readString();
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imageUrl = in.readString();
        this.thumbnailUrl = in.readString();
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ImageDocument> CREATOR = new Parcelable.Creator<ImageDocument>() {
        @Override
        public ImageDocument createFromParcel(Parcel source) {
            return new ImageDocument(source);
        }

        @Override
        public ImageDocument[] newArray(int size) {
            return new ImageDocument[size];
        }
    };
}
