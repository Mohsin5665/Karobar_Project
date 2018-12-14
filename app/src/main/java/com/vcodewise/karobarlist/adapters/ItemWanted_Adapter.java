package com.vcodewise.karobarlist.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.fragment.SearchFragment;
import com.vcodewise.karobarlist.models.CategoryItem;

import java.util.List;

public class ItemWanted_Adapter extends RecyclerView.Adapter<ItemWanted_Adapter.ViewHolder> {
    private List<CategoryItem> list;
    SearchFragment searchFragment;

    public ItemWanted_Adapter(List<CategoryItem> list, SearchFragment fragment) {
        this.list = list;
        this.searchFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wanted,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cname.setText(list.get(position).getNoofItems());
        holder.fbname.setText(list.get(position).getTitle());
        Glide.with(searchFragment).load(list.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  ImageView imageView;
        public  TextView cname,fbname;
        public ViewHolder(View itemView) {

            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.wantedimageview);
            cname = (TextView)itemView.findViewById(R.id.noofitems);
            fbname = (TextView)itemView.findViewById(R.id.categorytitle);
        }
    }
}
