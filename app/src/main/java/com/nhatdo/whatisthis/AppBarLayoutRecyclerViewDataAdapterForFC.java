package com.nhatdo.whatisthis;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhatdo.whatisthis.bean.FlashCardDetailHolder;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class AppBarLayoutRecyclerViewDataAdapterForFC extends RecyclerView.Adapter<FlashCardDetailHolder> {

    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCsWithId;

    public AppBarLayoutRecyclerViewDataAdapterForFC(ArrayList<FlashCardDetails> listFCsWithId) {
        this.listFCsWithId = listFCsWithId;
    }

    @NonNull
    @Override
    public FlashCardDetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        // Inflate the RecyclerView item layout xml.
        View flashCardItemView = layoutInflater.inflate(R.layout.activity_app_bar_layout_recycler_view_item_flash_card, viewGroup, false);
        // Create and return our custom Car Recycler View Item Holder object.
        FlashCardDetailHolder ret = new FlashCardDetailHolder(flashCardItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull FlashCardDetailHolder flashCardDetailHolder, int position) {
        if (listFCsWithId != null) {
            // Get flash card item dto in list.
            FlashCardDetails flashCardItem = listFCsWithId.get(position);
            if (flashCardItem != null) {
                flashCardDetailHolder.getFC_ID().setText(String.valueOf(flashCardItem.getCode()));
                flashCardDetailHolder.getFC_Name().setText(flashCardItem.getName());
                flashCardDetailHolder.getFC_Description().setText(flashCardItem.getDescription());
                flashCardDetailHolder.getFC_Image().setImageResource(flashCardItem.getImageId());
            }
        } else {
            System.out.println("=====>listFCsWithId == null-isEmpty: " + listFCsWithId.isEmpty());
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (listFCsWithId != null) {
            ret = listFCsWithId.size();
        }
        return ret;
    }

}
