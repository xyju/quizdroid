package edu.washington.xyju.quizdroidpart3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    String[] topics = new String[3];
    Topic[] topicName = {new Topic(),new Topic(),new Topic()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizApp quizApp = (QuizApp)getApplication();
        quizApp.readJson("questions.json",topicName);

        for(int i = 0; i<topics.length;i++){
            topics[i] = topicName[i].getTitle();
        }

        ListView listView = (ListView) findViewById(R.id.list_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,topics);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switchToMuti(position);

            }

        });

    }

    public void switchToMuti(int position){
        Intent overviewPage = new Intent(MainActivity.this,TopicOverview.class);
        overviewPage.putExtra("choice",position );
        startActivity(overviewPage);
    }
}
