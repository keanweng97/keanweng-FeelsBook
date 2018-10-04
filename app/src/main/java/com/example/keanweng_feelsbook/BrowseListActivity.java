package com.example.keanweng_feelsbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BrowseListActivity extends AppCompatActivity {
    public static final String POS_EXTRA = "com.example.keanweng-FeelsBook.POS";
    private static final String FILENAME = "file.sav";
    private RecyclerView emotionList;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_list);

        emotionList = (RecyclerView) findViewById(R.id.emotionList);

        emotionList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        emotionList.setLayoutManager(layoutManager);

        emotionList.addItemDecoration(new DividerItemDecoration(emotionList.getContext(),
                DividerItemDecoration.VERTICAL));

        loadFromFile();

        adapter = new MyAdapter(emotions);
        adapter.setOnEntryClickListener(new MyAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                // stuff that will happen when a list item is clicked
//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK button
//                    }
//                });
//                builder.setNeutralButton(R.string.edit, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked edit button
//                    }
//                });
//                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked delete button
//                    }
//                });
//                builder.setTitle(R.string.emotion_detail)
//                       .setMessage("test");
//                final AlertDialog dialog = builder.create();
//                findViewById(R.id.emotionList).post(new Runnable() {
//                    public void run() {
//                        dialog.show();
//                    }
//                });
                Intent intent = new Intent(getApplicationContext(), EmotionDetailActivity.class);
                intent.putExtra(POS_EXTRA, position);
                startActivity(intent);
            }
        });
        emotionList.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        emotionList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListTweets = new TypeToken<ArrayList<Emotion>>() {
            }.getType();
            emotions = gson.fromJson(reader, typeListTweets);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
