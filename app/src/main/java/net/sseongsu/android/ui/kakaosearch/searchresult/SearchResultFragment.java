package net.sseongsu.android.ui.kakaosearch.searchresult;

import android.os.Bundle;
import android.view.View;

import net.sseongsu.android.R;
import net.sseongsu.android.api.Api;
import net.sseongsu.android.ui.common.BaseFragment;
import net.sseongsu.android.ui.kakaosearch.model.ImageSearchResult;
import net.sseongsu.android.ui.kakaosearch.search.SearchViewModel;
import net.sseongsu.android.ui.kakaosearch.serachresultdetail.SearchResultDetailFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public final class SearchResultFragment extends BaseFragment {

    private static final String BUNDLE_KEY_QUERY = "query";

    @Nullable
    private SearchResultItemAdapter searchResultItemAdapter;

    @Nullable
    private SearchViewModel searchViewModel;

    @Nullable
    private RecyclerView recyclerView;
    @Nullable
    private View noDataView;
    @Nullable
    private String query;

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
        if (recyclerView != null) {
            recyclerView.setLayoutManager(RecyclerViewLayoutManagerFactory.get(requireContext()));
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
        Api.get(requireContext()).getKaKaoSearch().searchImage(query).observe(this, searchResultObserver);
    }

    private void setRecyclerViewData(@NonNull ImageSearchResult imageSearchResult) {
        if (searchResultItemAdapter != null) {
            changeVisibility(true);
            searchResultItemAdapter.setItems(imageSearchResult.getDocuments());
        }
    }

    private void changeVisibility(boolean showRecyclerView) {
        if (recyclerView == null) {
            return;
        }
        if (noDataView == null) {
            return;
        }

        if (showRecyclerView) {
            recyclerView.setVisibility(View.VISIBLE);
            noDataView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            noDataView.setVisibility(View.VISIBLE);
        }
    }
}
