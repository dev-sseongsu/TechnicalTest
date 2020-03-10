package net.madaru.android.ui.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public final class KeyboardUtils {

    private KeyboardUtils() {
    }

    public static void hide(@NonNull Context context, @NonNull View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
