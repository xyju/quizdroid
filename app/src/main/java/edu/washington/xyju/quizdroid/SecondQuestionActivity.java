package edu.washington.xyju.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;


public class SecondQuestionActivity extends ActionBarActivity {

    static String  yourAnswer;
    int selectedOption, myValue;
    Button submit2,back2;
    LinearLayout math2,physics2,msh2;
    RadioGroup radioMathId2,radioPhysicsId2,radiomshId2;
    RadioButton radioMathBtn2,radioPhysicsbtn2,radioMshbtn2;
    FirstQuestionActivity questions = new FirstQuestionActivity();
    RadioGroup rG1;
    RadioGroup rG2;
    RadioGroup rG3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        rG1 = (RadioGroup) findViewById(R.id.radiomath2);
        rG2 = (RadioGroup) findViewById(R.id.radiophysics2);
        rG3 = (RadioGroup) findViewById(R.id.radiomsh2);

        submit2 = (Button) findViewById(R.id.submit2);
        back2 = (Button) findViewById(R.id.back2);
        radioMathId2 = (RadioGroup) findViewById(R.id.radiomath2);
        radioPhysicsId2 = (RadioGroup) findViewById(R.id.radiophysics2);
        radiomshId2 = (RadioGroup) findViewById(R.id.radiomsh2);

        Intent topics = getIntent();
        myValue = topics.getIntExtra("question2",0);

        rG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit2.setVisibility(View.VISIBLE);
            }
        });
        rG2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit2.setVisibility(View.VISIBLE);
            }
        });
        rG3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit2.setVisibility(View.VISIBLE);
            }
        });
        switch(myValue){
            case 0:
                math2 = (LinearLayout) findViewById(R.id.math2);
                math2.setVisibility(View.VISIBLE);
                break;
            case 1:
                physics2 = (LinearLayout) findViewById(R.id.physics2);
                physics2.setVisibility(View.VISIBLE);
                break;
            case 2:
                msh2 = (LinearLayout) findViewById(R.id.msh2);
                msh2.setVisibility(View.VISIBLE);
                break;

        }

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(myValue){
                    case 0:
                        selectedOption = radioMathId2.getCheckedRadioButtonId();
                        radioMathBtn2 = (RadioButton) findViewById(selectedOption);
                        yourAnswer = radioMathBtn2.getText().toString();
                        break;
                    case 1:
                        selectedOption = radioPhysicsId2.getCheckedRadioButtonId();
                        radioPhysicsbtn2 = (RadioButton) findViewById(selectedOption);
                        yourAnswer = radioPhysicsbtn2.getText().toString();
                        break;
                    case 2:
                        selectedOption = radiomshId2.getCheckedRadioButtonId();
                        radioMshbtn2 = (RadioButton) findViewById(selectedOption);
                        yourAnswer = radioMshbtn2.getText().toString();
                        break;
                }
                if(selectedOption > 0){
                    switch(myValue){
                        case 0:
                            questions.mquestionNum++;
                            break;
                        case 1:
                            questions.pquestionNum++;
                            break;
                        case 2:
                            questions.mshquestionNum++;
                            break;
                    }


                    Intent question = new Intent(SecondQuestionActivity.this, SecondAnswerActivity.class);
                    switch(myValue){
                        case 0:
                            question.putExtra("topics",myValue);
                            break;
                        case 1:
                            question.putExtra("topics",myValue);
                            break;
                        case 2:
                            question.putExtra("topics",myValue);
                            break;
                    }
                    startActivity(question);
                    switch(selectedOption) {
                        case R.id.m2radioq2:
                            questions.mcounter++;
                            break;
                        case R.id.p2radioq2:
                            questions.pcounter++;
                            break;
                        case R.id.msh2radioq2:
                            questions.mshcounter++;
                            break;
                    }
                }else{
                    yourAnswer = "Please select one answer";
                    Toast.makeText(SecondQuestionActivity.this, yourAnswer, Toast.LENGTH_SHORT).show();

                }
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back2 = new Intent(SecondQuestionActivity.this,FirstQuestionActivity.class);
                switch(myValue){
                    case 0:
                        back2.putExtra("question",myValue);
                        break;
                    case 1:
                        back2.putExtra("question",myValue);
                        break;
                    case 2:
                        back2.putExtra("question",myValue);
                        break;
                }
                startActivity(back2);

                switch(myValue){
                    case 0:
                        questions.mquestionNum=0;
                        questions.mcounter=0;
                        break;
                    case 1:
                        questions.pquestionNum=0;
                        questions.pcounter=0;
                        break;
                    case 2:
                        questions.mshquestionNum=0;
                        questions.mshcounter=0;
                        break;
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_question, menu);
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
