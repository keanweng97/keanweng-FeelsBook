package com.example.keanweng_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FeelsBookActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    //private ListView emotionList;
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    //private ArrayAdapter<Emotion> adapter;

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
            // intent or dialog
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
                        saveInFile();
                    } catch (TooLongCommentException e) {
                        e.printStackTrace();
                        displayException(e.getMessage());
                    }
                    break;
            }
        }
    }

    /*@Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }
*/

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
}
