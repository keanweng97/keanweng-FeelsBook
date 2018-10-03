package com.example.keanweng_feelsbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class FeelsBookActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    //private ListView emotionList;
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    //private ArrayAdapter<Emotion> adapter;
    private TextView loveText;
    private TextView joyText;
    private TextView surpriseText;
    private TextView angerText;
    private TextView sadnessText;
    private TextView fearText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyText = (EditText) findViewById(R.id.comment);
        //emotionList = (ListView) findViewById(R.id.emotionList);
        Button loveButton = (Button) findViewById(R.id.love);
        Button joyButton = (Button) findViewById(R.id.joy);
        Button surpriseButton = (Button) findViewById(R.id.surprise);
        Button angerButton = (Button) findViewById(R.id.anger);
        Button sadnessButton = (Button) findViewById(R.id.sadness);
        Button fearButton = (Button) findViewById(R.id.fear);
        Button browseButton = (Button) findViewById(R.id.browseEmotion);
        Button countButton = (Button) findViewById(R.id.viewCount);
        loveText = findViewById(R.id.countLove);
        joyText = findViewById(R.id.countJoy);
        surpriseText = findViewById(R.id.countSurprise);
        angerText = findViewById(R.id.countAnger);
        sadnessText = findViewById(R.id.countSadness);
        fearText = findViewById(R.id.countFear);

        loveButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        angerButton.setOnClickListener(this);
        sadnessButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        browseButton.setOnClickListener(this);
        countButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        if(v.getId() == R.id.browseEmotion){
            // intent
        }
        else if(v.getId() == R.id.viewCount) {
            // dialog to show emotion count
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.view_count))
                   // string concatenation to display dialog message
                    .setMessage(
                           getString(R.string.love_count) + " " + String.valueOf(countEmotion("Love")) + "\n" +
                           getString(R.string.joy_count) + " " + String.valueOf(countEmotion("Joy")) + "\n" +
                           getString(R.string.surprise_count) + " " + String.valueOf(countEmotion("Surprise")) + "\n" +
                           getString(R.string.anger_count) + " " + String.valueOf(countEmotion("Anger")) + "\n" +
                           getString(R.string.sadness_count) + " " + String.valueOf(countEmotion("Sadness")) + "\n" +
                           getString(R.string.fear_count) + " " + String.valueOf(countEmotion("Fear")) + "\n"
                   )
                   .setCancelable(false)
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //close dialog
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            String text = bodyText.getText().toString();

            switch(v.getId()) {
                case R.id.love:
                    LoveEmotion newLoveEmotion = new LoveEmotion();
                    try {
                        newLoveEmotion.setComment(text);
                        emotions.add(newLoveEmotion);
                        bodyText.setText("");
                        displayRecorded();
                        displayCount();
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;

                case R.id.joy:
                    JoyEmotion newJoyEmotion = new JoyEmotion();
                    try {
                        newJoyEmotion.setComment(text);
                        emotions.add(newJoyEmotion);
                        bodyText.setText("");
                        displayRecorded();
                        displayCount();
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;

                case R.id.surprise:
                    SurpriseEmotion newSurpriseEmotion = new SurpriseEmotion();
                    try {
                        newSurpriseEmotion.setComment(text);
                        emotions.add(newSurpriseEmotion);
                        bodyText.setText("");
                        displayRecorded();
                        displayCount();
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;

                case R.id.anger:
                    AngerEmotion newAngerEmotion = new AngerEmotion();
                    try {
                        newAngerEmotion.setComment(text);
                        emotions.add(newAngerEmotion);
                        bodyText.setText("");
                        displayRecorded();
                        displayCount();
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;

                case R.id.sadness:
                    SadnessEmotion newSadnessEmotion = new SadnessEmotion();
                    try {
                        newSadnessEmotion.setComment(text);
                        emotions.add(newSadnessEmotion);
                        bodyText.setText("");
                        displayRecorded();
                        displayCount();
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;

                case R.id.fear:
                    FearEmotion newFearEmotion = new FearEmotion();
                    try {
                        newFearEmotion.setComment(text);
                        emotions.add(newFearEmotion);
                        bodyText.setText("");
                        displayRecorded();
                        displayCount();
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        displayCount();
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

    public void displayException(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void displayRecorded(){
        Toast toast = Toast.makeText(this, "Emotion recorded!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void displayCount(){
        loveText.setText(updateCountString("Love"));
        joyText.setText(updateCountString("Joy"));
        surpriseText.setText(updateCountString("Surprise"));
        angerText.setText(updateCountString("Anger"));
        sadnessText.setText(updateCountString("Sadness"));
        fearText.setText(updateCountString("Fear"));
    }

    public String updateCountString(String emotion){
        String countString = emotion + ":";
        int count = countEmotion(emotion);
        if (count > 99){
            countString = countString + "99+";
        } else {
            countString = countString + String.valueOf(count);
        }
        return countString;
    }

    public int countEmotion(String emotionToMatch) {

        int count = 0;
        for (Emotion emotion: emotions) {
            if ((emotion.moodtype).equals(emotionToMatch)) {
                count++;
            }
        }
        return count;
    }
}
