package com.nhatdo.whatisthis;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nhatdo.whatisthis.TutorialDialogFragment.DFFlashCardTest;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class FlashCardTestActivity extends AppCompatActivity {

    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCsWithId;
    RecyclerView flashCardRecyclerView;
    String topicItemTitle = "";
    private Context context;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_test);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("");
        Bundle bd = getIntent().getBundleExtra("data");
        topicItemTitle = bd.getString("topicItemTitle");
        listTopics = (ArrayList<Topics>) bd.getSerializable("listTopics");
        listFCsWithId = (ArrayList<FlashCardDetails>) bd.getSerializable("listFCsWithId");

        Toolbar toolbarfc = (Toolbar) findViewById(R.id.appbarlayout_tool_bar_fc);
        TextView FcTitle = toolbarfc.findViewById(R.id.txtToolbarTitle);
        FcTitle.setText(topicItemTitle + " Test");
        FcTitle.setTextSize(30);
        setSupportActionBar(toolbarfc);
        context = getApplicationContext();
        //Set up a Back Navigator icon into a ToolBar
        toolbarfc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("listTopics", listTopics);
                bundle.putSerializable("listFCsWithId", listFCsWithId);
                Intent i = new Intent(FlashCardTestActivity.this, TopicListsActivity.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        });
        createFlashCardTestListUseRecyclerView();
    }

    private void createFlashCardTestListUseRecyclerView() {
        // Create the recyclerview.
        flashCardRecyclerView = (RecyclerView) findViewById(R.id.appbarlayout_recycler_view_fc_test);

        //Swipe one item at a time Recyclerview using PagerSnapHelper
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(flashCardRecyclerView);

        /*Use this setting to improve performance if you know that changes
          in content do not change the layout size of the RecyclerView*/
        flashCardRecyclerView.setHasFixedSize(true);

        // Create the linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FlashCardTestActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // Set layout manager.
        flashCardRecyclerView.setLayoutManager(linearLayoutManager);
        // Create Flash Card recycler view data adapter with Flash Card item list.
        System.out.println("=====>Check context from FlashCardTestActivity to Adapter before transfer: context = " + context);
        FlashCardTestAdapter flashCardTestDataAdapter = new FlashCardTestAdapter(listFCsWithId, context);
        // Set data adapter.
        flashCardRecyclerView.setAdapter(flashCardTestDataAdapter);
    }

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
        DFFlashCardTest dfFlashCardTest = new DFFlashCardTest();
        dfFlashCardTest.show(fragmentManager, "Dialog Fragment");
        return super.onOptionsItemSelected(item);
    }
}
