package net.madaru.android.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Result {

    @Nullable
    private Throwable error;
    @Nullable
    private ApiError apiError;
    @Nullable
    private Object result;

    public Result(@NonNull Object result) {
        this.result = result;
        this.error = null;
        this.apiError = null;
    }

    public Result(@NonNull Throwable error) {
        this.result = null;
        this.error = error;
        this.apiError = null;
    }

    public Result(@NonNull ApiError apiError) {
        this.result = null;
        this.error = null;
        this.apiError = apiError;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Nullable
    public Object getResult() {
        return result;
    }

    @Nullable
    public ApiError getApiError() {
        return apiError;
    }
}
