/* 
 Author: Rawen Jendoubi
 Project: Creating Intelligent Interactive Systems with Android - Voice Notes Android  App
 This MainActivity.java file is just a small glimpse of the initial draft of the project. In the final version, the app includes multiple activities,
 such as inserting and updating notes, exporting notes to a PDF file and managing them through a dedicated database (NotesDAO) that handles initialization and updates.
 For the voice commands, I used Google’s Speech-to-Text API to convert speech into text, which allows users to interact with the app using natural voice commands.
 No Firebase or external backend was used — the entire project was built from scratch by me, including the database and all activities.
 */
package com.example.voiceautomation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //to call the voice command
        voiceautomation();
    }

    private void voiceautomation() {
        Intent  voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak open camera...");
        startActivityForResult(voice,1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&& resultCode==RESULT_OK&&data!=null){

            ArrayList<String >arrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if(arrayList.get(0).toString().equals("insert")){

                Intent intent = new Intent(getApplicationContext(),insert_Notes_Activity.class);
                startActivity(intent);

            }


        }
    }
}




