package net.sseongsu.android.ui.kakaosearch;

import android.os.Bundle;

import net.sseongsu.android.R;
import net.sseongsu.android.ui.common.BaseFragment;
import net.sseongsu.android.ui.kakaosearch.search.SearchFragment;

import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import timber.log.Timber;

public class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kakao_main;
    }

    public MainFragment() {
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
