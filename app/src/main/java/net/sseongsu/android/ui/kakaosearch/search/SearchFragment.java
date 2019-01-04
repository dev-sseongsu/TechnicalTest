package net.sseongsu.android.ui.kakaosearch.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import net.sseongsu.android.R;
import net.sseongsu.android.ui.common.BaseFragment;
import net.sseongsu.android.ui.kakaosearch.searchresult.SearchResultFragment;
import net.sseongsu.android.ui.utils.KeyboardUtils;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class SearchFragment extends BaseFragment {

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    private TextInputEditText editText;

    @Nullable
    private SearchViewModel viewModel;

    public SearchFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kakao_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(SearchViewModel.class);
    }

    @NonNull
    @Override
    protected List<? extends Class> getViewModelClasses() {
        return Collections.singletonList(SearchViewModel.class);
    }

    @Override
    protected void initView(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        editText = view.findViewById(R.id.edit_test_query);
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 0) {
                        return;
                    }

                    if (viewModel != null) {
                        viewModel.setSearchQuery(s);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            if (viewModel != null) {
                editText.setText(viewModel.getSearchQuery());
            }
        }

        final Button button = view.findViewById(R.id.btn_search);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(editText.getText())) {
                        return;
                    }

                    if (getContext() == null) {
                        return;
                    }

                    KeyboardUtils.hide(getContext(), editText);
                    search(editText.getText().toString());
                }
            });
        }
    }

    private void search(@NonNull String query) {
        final SearchResultFragment searchResultFragment =
                (SearchResultFragment) getChildFragmentManager().findFragmentById(R.id.container_kakao_search_result);
        if (searchResultFragment != null) {
            searchResultFragment.search(query);
        }
    }
}
