package com.nhatdo.whatisthis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class FlashCardDetailLists extends AppCompatActivity {

    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCsWithId;
    RecyclerView flashCardRecyclerView;
    String topicItemTitle = "";
    UtilPlayAudio utilPlayAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_detail_lists);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("");

        Bundle bd = getIntent().getBundleExtra("data");
        topicItemTitle = bd.getString("topicItemTitle");
        listTopics = (ArrayList<Topics>) bd.getSerializable("listTopics");
        listFCsWithId = (ArrayList<FlashCardDetails>) bd.getSerializable("listFCsWithId");

        Toolbar toolbarfc = (Toolbar)findViewById(R.id.appbarlayout_tool_bar_fc);
        TextView FcTitle = toolbarfc.findViewById(R.id.txtToolbarTitle);
        FcTitle.setText(topicItemTitle);
        FcTitle.setTextSize(30);
        setSupportActionBar(toolbarfc);

        createFlashCardListUseRecyclerView();
    }

    private void createFlashCardListUseRecyclerView() {
        // Create the recyclerview.
        flashCardRecyclerView = (RecyclerView)findViewById(R.id.appbarlayout_recycler_view_fc);

        //Swipe one item at a time Recyclerview using PagerSnapHelper
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(flashCardRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        flashCardRecyclerView.setHasFixedSize(true);

        // Create the linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FlashCardDetailLists.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        flashCardRecyclerView.setLayoutManager(linearLayoutManager);

        // Create car recycler view data adapter with car item list.
        AppBarLayoutRecyclerViewDataAdapterForFC flashCardDataAdapter = new AppBarLayoutRecyclerViewDataAdapterForFC(listFCsWithId);
        // Set data adapter.
        flashCardRecyclerView.setAdapter(flashCardDataAdapter);

        flashCardRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, flashCardRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "Position FC at = " + position, Toast.LENGTH_LONG).show();
                int positionPlus = position + 1;
                utilPlayAudio.playAudio(position, listFCsWithId, FlashCardDetailLists.this);
            }

            @Override
            public void onLongClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "LONG PRESS Position FC at = " + position, Toast.LENGTH_LONG).show();
                int positionPlus = position + 1;
                utilPlayAudio.playAudioForLong(position, listFCsWithId, FlashCardDetailLists.this);
            }
        }));
    }

}
