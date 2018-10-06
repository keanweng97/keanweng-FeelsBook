package com.example.keanweng_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BrowseListActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private MyAdapter adapter;
    private final ArrayList<Emotion> emotions = new ArrayList<>();
    private ArrayList<Emotion> temp_emotions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_list);

        RecyclerView emotionList = findViewById(R.id.emotionList);
        emotionList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        emotionList.setLayoutManager(layoutManager);
        emotionList.addItemDecoration(new DividerItemDecoration(emotionList.getContext(),
                DividerItemDecoration.VERTICAL));

        loadFromFile();

        adapter = new MyAdapter(emotions);
        emotionList.setAdapter(adapter);
        adapter.setOnEntryClickListener(new MyAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(int position) {
                Intent intent = new Intent(getApplicationContext(), EmotionDetailActivity.class);
                //intent.putExtra("Class","BrowseList");
                intent.putExtra("Pos", position);
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
            emotionSort();
            emotions.clear();
            emotions.addAll(temp_emotions);
            saveInFile();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(emotions,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void emotionSort(){
        Collections.sort(temp_emotions, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion o1, Emotion o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }
}
