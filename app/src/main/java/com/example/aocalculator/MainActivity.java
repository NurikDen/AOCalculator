package com.example.aocalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
     private TextView mathtext , answertextview;
     private Button answerbutton , nextbutton;
     private EditText edittextnumber;
     private String[] comands;
     private Random random;
     private int f,s,r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mathtext = findViewById(R.id.primer);
        answertextview = findViewById(R.id.yes);
        answerbutton = findViewById(R.id.answer);
        nextbutton = findViewById(R.id.next);
        edittextnumber = findViewById(R.id.editTextNumber);
        comands = new String[]{"+","*"};
        random = new Random();
        generate();
        answerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswer();
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }
    private void generate(){
        f = random.nextInt(99)+1;
        s = random.nextInt(99)+1;
        String command = comands[random.nextInt(comands.length)];
        switch (command){
            case"+":
                r = f+s;
                break;
            case "*":
                r = f*s;
                break;
        }
         mathtext.setText(f+" "+command+" "+s+" =?");
    }
    private void onAnswer(){
        boolean v = false;
        try {
            int res = Integer.parseInt(edittextnumber.getText().toString());
            v= r==res;
        }catch (Exception e){
            answertextview.setVisibility(View.VISIBLE);
            answertextview.setText("It's not number");

        }
        answerbutton.setVisibility(View.GONE);
        answertextview.setBackgroundColor(v ? getResources().getColor(R.color.green) :  getResources().getColor(R.color.red));
        answertextview.setText(v ?  R.string.right_answer: R.string.wrong_answer);
        answertextview.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.VISIBLE);

    }
    private void next(){
        nextbutton.setVisibility(View.GONE);
        answertextview.setVisibility(View.GONE);
        answerbutton.setVisibility(View.VISIBLE);
        generate();


    }
}