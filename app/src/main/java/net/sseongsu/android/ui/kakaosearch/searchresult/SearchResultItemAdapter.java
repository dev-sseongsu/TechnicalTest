package net.sseongsu.android.ui.kakaosearch.searchresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sseongsu.android.R;
import net.sseongsu.android.ui.kakaosearch.model.ImageDocument;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

class SearchResultItemAdapter extends RecyclerView.Adapter<SearchResultItemAdapter.ItemViewHolder> {

    @NonNull
    private Context context;
    @Nullable
    private SearchResultItemClickListener searchResultItemClickListener;

    SearchResultItemAdapter(@NonNull Context context) {
        this.context = context;
    }

    @Nullable
    private List<ImageDocument> items;

    void setItemClickListener(@Nullable SearchResultItemClickListener searchResultItemClickListener) {
        this.searchResultItemClickListener = searchResultItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        itemView.setOnClickListener(v -> {
            if (searchResultItemClickListener != null) {
                Integer position = (Integer) v.getTag();
                ImageDocument imageDocument = items == null ? null : items.get(position);
                if (imageDocument != null) {
                    searchResultItemClickListener.onItemClick(imageDocument);
                }
            }
        });
        return new ItemViewHolder(itemView);
    }

    public void setItems(List<ImageDocument> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (items != null) {
            ImageDocument document = items.get(position);
            if (document != null) {
                holder.itemView.setTag(position);
                if (holder.displaySiteName != null) {
                    holder.displaySiteName.setText(document.getDisplaySiteName());
                }
                if (holder.thumbnail != null) {
                    Glide.with(context)
                            .load(document.getThumbnailUrl())
                            .into(holder.thumbnail);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        private TextView displaySiteName;
        @Nullable
        private ImageView thumbnail;

        ItemViewHolder(@NonNull View v) {
            super(v);
            displaySiteName = v.findViewById(R.id.tv_display_site_name);
            thumbnail = v.findViewById(R.id.image_view_thumbnail);
        }
    }

    public interface SearchResultItemClickListener {
        void onItemClick(@NonNull ImageDocument imageDocument);
    }
}
