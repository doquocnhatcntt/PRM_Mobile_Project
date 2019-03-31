package com.nhatdo.whatisthis;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.nhatdo.whatisthis.SqlHelper.MyDatabaseHelper;
import com.nhatdo.whatisthis.TutorialDialogFragment.DFTopic;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class TopicListsActivity extends AppCompatActivity {

    String topicItemTitle;
    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCs;
    ArrayList<FlashCardDetails> listFCsWithId;
    RecyclerView topicRecyclerView;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_lists);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);

        Bundle bd = getIntent().getBundleExtra("data");
        listTopics = (ArrayList<Topics>) bd.getSerializable("listTopics");
        listFCs = (ArrayList<FlashCardDetails>) bd.getSerializable("listFCs");

        //Set up a Back Navigator icon onto a ToolBar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("listTopics", listTopics);
                bundle.putSerializable("listFCsWithId", listFCsWithId);
                Intent i = new Intent(TopicListsActivity.this, MainActivity.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        });
        createTopicListUseRecyclerView();
    }

    // This method shows how to customize SimpleAdapter to show image and text in ListView.
    private void createTopicListUseRecyclerView() {
        final MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        // Create the Recycler View.
        topicRecyclerView = (RecyclerView) findViewById(R.id.appbarlayout_recycler_view);
        /*Use this setting to improve performance if you know that changes
          in content do not change the layout size of the RecyclerView*/
        topicRecyclerView.setHasFixedSize(true);
        //Create the linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TopicListsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //Set layout manager.
        topicRecyclerView.setLayoutManager(linearLayoutManager);
        // Create topic recycler view data adapter with topic item list.
        TopicListsAdapter topicDataAdapter = new TopicListsAdapter(listTopics);
        // Set data adapter.
        topicRecyclerView.setAdapter(topicDataAdapter);
        //Set Touch Listener
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
                Intent i = new Intent(TopicListsActivity.this, FlashCardDetailListsActivity.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                int positionPlus = position + 1;
                topicItemTitle = listTopics.get(position).getName();
                listFCsWithId = (ArrayList<FlashCardDetails>) myDatabaseHelper.getFCNodeWithIDTopic(positionPlus);

                //Check for each card with long press
                //Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("topicItemTitle", topicItemTitle);
                bundle.putSerializable("listTopics", listTopics);
                bundle.putSerializable("listFCsWithId", listFCsWithId);
                Intent i = new Intent(TopicListsActivity.this, FlashCardTestActivity.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        }));
    }

    //Create and set OnClick Tool Bar Menu For Topic List Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Get menu inflater.
        MenuInflater menuInflater = getMenuInflater();

        // Use app bar layout menu to inflate the tool bar.
        menuInflater.inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "This is DFTopic Quick Help", Toast.LENGTH_LONG).show();
        DFTopic dfTopic = new DFTopic();
        dfTopic.show(fragmentManager, "Dialog Fragment");
        return super.onOptionsItemSelected(item);
    }
}
