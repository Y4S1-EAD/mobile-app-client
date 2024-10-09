package com.example.mobile_app_client.reviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_app_client.R;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> reviewList;

    public ReviewAdapter(List<Review> reviews) {
        this.reviewList = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.vendorName.setText(review.getVendorName());
        holder.ratingValue.setText(String.valueOf(review.getRatingValue()));
        holder.comment.setText(review.getComment());
        holder.datePosted.setText(review.getDatePosted());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vendorName, ratingValue, comment, datePosted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vendorName = itemView.findViewById(R.id.vendorName);
            ratingValue = itemView.findViewById(R.id.ratingValue);
            comment = itemView.findViewById(R.id.comment);
            datePosted = itemView.findViewById(R.id.datePosted);
        }
    }
}
