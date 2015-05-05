package edu.washington.xyju.quizdroidpart2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;


public class MultiUseActivity extends FragmentActivity implements QuestionFragment.SendValue {

    private static int position,jumpTo = 0;
    private static int cNum,totalNum;
    private static String yourAnswer;
    Fragment startQFragment,startAFragment,startOvFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_use);

        Intent choice = getIntent();
        position = choice.getIntExtra("choice",position);

        Intent swap = getIntent();
        jumpTo = swap.getIntExtra("jumpTo",0);

        switch(jumpTo) {
            case 0:
                startOvFragment = passValueToOv(position);
                switchToOv();
                break;
            case 1:
                startQFragment = passValueToQ(position);
                switchToQ();
                break;
            case 2:
                startAFragment = passValueToA(position);
                switchToA();
                break;
            default:
                break;

        }


    }

    private Fragment passValueToQ(int position){
        Bundle bundle = new Bundle();
        bundle.putInt("passValue2",position);

        QuestionFragment question = new QuestionFragment();
        question.setArguments(bundle);
        return question;
    }

    private void switchToQ(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_enter,R.anim.slide_out)
                .add(R.id.show,startQFragment)
                .commit();
    }

    private Fragment passValueToA(int position){
        Bundle bundle = new Bundle();
        bundle.putInt("passValue3",position);
        bundle.putInt("correct",cNum);
        bundle.putInt("total",totalNum);
        bundle.putString("answer",yourAnswer);

        AnswerFragment answer = new AnswerFragment();
        answer.setArguments(bundle);
        return answer;
    }

    private void switchToA(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_enter,R.anim.slide_out)
                .replace(R.id.show, startAFragment)
                .commit();
    }

    private Fragment passValueToOv(int position){
        Bundle bundle = new Bundle();
        bundle.putInt("passValue1",position);

        OverviewFragment topicOverview = new OverviewFragment();
        topicOverview.setArguments(bundle);
        return topicOverview;
    }

    private void switchToOv(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_enter,R.anim.slide_out)
                .add(R.id.show,startOvFragment)
                .commit();
    }


    @Override
    public void passData(int correct, int total, String answer) {
        this.cNum = correct;
        this.totalNum = total;
        this.yourAnswer = answer;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_use, menu);
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
