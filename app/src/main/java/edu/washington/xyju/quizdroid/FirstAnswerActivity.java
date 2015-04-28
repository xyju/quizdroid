package edu.washington.xyju.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;


public class FirstAnswerActivity extends ActionBarActivity {

    int myValue;
    LinearLayout math1,physics1,msh1;
    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_answer);
        Intent answer = getIntent();
        myValue = answer.getIntExtra("topics",0);
        switch(myValue) {
            case 0:
                math1 = (LinearLayout) findViewById(R.id.math1);
                math1.setVisibility(View.VISIBLE);
                TextView m1Answers1 = (TextView) findViewById(R.id.m1answer2);
                TextView m1Answers2 = (TextView) findViewById(R.id.m1answer3);

                FirstQuestionActivity question1 = new FirstQuestionActivity();
                m1Answers1.setText("Your answer is: " + question1.yourAnswer);
                m1Answers2.setText("You have " + question1.mcounter + " out of " + question1.mquestionNum + " correct");
                break;
            case 1:
                physics1 = (LinearLayout) findViewById(R.id.physics1);
                physics1.setVisibility(View.VISIBLE);
                TextView p1Answers1 = (TextView) findViewById(R.id.p1answer2);
                TextView p1Answers2 = (TextView) findViewById(R.id.p1answer3);

                FirstQuestionActivity question2 = new FirstQuestionActivity();
                p1Answers1.setText("Your answer is: " + question2.yourAnswer);
                p1Answers2.setText("You have " + question2.pcounter + " out of " + question2.pquestionNum + " correct");
                break;
            case 2:
                msh1 = (LinearLayout) findViewById(R.id.msh1);
                msh1.setVisibility(View.VISIBLE);
                TextView msh1Answers1 = (TextView) findViewById(R.id.msh1answer2);
                TextView msh1Answers2 = (TextView) findViewById(R.id.msh1answer3);

                FirstQuestionActivity question3 = new FirstQuestionActivity();
                msh1Answers1.setText("Your answer is: " + question3.yourAnswer);
                msh1Answers2.setText("You have " + question3.mshcounter + " out of " + question3.mshquestionNum + " correct");
                break;
        }

        nextbtn = (Button) findViewById(R.id.m1btn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(FirstAnswerActivity.this, SecondQuestionActivity.class);
                switch(myValue){
                    case 0:
                        next.putExtra("question2",myValue);
                        break;
                    case 1:
                        next.putExtra("question2",myValue);
                        break;
                    case 2:
                        next.putExtra("question2",myValue);
                        break;
                }
                startActivity(next);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
