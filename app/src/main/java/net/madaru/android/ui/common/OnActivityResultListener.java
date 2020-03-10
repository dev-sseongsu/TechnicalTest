package net.madaru.android.ui.common;

import android.content.Intent;

import androidx.annotation.Nullable;

public interface OnActivityResultListener {
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
