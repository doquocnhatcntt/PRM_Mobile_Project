package com.nhatdo.whatisthis;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Topics> listTopics;
    ArrayList<FlashCardDetails> listFCs;
    Button btnPlay, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bd = getIntent().getBundleExtra("data");
        listTopics = (ArrayList<Topics>) bd.getSerializable("listTopics");
        listFCs = (ArrayList<FlashCardDetails>) bd.getSerializable("listFCs");

        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("listTopics", listTopics);
                bundle.putSerializable("listFCs", listFCs);
                Intent i = new Intent(MainActivity.this, TopicListsActivity.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        });

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Don't leave me, please!");
                builder.setPositiveButton("Exit Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
