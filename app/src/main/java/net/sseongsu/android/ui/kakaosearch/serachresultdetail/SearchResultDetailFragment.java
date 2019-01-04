package net.sseongsu.android.ui.kakaosearch.serachresultdetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sseongsu.android.R;
import net.sseongsu.android.ui.common.BaseFragment;
import net.sseongsu.android.ui.kakaosearch.model.ImageDocument;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class SearchResultDetailFragment extends BaseFragment {

    private static final String BUNDLE_KEY_IMAGE_DOCUMENT = "image_document";

    public static SearchResultDetailFragment newInstance(@NonNull ImageDocument imageDocument) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_KEY_IMAGE_DOCUMENT, imageDocument);
        SearchResultDetailFragment fragment = new SearchResultDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    private ImageDocument imageDocument;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kakao_search_result_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null) {
            finishActivity();
            return;
        }

        imageDocument = getArguments().getParcelable(BUNDLE_KEY_IMAGE_DOCUMENT);
    }

    @Override
    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (imageDocument == null) {
            return;
        }
        final ImageView documentImageView = view.findViewById(R.id.image);
        if (documentImageView != null) {
            Glide.with(requireActivity())
                    .load(imageDocument.getImageUrl())
                    .into(documentImageView);
        }
        final TextView tvCollection = view.findViewById(R.id.tv_collection);
        if (tvCollection != null) {
            tvCollection.setText(imageDocument.getCollection());
        }
        final TextView tvDateTime = view.findViewById(R.id.tv_datetime);
        if (tvDateTime != null) {
            tvDateTime.setText(imageDocument.getDatetime());
        }
        final TextView tvDisplaySiteName = view.findViewById(R.id.tv_display_site_name);
        if (tvDisplaySiteName != null) {
            tvDisplaySiteName.setText(imageDocument.getDisplaySiteName());
        }
        final TextView tvDocUrl = view.findViewById(R.id.tv_doc_url);
        if (tvDocUrl != null) {
            tvDocUrl.setText(imageDocument.getDocUrl());
        }
    }

    @Override
    public boolean onBackPressed() {
        popBackStack();
        return true;
    }
}
