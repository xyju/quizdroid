package edu.washington.xyju.quizdroidpart3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class Question extends ActionBarActivity {

    private static int myValue;
    Topic[] topicName = {new Topic(),new Topic(),new Topic()};

    private static int i, j;
    private int selectedOption;
    private static String yourAnswer;
    TextView topic,answer;
    RadioButton r1,r2,r3,r4;
    RadioGroup radio;
    Button next,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        QuizApp quizApp = (QuizApp)getApplication();
        quizApp.readJson("questions.json",topicName);


        Intent choice = getIntent();
        myValue = choice.getIntExtra("choice", 0);

        topic = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);
        r1 = (RadioButton) findViewById(R.id.radioc1);
        r2 = (RadioButton) findViewById(R.id.radioc2);
        r3 = (RadioButton) findViewById(R.id.radioc3);
        r4 = (RadioButton) findViewById(R.id.radioc4);

        topic.setText(topicName[myValue].getQuestion(j).getText());
        r1.setText(topicName[myValue].getQuestion(j).getAnswer1());
        r2.setText(topicName[myValue].getQuestion(j).getAnswer2());
        r3.setText(topicName[myValue].getQuestion(j).getAnswer3());
        r4.setText(topicName[myValue].getQuestion(j).getAnswer4());
        answer.setText("The correct answer is " + topicName[myValue].getQuestion(j).getAnswer());


        radio = (RadioGroup) findViewById(R.id.radiobtn);
        next = (Button) findViewById(R.id.next);
        submit = (Button) findViewById(R.id.submit);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = radio.getCheckedRadioButtonId();

                if (selectedOption > 0) {

                    answer.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.INVISIBLE);

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            j++;

                            if (j < topicName[myValue].getQuestionsList().size()-3) {

                                Intent nextQuestion = new Intent(Question.this, Question.class);
                                nextQuestion.putExtra("choice", myValue);
                                startActivity(nextQuestion);

                            } else {
                                j = 0;
                                Intent back = new Intent(Question.this, MainActivity.class);
                                startActivity(back);
                            }


                        }
                    });
                }
            }
        });
    }

}
