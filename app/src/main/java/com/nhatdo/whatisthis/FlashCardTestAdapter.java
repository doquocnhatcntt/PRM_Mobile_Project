package com.nhatdo.whatisthis;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhatdo.whatisthis.bean.FlashCardDetailTestHolder;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;
import java.util.Random;

public class FlashCardTestAdapter extends RecyclerView.Adapter<FlashCardDetailTestHolder> {

    ArrayList<FlashCardDetails> listFCsWithId;
    UtilPlayAudio utilPlayAudio;
    private Context mContext;

    public FlashCardTestAdapter(ArrayList<FlashCardDetails> listFCsWithId, Context context) {
        this.listFCsWithId = listFCsWithId;
        this.mContext = context;
    }

    @NonNull
    @Override
    public FlashCardDetailTestHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        // Inflate the RecyclerView item layout xml.
        View flashCardItemView = layoutInflater.inflate(R.layout.activity_flash_card_test_recycler_view_item, viewGroup, false);
        // Create and return our custom Car Recycler View Item Holder object.
        FlashCardDetailTestHolder flashCardDetailTestHolder = new FlashCardDetailTestHolder(flashCardItemView);
        return flashCardDetailTestHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FlashCardDetailTestHolder flashCardDetailTestHolder, final int position) {
        if (listFCsWithId != null) {
            //Create a random between 0 to (size - 1)
            Random random = new Random();
            int max = listFCsWithId.size() - 1;
            int min = 0;
            int randomPosition = random.nextInt((max - min) + 1) + min;
            int randomButton = random.nextInt(2);
            System.out.println("=====> randomButton: " + randomButton);

            //Check if the position = ramdomNumer... It's mean 2 button have same name
            if (position == randomPosition) {
                System.out.println("=====>position == randomPosition<=====");
                randomPosition = random.nextInt((max - min) + 1) + min;
            }
            // Get flash card item dto in list.
            FlashCardDetails flashCardItem = listFCsWithId.get(position);
            System.out.println("=====>position : " + position);
            //Position start at 0 to (size - 1)
            FlashCardDetails otherFlashCardItem = listFCsWithId.get(randomPosition);
            System.out.println("=====>randomPosition : " + randomPosition);

            if (flashCardItem != null) {
                if (randomButton == 1) {
                    //For Choice button no 1
                    flashCardDetailTestHolder.getFC_Button_Name_one().setText(flashCardItem.getName());
                    flashCardDetailTestHolder.getFC_Button_Name_one().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            flashCardDetailTestHolder.getFC_Button_Name_one().getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                            //flashCardDetailTestHolder.getFC_Button_Name_two().setVisibility(View.GONE);
                            flashCardDetailTestHolder.getFC_Button_Name_two().setEnabled(false);
                        }
                    });

                    //For Choice button no 2
                    flashCardDetailTestHolder.getFC_Button_Name_two().setText(otherFlashCardItem.getName());
                    flashCardDetailTestHolder.getFC_Button_Name_two().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            flashCardDetailTestHolder.getFC_Button_Name_two().getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                            //flashCardDetailTestHolder.getFC_Button_Name_one().setVisibility(View.GONE);
                            flashCardDetailTestHolder.getFC_Button_Name_one().setEnabled(false);
                        }
                    });
                } else {
                    //For Choice button no 2
                    flashCardDetailTestHolder.getFC_Button_Name_two().setText(flashCardItem.getName());
                    flashCardDetailTestHolder.getFC_Button_Name_two().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            flashCardDetailTestHolder.getFC_Button_Name_two().getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                            //flashCardDetailTestHolder.getFC_Button_Name_one().setVisibility(View.GONE);
                            flashCardDetailTestHolder.getFC_Button_Name_one().setEnabled(false);
                        }
                    });

                    //For Choice button no 1
                    flashCardDetailTestHolder.getFC_Button_Name_one().setText(otherFlashCardItem.getName());
                    flashCardDetailTestHolder.getFC_Button_Name_one().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            flashCardDetailTestHolder.getFC_Button_Name_one().getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                            //flashCardDetailTestHolder.getFC_Button_Name_two().setVisibility(View.GONE);
                            flashCardDetailTestHolder.getFC_Button_Name_two().setEnabled(false);
                        }
                    });
                }

                flashCardDetailTestHolder.getFC_Image().setImageResource(flashCardItem.getImageId());
                flashCardDetailTestHolder.getFC_Image().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("=====>Test: Before playAudioForTest Function is called<=====");
                        System.out.println("=====>Test: mContext.toString: " + mContext.toString());
                        utilPlayAudio.playAudio(position, listFCsWithId, mContext);
                    }
                });
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