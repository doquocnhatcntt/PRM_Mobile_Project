package com.nhatdo.whatisthis;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.nhatdo.whatisthis.SqlHelper.Config;
import com.nhatdo.whatisthis.SqlHelper.MyDatabaseHelper;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class TopicLists extends AppCompatActivity {

    String topicItemTitle;
    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCs;
    ArrayList<FlashCardDetails> listFCsWithId;
    RecyclerView topicRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_lists);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar)findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);

        Bundle bd = getIntent().getBundleExtra("data");
        listTopics = (ArrayList<Topics>) bd.getSerializable("listTopics");
        listFCs = (ArrayList<FlashCardDetails>) bd.getSerializable("listFCs");

        createTopicListUseRecyclerView();

    }

    // This method shows how to customize SimpleAdapter to show image and text in ListView.
    private void createTopicListUseRecyclerView()
    {
        final MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        // Create the recyclerview.
        topicRecyclerView = (RecyclerView)findViewById(R.id.appbarlayout_recycler_view);

        //Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        topicRecyclerView.setHasFixedSize(true);

        //Create the linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TopicLists.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        topicRecyclerView.setLayoutManager(linearLayoutManager);

        // Create car recycler view data adapter with car item list.
        AppBarLayoutRecyclerViewDataAdapter topicDataAdapter = new AppBarLayoutRecyclerViewDataAdapter(listTopics);
        // Set data adapter.
        topicRecyclerView.setAdapter(topicDataAdapter);

        topicRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, topicRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                int positionPlus = position + 1;
                topicItemTitle = listTopics.get(position).getName();
                listFCsWithId = (ArrayList<FlashCardDetails>) myDatabaseHelper.getFCNodeWithIDTopic(positionPlus);
                System.out.println("=====> listFCsWithId.size(): " + listFCsWithId.size());

                //Check for each card with short click
                //Toast.makeText(getApplicationContext(), "This is Add On Item Touch Listener with position = " + position, Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("topicItemTitle", topicItemTitle);
                bundle.putSerializable("listTopics", listTopics);
                bundle.putSerializable("listFCsWithId", listFCsWithId);
                Intent i = new Intent(TopicLists.this, FlashCardDetailLists.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                int positionPlus = position + 1;
                topicItemTitle = listTopics.get(position).getName();
                listFCsWithId = (ArrayList<FlashCardDetails>) myDatabaseHelper.getFCNodeWithIDTopic(positionPlus);

                //Check for each card with short click
                //Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("topicItemTitle", topicItemTitle);
                bundle.putSerializable("listTopics", listTopics);
                bundle.putSerializable("listFCsWithId", listFCsWithId);
                Intent i = new Intent(TopicLists.this, FlashCardTest.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        }));
    }

}
