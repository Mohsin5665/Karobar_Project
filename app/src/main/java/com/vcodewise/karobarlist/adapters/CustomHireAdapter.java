package com.vcodewise.karobarlist.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.vcodewise.karobarlist.MyApplication;
import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.models.BusinessItem;

import java.util.List;

public class CustomHireAdapter extends RecyclerView.Adapter<CustomHireAdapter.ViewHolder> {

    private static final String TAG = "CustomHireAdapter";

    private static List<BusinessItem> mDataset;
    private static Activity mActivity;
    private DisplayImageOptions mDisplayImageOptions;

    public CustomHireAdapter(Activity activity, List<BusinessItem> dataset) {
        mActivity = activity;
        mDataset = dataset;

        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView, favView, shareView;
            private TextView txtName, txtAddress, txtNumber, txtNoofReviews;
            private RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.hireimageview);
            favView = (ImageView) view.findViewById(R.id.favview);
            shareView = (ImageView) view.findViewById(R.id.shareview);
            txtName = (TextView) view.findViewById(R.id.name);
            txtAddress = (TextView) view.findViewById(R.id.address);
            txtNumber = (TextView) view.findViewById(R.id.number);
            txtNoofReviews = (TextView) view.findViewById(R.id.noofreview);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public CustomHireAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hire, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        BusinessItem item = mDataset.get(position);

        MyApplication.imageLoader.displayImage(item.getImagesList().get(0), holder.imageView, mDisplayImageOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                //holder.mSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "Input/Output error";
                        break;
                    case DECODING_ERROR:
                        message = "Image can't be decoded";
                        break;
                    case NETWORK_DENIED:
                        message = "Downloads are denied";
                        break;
                    case OUT_OF_MEMORY:
                        message = "Out Of Memory error";
                        break;
                    case UNKNOWN:
                        message = "Unknown error";
                        break;
                }
               // holder.mSpinner.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
               // holder.mSpinner.setVisibility(View.GONE);
            }
        });
        holder.txtName.setText(item.getName() + " (" +item.getCategoryName() + ")");
        holder.txtAddress.setText(item.getAddress());
        holder.txtNumber.setText(item.getNumber());
        holder.ratingBar.setRating(item.getRating());
        holder.txtNoofReviews.setText("(" + item.getNoofRating() + ")");
        holder.shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.favView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void animateTo(List<BusinessItem> categories) {
        applyAndAnimateRemovals(categories);
        applyAndAnimateAdditions(categories);
        applyAndAnimateMovedItems(categories);
    }

    private void applyAndAnimateRemovals(List<BusinessItem> categories) {
        for (int i = mDataset.size() - 1; i >= 0; i--) {
            BusinessItem item = mDataset.get(i);
            if (!categories.contains(item)) {
                deleteItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<BusinessItem> categories) {
        for (int i = 0, count = categories.size(); i < count; i++) {
            BusinessItem item = categories.get(i);
            if (!mDataset.contains(item)) {
                addItem(item, i);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<BusinessItem> categories) {
        for (int toPosition = categories.size() - 1; toPosition >= 0; toPosition--) {
            BusinessItem item = categories.get(toPosition);
            final int fromPosition = mDataset.indexOf(item);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public void addItem(BusinessItem item, int position) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public BusinessItem deleteItem(int position) {
        BusinessItem item = mDataset.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    public void moveItem(int fromposition, int toposition) {
        BusinessItem item = deleteItem(fromposition);
        addItem(item, toposition);
        notifyItemMoved(fromposition, toposition);
    }
}
