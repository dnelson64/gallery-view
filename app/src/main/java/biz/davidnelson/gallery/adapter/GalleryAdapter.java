package biz.davidnelson.gallery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import biz.davidnelson.gallery.R;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<String> mImageList;

    public GalleryAdapter(ArrayList<String> imageList) {
        mImageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.mImageView.getContext())
            .load(mImageList.get(position))
            .centerCrop()
            .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view;
        }

    }
}
