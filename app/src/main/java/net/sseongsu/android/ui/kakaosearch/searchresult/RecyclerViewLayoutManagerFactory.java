package net.sseongsu.android.ui.kakaosearch.searchresult;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

final class RecyclerViewLayoutManagerFactory {

    static RecyclerView.LayoutManager get(@NonNull Context context) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        return new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
    }
}
