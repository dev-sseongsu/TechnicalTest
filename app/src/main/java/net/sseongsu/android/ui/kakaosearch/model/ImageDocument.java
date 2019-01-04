package net.sseongsu.android.ui.kakaosearch.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class ImageDocument implements Parcelable {

    private String collection;
    private String datetime;
    private String displaySiteName;
    private String docUrl;
    private Integer height;
    private String imageUrl;
    private String thumbnailUrl;
    private Integer width;

    public String getCollection() {
        return collection;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getDisplaySiteName() {
        return displaySiteName;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public Integer getHeight() {
        return height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getWidth() {
        return width;
    }

    private ImageDocument(Builder builder) {
        collection = builder.collection;
        datetime = builder.datetime;
        displaySiteName = builder.displaySitename;
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
        private String collection;
        private String datetime;
        private String displaySitename;
        private String docUrl;
        private Integer height;
        private String imageUrl;
        private String thumbnailUrl;
        private Integer width;

        private Builder() {
        }

        public Builder setCollection(String collection) {
            this.collection = collection;
            return this;
        }

        public Builder setDatetime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder setDisplaySitename(String displaySitename) {
            this.displaySitename = displaySitename;
            return this;
        }

        public Builder setDocUrl(String docUrl) {
            this.docUrl = docUrl;
            return this;
        }

        public Builder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder setWidth(Integer width) {
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
