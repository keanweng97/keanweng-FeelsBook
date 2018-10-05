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
    private ArrayList<Emotion> temp_emotions = new ArrayList<Emotion>();

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
        emotionList.setAdapter(adapter);
        adapter.setOnEntryClickListener(new MyAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), EmotionDetailActivity.class);
                intent.putExtra(POS_EXTRA, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        loadFromFile();
        adapter.notifyDataSetChanged();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListEmotions = new TypeToken<ArrayList<Emotion>>() {
            }.getType();

            temp_emotions = gson.fromJson(reader, typeListEmotions);
            emotions.clear();
            emotions.addAll(temp_emotions);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
