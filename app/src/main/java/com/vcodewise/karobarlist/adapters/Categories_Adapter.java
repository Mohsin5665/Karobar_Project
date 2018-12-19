package com.vcodewise.karobarlist.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcodewise.karobarlist.R;

import com.vcodewise.karobarlist.activities.MainActivity;
import com.vcodewise.karobarlist.fragment.ItemListFragment;
import com.vcodewise.karobarlist.fragment.SearchFragment;
import com.vcodewise.karobarlist.models.Categories_list;

import java.util.List;


public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder> {

    private List<Categories_list> listdata;
  SearchFragment searchFragment;
  //Activity activity;
//    Context context;

    public Categories_Adapter(List<Categories_list> list, SearchFragment ctx) {
        this.listdata = list;
        this.searchFragment =  ctx;
     //  this.context = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagories,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {



        holder.name.setText(listdata.get(position).getName());
      // Glide.with(context).load(listdata.get(position).getImageUrl()).into(holder.image);
        Glide.with(searchFragment).load(listdata.get(position).getImageUrl()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity., ItemListFragment.class);
                intent.putExtra("id",listdata.get(position).getId());


            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.category);
            image = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
