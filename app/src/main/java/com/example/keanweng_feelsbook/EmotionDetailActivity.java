package com.example.keanweng_feelsbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmotionDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String FILENAME = "file.sav";
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    private TextView dateText;
    private TextView emotionText;
    private TextView commentText;
    private Calendar date;
    private int position;
    private Emotion emotion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_detail);

        dateText = findViewById(R.id.date);
        emotionText = findViewById(R.id.emotion);
        commentText = findViewById(R.id.comment);
        Button editEmotion = findViewById(R.id.edit_emotion);
        Button editDate = findViewById(R.id.edit_date);
        Button editComment = findViewById(R.id.edit_comment);

        editEmotion.setOnClickListener(this);
        editDate.setOnClickListener(this);
        editComment.setOnClickListener(this);

        Intent intent = getIntent();
        position = intent.getIntExtra(BrowseListActivity.POS_EXTRA, 0);

        updateEmotion();

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.edit_date:
                showDateTimePicker();
                break;

            case R.id.edit_emotion:
                break;

            case R.id.edit_comment:
                break;
        }
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

    private void updateEmotion() {
        loadFromFile();
        emotion = emotions.get(position);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date uf_date = emotion.getDate();
        String f_date = df.format(uf_date);

        dateText.setText(f_date);
        emotionText.setText(emotion.getMoodtype());
        commentText.setText(emotion.getComment());
    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(emotion.getDate());
        date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(EmotionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        emotion.setDate(date.getTime());
                        emotions.set(position, emotion);
                        saveInFile();
                        updateEmotion();
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
}
