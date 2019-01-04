package net.sseongsu.android.ui.kakaosearch.searchresult;

import android.os.Bundle;
import android.view.View;

import net.sseongsu.android.R;
import net.sseongsu.android.api.Api;
import net.sseongsu.android.ui.common.BaseFragment;
import net.sseongsu.android.ui.kakaosearch.model.ImageDocument;
import net.sseongsu.android.ui.kakaosearch.model.ImageSearchResult;
import net.sseongsu.android.ui.kakaosearch.search.SearchViewModel;
import net.sseongsu.android.ui.kakaosearch.serachresultdetail.SearchResultDetailFragment;

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
    private ImageSearchResult imageSearchResult;

    @Nullable
    private SearchViewModel searchViewModel;

    @Nullable
    private String query;
    @Nullable
    private RecyclerView recyclerView;
    @Nullable
    private View noDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kakao_search_result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            query = getArguments().getString(BUNDLE_KEY_QUERY);
        }

        searchViewModel = getViewModel(SearchViewModel.class);
    }

    @Override
    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        noDataView = view.findViewById(R.id.tv_no_data);
        recyclerView = view.findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(RecyclerViewLayoutManagerFactory.get(requireContext()));
            searchResultItemAdapter = new SearchResultItemAdapter(requireContext());
            searchResultItemAdapter.setItemClickListener(new SearchResultItemAdapter.SearchResultItemClickListener() {
                @Override
                public void onItemClick(@NonNull ImageDocument imageDocument) {
                    if (getParentFragment() != null && getParentFragment().getFragmentManager() != null) {
                        SearchResultDetailFragment searchResultDetailFragment = SearchResultDetailFragment.newInstance(imageDocument);
                        FragmentTransaction fragmentTransaction = getParentFragment().getFragmentManager().beginTransaction()
                                .replace(R.id.container_main, searchResultDetailFragment, searchResultDetailFragment.getTagName())
                                .addToBackStack(searchResultDetailFragment.getBackStackName());
                        performFragmentTransaction(fragmentTransaction, getParentFragment().getFragmentManager());
                    }

                }
            });
            recyclerView.setAdapter(searchResultItemAdapter);
        }

        if (searchViewModel != null && searchViewModel.getSearchQuery() != null) {
            search(searchViewModel.getSearchQuery());
        }
    }

    public void search(@NonNull String query) {
        Api.get(requireContext()).getKaKaoSearch().searchImage(query).observe(this, new Observer<ImageSearchResult>() {
            @Override
            public void onChanged(ImageSearchResult imageSearchResult) {
                if (imageSearchResult == null) {
                    return;
                }

                SearchResultFragment.this.imageSearchResult = imageSearchResult;

                invalidateRecyclerView(imageSearchResult);
            }
        });
    }

    private void invalidateRecyclerView(ImageSearchResult imageSearchResult) {
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
