package com.rssin.braintrainer;

import android.os.CountDownTimer;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView sumTextView;
    int locationOfAns;
    int score;
    int noOfQuestions;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    Button startButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    RelativeLayout relativeLayout;
    public void playAgain(View view){
         score=0;
         noOfQuestions=0;
         timerTextView.setText("30s");
         resultTextView.setText("");
         scoreTextView.setText("0/0");
         playAgain.setVisibility(view.INVISIBLE);
         countDownTimer();
    }
    public void generateQuestion(){
        Random rand;
        rand = new Random();
        int a=rand.nextInt(21);
        int b= rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfAns=rand.nextInt(4);
        for(int i=0;i<4;i++){
            if(i==locationOfAns){
                answers.add(a+b);
            }else{
                int incorrectAns=rand.nextInt(42);
                while(incorrectAns==(a+b)){
                    incorrectAns=rand.nextInt(42);
                }
                answers.add(incorrectAns);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(view.VISIBLE);
        countDownTimer();
        generateQuestion();
    }
    public void countDownTimer(){
        new CountDownTimer(31000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your Score :"+Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
                playAgain.setVisibility(findViewById(R.id.playAgainButton).VISIBLE);
            }
        }.start();
    }
    public void choosedAns(View view){
//        Log.i("ans",view.getTag().toString()+ locationOfAns);
        if(view.getTag().toString().equals(Integer.toString(locationOfAns))){
           resultTextView.setText("Correct!");
           score++;

        }else{
            resultTextView.setText("Incorrect!");
        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        answers.clear();
        generateQuestion();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);
        relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        button1=(Button)findViewById(R.id.ansButton1);
        button2=(Button)findViewById(R.id.ansButton2);
        button3=(Button)findViewById(R.id.ansButton3);
        button4=(Button)findViewById(R.id.ansButton4);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playAgain=(Button)findViewById(R.id.playAgainButton);



    }
}
