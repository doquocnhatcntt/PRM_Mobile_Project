package com.nhatdo.whatisthis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class TopicLists extends AppCompatActivity {

    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCs;
    RecyclerView topicRecyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_lists);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("dev2qa.com - Android AppBarLayout Example.");

        Toolbar toolbar = (Toolbar)findViewById(R.id.appbarlayout_tool_bar);
        toolbar.setTitle("Topics");
        setSupportActionBar(toolbar);

        Bundle bd = getIntent().getBundleExtra("data");
        listTopics = (ArrayList<Topics>) bd.getSerializable("listTopics");
        listFCs = (ArrayList<FlashCardDetails>) bd.getSerializable("listFCs");

        createTopicListUseRecyclerView();
    }

    // This method shows how to customize SimpleAdapter to show image and text in ListView.
    private void createTopicListUseRecyclerView()
    {
        // Create the recyclerview.
        topicRecyclerView = (RecyclerView)findViewById(R.id.appbarlayout_recycler_view);
        // Create the linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        topicRecyclerView.setLayoutManager(linearLayoutManager);

        // Create car recycler view data adapter with car item list.
        AppBarLayoutRecyclerViewDataAdapter topicDataAdapter = new AppBarLayoutRecyclerViewDataAdapter(listTopics);
        // Set data adapter.
        topicRecyclerView.setAdapter(topicDataAdapter);
    }

//    /* Invoked when user click the menu in tool bar. */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        // Get menu item id.
//        int menuItemId = item.getItemId();
//
//        // Get item list size.
//        int size = listTopics.size();
//
//        // Get an item index randomly.
//        Random random = new Random();
//        int randomIndex = random.nextInt(size);
//
//        // If add menu is clicked.
//        if(menuItemId == R.id.appbarlayout_add_car_menu)
//        {
//            // Get random item.
//            AppBarLayoutRecyclerViewItem randomItem = carList.get(randomIndex);
//            // Add the item in car list.
//            carList.add(randomItem);
//            // Get new car list size.
//            int carListSize = carList.size();
//            // Notify recycler view data adapter a new item has been added.
//            carRecyclerView.getAdapter().notifyItemInserted(carListSize - 1);
//
//            Toast.makeText(this, "A car has been added at the bottom of the list.", Toast.LENGTH_SHORT).show();
//        }else if(menuItemId == R.id.appbarlayout_delete_car_menu)
//        {
//            // Remove the random selected item.
//            carList.remove(randomIndex);
//            // Get the car list size again.
//            int carListSize = carList.size();
//            // Avoid index out of bounds exception.
//            if(randomIndex > carListSize - 1)
//            {
//                randomIndex = carListSize - 1;
//            }
//            // Notify recycler view an item has been deleted.
//            carRecyclerView.getAdapter().notifyItemRemoved(randomIndex);
//
//            Toast.makeText(this, "A car has been removed from the list.", Toast.LENGTH_SHORT).show();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
