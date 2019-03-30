package com.nhatdo.whatisthis.bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatdo.whatisthis.R;

public class FlashCardDetailTestHolder extends RecyclerView.ViewHolder {

    private ImageView FC_Image = null;
    private Button FC_Button_Name_one;
    private Button FC_Button_Name_two;

    public FlashCardDetailTestHolder(@NonNull View itemView) {
        super(itemView);
        if (itemView != null) {
            FC_Image = itemView.findViewById(R.id.appbarlayout_view_item_fc_image);
            FC_Button_Name_one = itemView.findViewById(R.id.btnAnswerOne);
            FC_Button_Name_two = itemView.findViewById(R.id.btnAnswerTwo);
        }
    }

    public ImageView getFC_Image() {
        return FC_Image;
    }

    public TextView getFC_Button_Name_one() {
        return FC_Button_Name_one;
    }

    public TextView getFC_Button_Name_two() {
        return FC_Button_Name_two;
    }
}

