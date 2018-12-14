package com.vcodewise.karobarlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.models.ItemListing;

import java.util.ArrayList;
import java.util.List;

public class ItemListing_Adapter extends RecyclerView.Adapter<ItemListing_Adapter.ViewHolder> {

    private List<ItemListing> listings= new ArrayList<>();
    Context context;

    public ItemListing_Adapter(List<ItemListing> listings, Context context) {
        this.listings = listings;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.categoryname.setText(listings.get(position).getCategoryName());
        holder.address.setText(listings.get(position).getAddress());
        holder.noofreview.setText(listings.get(position).getNoofReviews());
        holder.ratingBar.setRating(listings.get(position).getRating());
        Glide.with(context).load(listings.get(position).getImageURL());

    }

    @Override
    public int getItemCount() {
        return listings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryname,address,noofreview;
        private RatingBar ratingBar;
        private ImageView imageView;


        public ViewHolder(View itemView) {

            super(itemView);
            categoryname = (TextView) itemView.findViewById(R.id.categories);
            noofreview = (TextView) itemView.findViewById(R.id.noofreview);
            address = (TextView) itemView.findViewById(R.id.address);
            imageView = (ImageView) itemView.findViewById(R.id.listimageview);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }
}
