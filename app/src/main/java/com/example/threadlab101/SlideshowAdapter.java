package com.example.threadlab101; // Make sure this matches your actual package name

import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SlideshowAdapter extends RecyclerView.Adapter<SlideshowAdapter.SliderViewHolder> {

    private List<Integer> images; // List of drawable resource IDs

    public SlideshowAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a full-size ImageView programmatically
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        // FIT_CENTER ensures the whole logo is seen; CENTER_CROP fills the box
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(40, 40, 40, 40); // Optional padding
        return new SliderViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public SliderViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}

