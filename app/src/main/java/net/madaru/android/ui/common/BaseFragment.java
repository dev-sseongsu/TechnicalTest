package net.madaru.android.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.madaru.android.BuildConfig;

import java.util.Collections;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseFragment extends Fragment implements ViewModelOwner, OnBackPressedListener {

    @LayoutRes
    protected abstract int getLayoutId();

    public String getTagName() {
        return getClass().getSimpleName();
    }

    public String getBackStackName() {
        return getTagName();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onAttach");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onCreate");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onCreateView");
        }
        int layoutId = getLayoutId();
        if (layoutId == 0) {
            return null;
        }

        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onViewCreated");
        }
        initView(view, savedInstanceState);
    }

    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onActivityCreated");
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onViewStateRestored");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onStart");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onResume");
        }
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onInflate");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onSaveInstanceState");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onPause");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onStop");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onDestroyView");
        }
        releaseView();
    }

    protected void releaseView() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onDestroy");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onDetach");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onHiddenChanged: " + hidden);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (BuildConfig.DEBUG) {
            Log.v(getClass().getSimpleName(), "onRequestPermissionsResult");
        }
    }

    public boolean onBackPressed() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return false;
        }
        Fragment fragment = fragments.get(fragments.size() - 1);
        return fragment instanceof OnBackPressedListener && ((OnBackPressedListener) fragment).onBackPressed();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }

        Fragment fragment = fragments.get(fragments.size() - 1);
        if (fragment instanceof OnActivityResultListener) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    @Nullable
    final public <T extends ViewModel> T getViewModel(Class<T> modelClass) {
        BaseActivity activity = getBaseActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        T viewModel = newViewModel(modelClass);
        if (viewModel != null) {
            return viewModel;
        }
        BaseFragment parentFragment = (BaseFragment)getParentFragment();
        if (parentFragment != null) {
            return parentFragment.getViewModel(modelClass);
        }
        return activity.getViewModel(modelClass);
    }

    @NonNull
    protected List<? extends Class> getViewModelClasses() {
        return Collections.emptyList();
    }

    @Nullable
    protected <T extends ViewModel> T newViewModel(Class<T> modelClass) {
        if (getViewModelClasses().contains(modelClass)) {
            return ViewModelProviders.of(this).get(modelClass);
        }
        return null;
    }

    @Nullable
    public final BaseActivity getBaseActivity() {
        if (!(getActivity() instanceof BaseActivity)) {
            if (BuildConfig.DEBUG) {
                throw new RuntimeException(getActivity() + " doesn't extends BaseActivity");
            }
            return null;
        }
        return (BaseActivity) getActivity();
    }

    @Nullable
    public final BaseFragment getBaseParentFragment() {
        return (BaseFragment)getParentFragment();
    }

    public final void finishActivity() {
        finishActivity(null);
    }

    public final void finishActivity(@Nullable Bundle bundle) {
        if (getParentFragment() instanceof BaseFragment) {
            beforeFinishActivity(bundle);
            ((BaseFragment) getParentFragment()).finishActivity(bundle);
        } else {
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }

    protected void beforeFinishActivity(@Nullable Bundle bundle) {
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

    public final void popBackStack() {
        popBackStack(null, 0);
    }

    public final void popBackStack(@Nullable String name, int flags) {
        final BaseFragment parentFragment = getBaseParentFragment();
        if (parentFragment == null) {
            if (getFragmentManager() == null) {
                return;
            }
            try {
                if (!getFragmentManager().isStateSaved()) {
                    //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
                    getFragmentManager().popBackStackImmediate(name, flags);
                } else {
                    getFragmentManager().popBackStack();
                }
            } catch (IllegalStateException e) {
                //FragmentManager is already executing transactions
            }
        } else {
            try {
                if (!parentFragment.getChildFragmentManager().isStateSaved()){
                    //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
                    parentFragment.getChildFragmentManager().popBackStackImmediate(name, flags);
                } else {
                    parentFragment.getChildFragmentManager().popBackStack();
                }
            } catch (IllegalStateException e) {
                //FragmentManager is already executing transactions
            }
        }
    }
}

