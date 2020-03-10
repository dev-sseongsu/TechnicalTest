package net.madaru.android.ui.kakaosearch;

import android.os.Bundle;

import net.madaru.android.R;
import net.madaru.android.ui.common.BaseFragment;
import net.madaru.android.ui.kakaosearch.search.SearchFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public final class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kakao_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            if (getFragmentManager() != null) {
                SearchFragment searchFragment = SearchFragment.newInstance();
                FragmentTransaction searchFragmentFragmentTransaction = getChildFragmentManager().beginTransaction()
                        .replace(R.id.container_main, searchFragment, searchFragment.getTagName())
                        .addToBackStack(searchFragment.getBackStackName());
                performFragmentTransaction(searchFragmentFragmentTransaction, getFragmentManager());
            }
        }

    }

    @Override
    public boolean onBackPressed() {
        if(super.onBackPressed()) {
            return true;
        }

        finishActivity();
        return true;
    }
}
