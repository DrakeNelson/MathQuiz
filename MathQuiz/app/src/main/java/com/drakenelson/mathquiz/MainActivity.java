package com.drakenelson.mathquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Question[] questionses = new Question[]{
            new Question(R.string.question_one, true, R.string.correct_one, R.string.incorrect_one),
            new Question(R.string.question_two, true, R.string.correct_two, R.string.incorrect_two),
            new Question(R.string.question_three, false, R.string.correct_three, R.string.incorrect_three),
            new Question(R.string.question_four, true, R.string.correct_four, R.string.incorrect_four)
    };

    private static final String TAG = "QuizActivity";
    private static final int REQUEST_CODE_CHEAT = 0;
     private int currentIndex = 0;
    private static final String KEY_INDEX = "index";

    private TextView questionView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "OnSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    //the bundle class is the saved state of the program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = (TextView) findViewById(R.id.Question_Text);
        mTrueButton = (Button) findViewById(R.id.left_button);
        mFalseButton = (Button) findViewById(R.id.right_button);
        updateQuestion();

        View mNextButton;
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        View mPreButton;
        mPreButton = (ImageButton) findViewById(R.id.previous_button);

        questionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questionses.length;
                updateQuestion();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionses[currentIndex].isAnswerTrue()) {
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!questionses[currentIndex].isAnswerTrue()) {
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questionses.length;
                isCheater = false;
                updateQuestion();
            }
        });
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex == 0) ? currentIndex = questionses.length - 1 : currentIndex - 1;
                updateQuestion();
            }
        });
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState != null) {
                    currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
                    //Intent i = new Intent(MainActivity.this, CheatActivity.class);

                    boolean answerIsTrue = questionses[currentIndex].isAnswerTrue();
                    Intent i = CheatActivity.newIntent(MainActivity.this, answerIsTrue);

                    //startActivity(i);
                    startActivityForResult(i, REQUEST_CODE_CHEAT);
                }
            }
        });

        updateQuestion();
    }

    private boolean isCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            isCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void updateQuestion() {
        int question = questionses[currentIndex].getTextResId();
        questionView.setText(question);
        int correctAnswer = questionses[currentIndex].getCorrectString();
        mTrueButton.setText(correctAnswer);
        int wrongAnswer = questionses[currentIndex].getIncorrectString();
        mFalseButton.setText(wrongAnswer);
    }
}
