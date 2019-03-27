package com.nhatdo.whatisthis;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.TopicHolder;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class AppBarLayoutRecyclerViewDataAdapter extends RecyclerView.Adapter<TopicHolder> {

    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCs;

    public AppBarLayoutRecyclerViewDataAdapter(ArrayList<Topics> listTopics) {
        this.listTopics = listTopics;
    }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        // Inflate the RecyclerView item layout xml.
        View topicItemView = layoutInflater.inflate(R.layout.activity_app_bar_layout_recycler_view_item, viewGroup, false);
        // Create and return our custom Car Recycler View Item Holder object.
        TopicHolder ret = new TopicHolder(topicItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder topicHolder, int position) {
        if (listTopics != null) {
            // Get topic item dto in list.
            Topics topicItem = listTopics.get(position);
            if (topicItem != null) {
                // Set topic item title.
                topicHolder.getTopicName().setText(topicItem.getName());
                // Set topic image resource id.
                topicHolder.getTopicImage().setImageResource(topicItem.getTopicBackground());
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (listTopics != null) {
            ret = listTopics.size();
        }
        return ret;
    }


}
