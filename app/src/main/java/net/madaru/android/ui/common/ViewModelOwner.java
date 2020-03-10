package net.madaru.android.ui.common;

import androidx.lifecycle.ViewModel;

public interface ViewModelOwner {
    <T extends ViewModel> T getViewModel(Class<T> modelClass);
}
