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

    public ShowsListAdapter(List<Show> showItems) {
        this.showItems = showItems;
    }

    @Override
    public ShowsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowsListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_show_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShowsListViewHolder holder, int position) {
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

    public static class ShowsListViewHolder extends RecyclerView.ViewHolder {
        TextView tvShowTitle;
        ImageView imgShowCover;

        public ShowsListViewHolder(View itemView) {
            super(itemView);
            tvShowTitle = itemView.findViewById(R.id.tvShowTitle);
            imgShowCover = itemView.findViewById(R.id.imgShowCover);
        }
    }
}
