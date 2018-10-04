package com.example.keanweng_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmotionDetailActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    private TextView dateText;
    private TextView emotionText;
    private TextView commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_detail);

        dateText = findViewById(R.id.date);
        emotionText = findViewById(R.id.emotion);
        commentText = findViewById(R.id.comment);

        Intent intent = getIntent();
        int position = intent.getIntExtra(BrowseListActivity.POS_EXTRA, 0);

        loadFromFile();
        Emotion emotion = emotions.get(position);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = emotion.getDate();
        String f_date = df.format(date);

        dateText.setText(f_date);
        emotionText.setText(emotion.getMoodtype());
        commentText.setText(emotion.getComment());

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
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
