package com.nhatdo.whatisthis.bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatdo.whatisthis.R;

public class FlashCardDetailHolder extends RecyclerView.ViewHolder {

    private TextView FC_ID = null;
    private TextView FC_Name = null;
    private TextView FC_Description = null;
    private ImageView FC_Image = null;

    public FlashCardDetailHolder(@NonNull View itemView) {
        super(itemView);
        if (itemView != null) {
            FC_ID = itemView.findViewById(R.id.txtId);
            FC_Name = itemView.findViewById(R.id.appbarlayout_view_item_fc_title);
            FC_Description = itemView.findViewById(R.id.appbarlayout_view_item_fc_description);
            FC_Image = itemView.findViewById(R.id.appbarlayout_view_item_fc_image);
        }
    }

    public TextView getFC_Name() {
        return FC_Name;
    }

    public TextView getFC_Description() {
        return FC_Description;
    }

    public ImageView getFC_Image() {
        return FC_Image;
    }

    public TextView getFC_ID() {
        return FC_ID;
    }
}
