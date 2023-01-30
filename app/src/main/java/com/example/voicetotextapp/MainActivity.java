package com.example.voicetotextapp;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_mic1;
    private TextView tv_Speech_to_text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    ListView listView;
    String mAnswer = "";
    ArrayList<String> result;
    //String courseList1 [] = new String[4];
    int num = 0;
    LinkedList<String> ll= new LinkedList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* LinearLayout llMain = findViewById(R.id.rlMain);
        TextView textView = new TextView(this);
        textView.setText("I am added dynamically to the view");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        textView.setLayoutParams(params);
        llMain.addView(textView);*/


        iv_mic1 = findViewById(R.id.iv_mic);
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);
        listView = findViewById(R.id.list_item);




        iv_mic1.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);


            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mAnswer = result.get(0);
                    tv_Speech_to_text.setText(mAnswer);
                    //getListView(result);

                onResultExecute(result.get(0),num);


            }

        }
    }

   /* private void getListView(ArrayList<String> result) {
        ArrayAdapter<String> adapter ;
        Collection <String> collect = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,R.id.textView, (List<String>) collect);
        for (String e:result) {
            adapter.add(e);
            listView.setAdapter(adapter);
        }


    }*/
   protected void onResultExecute(String string,int po){

       for(int i=po;i<=po;i++) ll.add(i, string);
       ArrayAdapter<String> adapter ;
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ll);
       listView.setAdapter(adapter);
       num++;

   }


}