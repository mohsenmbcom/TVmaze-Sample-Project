package com.mohsenmb.tvmazesampleproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.tvmazesampleproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowsListAdapter extends RecyclerView.Adapter<ShowsListAdapter.ShowsListViewHolder> {

    private List<Show> showItems;
    private ShowItemClickListener showItemClickListener;

    public ShowsListAdapter(List<Show> showItems) {
        this.showItems = showItems;
    }

    @Override
    public ShowsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowsListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_show_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ShowsListViewHolder holder, int position) {
        Show show = showItems.get(position);

        Context context = holder.imgShowCover.getContext();
        Picasso.with(context).cancelRequest(holder.imgShowCover);

        if (show.getImage() != null) {
            Picasso
                    .with(context)
                    .load(show.getImage().getMedium())
                    .fit()
                    .placeholder(R.drawable.img_loading_placeholder)
                    .error(R.drawable.img_loading_placeholder)
                    .into(holder.imgShowCover);
        }

        holder.tvShowTitle.setText(show.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showItemClickListener != null) {
                    Show clickedShow = showItems.get(holder.getAdapterPosition());
                    showItemClickListener.onShowItemClicked(view, clickedShow, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (showItems == null) {
            return 0;
        }
        return showItems.size();
    }

    public List<Show> getShowItems() {
        return showItems;
    }

    public void setShowItems(List<Show> showItems) {
        this.showItems = showItems;
    }

    public ShowItemClickListener getShowItemClickListener() {
        return showItemClickListener;
    }

    public void setShowItemClickListener(ShowItemClickListener showItemClickListener) {
        this.showItemClickListener = showItemClickListener;
    }

    public static class ShowsListViewHolder extends RecyclerView.ViewHolder {
        TextView tvShowTitle;
        ImageView imgShowCover;

        public ShowsListViewHolder(View itemView) {
            super(itemView);
            tvShowTitle = itemView.findViewById(R.id.tvShowTitle);
            imgShowCover = itemView.findViewById(R.id.imgShowCover);
        }
    }

    public interface ShowItemClickListener {
        void onShowItemClicked(View view, Show show, int position);
    }
}
