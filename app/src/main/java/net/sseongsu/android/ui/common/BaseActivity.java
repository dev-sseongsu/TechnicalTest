package net.sseongsu.android.ui.common;

import android.os.Bundle;

import java.util.List;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity extends AppCompatActivity implements ViewModelOwner {

    @UiThread
    @LayoutRes
    protected int getLayoutId() {
        return 0;
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (hasFragmentBackStackEntry(fragmentManager)) {
            return;
        }

        final List<Fragment> fragmentList = fragmentManager.getFragments();
        for (Fragment fragment : fragmentList) {
            if (fragment instanceof OnBackPressedListener) {
                if (((OnBackPressedListener) fragment).onBackPressed()) {
                    return;
                }
            }
        }

        super.onBackPressed();
    }

    private boolean hasFragmentBackStackEntry(FragmentManager fragmentManager) {
        final int count = fragmentManager.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(count - 1);
            Fragment fragment = fragmentManager.findFragmentByTag(backStackEntryAt.getName());
            if (fragment instanceof OnBackPressedListener) {
                if (((OnBackPressedListener) fragment).onBackPressed()) {
                    return true;
                }
            }
            if (count == 1) {
                if (fragment != null) {
                    FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                    int childBackStackCount = childFragmentManager.getBackStackEntryCount();
                    if (childBackStackCount > 0) {
                        super.onBackPressed();
                        return true;
                    }
                }
            }
        }

        if (count == 1) {
            finish();
            return true;
        }
        return false;
    }

    @Override
    public final <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return ViewModelProviders.of(this).get(modelClass);
    }

    public final void performFragmentTransaction(@NonNull FragmentTransaction fragmentTransaction,
                                                 @NonNull FragmentManager fragmentManager) {
        try {
            //commit this transaction synchronously
            if (!fragmentManager.isStateSaved()) {
                fragmentTransaction.commitNow();
            } else {
                //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
                fragmentTransaction.commitNowAllowingStateLoss();
            }
        } catch (IllegalStateException e) {
            //FragmentManager is already executing transactions
            //so We should commit this transaction asynchronously
            if (!fragmentManager.isStateSaved()) {
                fragmentTransaction.commit();
            } else {
                //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }
}
