package edu.washington.xyju.quizdroidpart3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class TopicOverview extends ActionBarActivity {

    private static int myValue;
    Topic[] topicName = {new Topic(),new Topic(),new Topic()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        QuizApp quizApp = (QuizApp)getApplication();
        quizApp.readJson("questions.json",topicName);

        Intent choice = getIntent();
        myValue = choice.getIntExtra("choice",0);

        TextView title = (TextView) findViewById(R.id.to1);
        TextView des = (TextView) findViewById(R.id.to2);

        title.setText(topicName[myValue].getTitle());
        des.setText(topicName[myValue].getsDes());


        Button begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionPage = new Intent(TopicOverview.this,Question.class);
                questionPage.putExtra("choice", myValue);
                startActivity(questionPage);
            }
        });

    }
}
