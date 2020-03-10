package net.madaru.android.ui.kakaosearch.searchresult;

import android.os.Bundle;
import android.view.View;

import net.madaru.android.R;
import net.madaru.android.api.Api;
import net.madaru.android.api.KaKaoSearch;
import net.madaru.android.ui.common.BaseFragment;
import net.madaru.android.ui.kakaosearch.model.ImageSearchResult;
import net.madaru.android.ui.kakaosearch.search.SearchViewModel;
import net.madaru.android.ui.kakaosearch.serachresultdetail.SearchResultDetailFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

public final class SearchResultFragment extends BaseFragment {

    private static final String BUNDLE_KEY_QUERY = "query";

    @Nullable
    private SearchResultItemAdapter searchResultItemAdapter;

    @Nullable
    private SearchViewModel searchViewModel;

    @Nullable
    private RecyclerView recyclerView;
    @Nullable
    private View noDataView, btnTop;
    @Nullable
    private String query;
    @Nullable
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kakao_search_result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchViewModel = getViewModel(SearchViewModel.class);
    }

    @Override
    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        noDataView = view.findViewById(R.id.tv_no_data);
        recyclerView = view.findViewById(R.id.recycler_view);
        btnTop = view.findViewById(R.id.btn_top);
        btnTop.setOnClickListener(v -> {
            if (layoutManager != null) {
                layoutManager.scrollToPosition(0);
            }
        });
        if (recyclerView != null) {
            layoutManager = RecyclerViewLayoutManagerFactory.get(requireContext());
            recyclerView.setLayoutManager(layoutManager);
            searchResultItemAdapter = new SearchResultItemAdapter(requireContext());
            searchResultItemAdapter.setItemClickListener(imageDocument -> {
                if (getParentFragment() != null && getParentFragment().getFragmentManager() != null) {
                    SearchResultDetailFragment searchResultDetailFragment = SearchResultDetailFragment.newInstance(imageDocument);
                    FragmentTransaction fragmentTransaction = getParentFragment().getFragmentManager().beginTransaction()
                            .replace(R.id.container_main, searchResultDetailFragment, searchResultDetailFragment.getTagName())
                            .addToBackStack(searchResultDetailFragment.getBackStackName());
                    performFragmentTransaction(fragmentTransaction, getParentFragment().getFragmentManager());
                }

            });
            recyclerView.setAdapter(searchResultItemAdapter);
        }

        if (searchViewModel != null && searchViewModel.getImageSearchResult() != null) {
            setRecyclerViewData(searchViewModel.getImageSearchResult());
        }
    }

    private final Observer<ImageSearchResult> searchResultObserver = new Observer<ImageSearchResult>() {
        @Override
        public void onChanged(ImageSearchResult imageSearchResult) {
            if (imageSearchResult == null || imageSearchResult.getDocuments().size() == 0) {
                changeVisibility(false);
                return;
            }

            if (searchViewModel != null) {
                searchViewModel.setImageSearchResult(query, imageSearchResult);
            }

            setRecyclerViewData(imageSearchResult);
        }
    };

    public void search(@NonNull String query) {
        this.query = query;
//        Api.get(requireContext()).getKaKaoSearch().searchImage(query).observe(this, searchResultObserver);
        KaKaoSearch.Request request = KaKaoSearch.Request.newBuilder().setQuery(query).build();
        Api.get(requireContext()).getKaKaoSearch().searchImage(request).observe(this, searchResultObserver);
    }

    private void setRecyclerViewData(@NonNull ImageSearchResult imageSearchResult) {
        if (searchResultItemAdapter != null) {
            changeVisibility(true);
            searchResultItemAdapter.setItems(imageSearchResult.getDocuments());
        }
    }

    private void changeVisibility(boolean showRecyclerView) {
        if (recyclerView == null || noDataView == null || btnTop == null) {
            return;
        }

        if (showRecyclerView) {
            recyclerView.setVisibility(View.VISIBLE);
            btnTop.setVisibility(View.VISIBLE);
            noDataView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            btnTop.setVisibility(View.GONE);
            noDataView.setVisibility(View.VISIBLE);
        }
    }
}
