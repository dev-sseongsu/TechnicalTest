package net.madaru.android.api.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.madaru.android.ui.kakaosearch.model.ImageDocument;
import net.madaru.android.ui.kakaosearch.model.ImageSearchResult;
import net.madaru.android.ui.kakaosearch.model.ImageSearchResultMetaData;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImageSearchResponse implements ImageSearchResult {

    @SerializedName("documents")
    @Expose
    public List<Document> documents;
    @SerializedName("meta")
    @Expose
    public Meta meta;

    @NonNull
    private List<ImageDocument> imageDocumentList = new LinkedList<>();
    @Nullable
    private ImageSearchResultMetaData imageSearchResultMetaData;

    @NonNull
    @Override
    public List<ImageDocument> getDocuments() {
        if (imageDocumentList.size() == documents.size()) {
            return imageDocumentList;
        }

        for (Document document : documents) {
            ImageDocument imageDocument = ImageDocument.newBuilder()
                    .setCollection(document.collection)
                    .setDatetime(document.datetime)
                    .setDisplaySiteName(document.displaySitename)
                    .setDocUrl(document.docUrl)
                    .setHeight(document.height)
                    .setImageUrl(document.imageUrl)
                    .setThumbnailUrl(document.thumbnailUrl)
                    .setWidth(document.width)
                    .build();
            imageDocumentList.add(imageDocument);
        }

        return imageDocumentList;
    }

    @NonNull
    @Override
    public ImageSearchResultMetaData getMetaData() {
        if (imageSearchResultMetaData == null) {
            imageSearchResultMetaData = ImageSearchResultMetaData.newBuilder()
                    .setIsEnd(meta.isEnd)
                    .setPageableCount(meta.pageableCount)
                    .setTotalCount(meta.totalCount)
                    .build();
        }
        return imageSearchResultMetaData;
    }

    public class Document {

        @SerializedName("collection")
        @Expose
        public String collection;
        @SerializedName("datetime")
        @Expose
        public String datetime;
        @SerializedName("display_sitename")
        @Expose
        public String displaySitename;
        @SerializedName("doc_url")
        @Expose
        public String docUrl;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("image_url")
        @Expose
        public String imageUrl;
        @SerializedName("thumbnail_url")
        @Expose
        public String thumbnailUrl;
        @SerializedName("width")
        @Expose
        public Integer width;

    }

    public class Meta {

        @SerializedName("is_end")
        @Expose
        public Boolean isEnd;
        @SerializedName("pageable_count")
        @Expose
        public Integer pageableCount;
        @SerializedName("total_count")
        @Expose
        public Integer totalCount;

    }
}
