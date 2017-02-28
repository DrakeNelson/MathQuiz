package com.drakenelson.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.drakenelson.mathquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN="com.drakenelson.mathquiz.answer_shown";

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);

        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }


    private boolean answerIsTrue;

    private TextView answerTextView;
    private Button showAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        int currentIndex = 1;//com.drakenelson.mathquiz.MainActivity.currentIndex;
        showAnswer = (Button) findViewById(R.id.show_answer_button);
        answerTextView.setOnClickListener(e -> {
            if (answerIsTrue) {
                switch (currentIndex) {
                    case 0:
                        answerTextView.setText(R.string.correct_one);
                        break;
                    case 1:
                        answerTextView.setText(R.string.correct_two);
                        break;
                    case 2:
                        answerTextView.setText(R.string.correct_three);
                        break;
                    case 3:
                        answerTextView.setText(R.string.correct_four);
                        break;
                    default:
                        System.out.print("****************************case  FAIL(((((((((((((((*********************************");
                        break;
                }
            } else {
                switch (currentIndex) {
                    case 0:
                        answerTextView.setText(R.string.incorrect_one);
                        break;
                    case 1:
                        answerTextView.setText(R.string.incorrect_two);
                        break;
                    case 2:
                        answerTextView.setText(R.string.incorrect_three);
                        break;
                    case 3:
                        answerTextView.setText(R.string.incorrect_four);
                        break;
                    default:
                        System.out.print("****************************case  FAIL(((((((((((((((*********************************");
                        break;
                }
            }
            setAnswerShownResult(true);
        });
    }

    public void setAnswerShownResult(boolean answerShownResult) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, answerShownResult);
        setResult(RESULT_OK, data);
    }
}
