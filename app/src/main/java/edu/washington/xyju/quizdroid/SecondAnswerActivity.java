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




public class SecondAnswerActivity extends ActionBarActivity {

    int myValue;
    LinearLayout math2,physics2,msh2;
    Button finishbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_answer);


        Intent answer2 = getIntent();
        myValue = answer2.getIntExtra("topics",0);
        switch(myValue) {
            case 0 :
                math2 = (LinearLayout) findViewById(R.id.math2);
                math2.setVisibility(View.VISIBLE);

                TextView m2Answers1 = (TextView) findViewById(R.id.m2answer2);
                TextView m2Answers2 = (TextView) findViewById(R.id.m2answer3);

                FirstQuestionActivity question1 = new FirstQuestionActivity();
                m2Answers1.setText("Your answer is: " + question1.yourAnswer);
                m2Answers2.setText("You have " + question1.mcounter + " out of " + question1.mquestionNum + " correct");
                break;
            case 1:
                physics2 = (LinearLayout) findViewById(R.id.physics2);
                physics2.setVisibility(View.VISIBLE);

                TextView p2Answers1 = (TextView) findViewById(R.id.p2answer2);
                TextView p2Answers2 = (TextView) findViewById(R.id.p2answer3);

                FirstQuestionActivity question2 = new FirstQuestionActivity();
                p2Answers1.setText("Your answer is: " + question2.yourAnswer);
                p2Answers2.setText("You have " + question2.pcounter + " out of " + question2.pquestionNum + " correct");
                break;
            case 2:
                msh2= (LinearLayout) findViewById(R.id.msh2);
                msh2.setVisibility(View.VISIBLE);

                TextView msh2Answers1 = (TextView) findViewById(R.id.msh2answer2);
                TextView msh2Answers2 = (TextView) findViewById(R.id.msh2answer3);

                FirstQuestionActivity question3 = new FirstQuestionActivity();
                msh2Answers1.setText("Your answer is: " + question3.yourAnswer);
                msh2Answers2.setText("You have " + question3.mshcounter + " out of " + question3.mshquestionNum + " correct");
                break;
        }

        finishbtn = (Button) findViewById(R.id.m2btn);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstQuestionActivity question4 = new FirstQuestionActivity();
                switch(myValue){
                    case 0:
                        question4.mquestionNum=0;
                        question4.mcounter=0;
                        break;
                    case 1:
                        question4.pquestionNum=0;
                        question4.pcounter=0;
                        break;
                    case 2:
                        question4.mshquestionNum=0;
                        question4.mshcounter=0;
                        break;
                }
                Intent finish = new Intent(SecondAnswerActivity.this, MainActivity.class);
                startActivity(finish);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_answer, menu);
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
