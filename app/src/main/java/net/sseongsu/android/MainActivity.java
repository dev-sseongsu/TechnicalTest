package net.sseongsu.android;

import android.os.Bundle;

import net.sseongsu.android.ui.common.BaseActivity;
import net.sseongsu.android.ui.kakaosearch.MainFragment;

public final class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_activity_main, MainFragment.newInstance())
                    .commit();
        }

    }
}
