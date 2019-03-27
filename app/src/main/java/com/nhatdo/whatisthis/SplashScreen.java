package com.nhatdo.whatisthis;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.nhatdo.whatisthis.SqlHelper.MyDatabaseHelper;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Intent intent = new Intent(this, MainActivity.class);
        final MyDatabaseHelper mySqlHelper = new MyDatabaseHelper(this);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    mySqlHelper.insertDefaultValues();
                    ArrayList<Topics> listTopics = (ArrayList<Topics>) mySqlHelper.getAllTopicNode();
                    ArrayList<FlashCardDetails> listFCs = (ArrayList<FlashCardDetails>) mySqlHelper.getAllFCNode();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("listTopics", listTopics);
                    bundle.putSerializable("listFCs", listFCs);
                    intent.putExtra("data", bundle);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
