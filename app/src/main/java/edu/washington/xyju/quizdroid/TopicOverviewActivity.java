package edu.washington.xyju.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


public class TopicOverviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);
        Button begin = (Button) findViewById(R.id.begin1);
        Intent topics = getIntent();
        final int myValue = topics.getIntExtra("choice",0);
        switch(myValue) {
            case 0:
                TextView math = (TextView) findViewById(R.id.to2);
                math.setVisibility(View.VISIBLE);
                break;
            case 1:
                TextView physics = (TextView) findViewById(R.id.to3);
                physics.setVisibility(View.VISIBLE);
                break;
            case 2:
                TextView marvel = (TextView) findViewById(R.id.to4);
                marvel.setVisibility(View.VISIBLE);
                break;
        }

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent question = new Intent(TopicOverviewActivity.this,FirstQuestionActivity.class);
                switch(myValue){
                    case 0:
                        question.putExtra("question",myValue);
                        break;
                    case 1:
                        question.putExtra("question",myValue);
                        break;
                    case 2:
                        question.putExtra("question",myValue);
                        break;
                }
                startActivity(question);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
