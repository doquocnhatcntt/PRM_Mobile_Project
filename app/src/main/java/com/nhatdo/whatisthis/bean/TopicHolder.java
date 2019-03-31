package com.nhatdo.whatisthis.bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatdo.whatisthis.R;

public class TopicHolder extends RecyclerView.ViewHolder {

    private TextView topicName = null;
    private TextView topicDescription = null;
    private ImageView topicImage = null;

    public TopicHolder(@NonNull View itemView) {
        super(itemView);
        if (itemView != null) {
            topicName = itemView.findViewById(R.id.appbarlayout_view_item_title);
            topicImage = itemView.findViewById(R.id.appbarlayout_view_item_image);
        }
    }

    public TextView getTopicName() {
        return topicName;
    }

    public TextView getTopicDescription() {
        return topicDescription;
    }

    public ImageView getTopicImage() {
        return topicImage;
    }

}
