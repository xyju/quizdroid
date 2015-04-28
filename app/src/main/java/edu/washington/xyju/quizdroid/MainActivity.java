package edu.washington.xyju.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;



public class MainActivity extends ActionBarActivity {

    String[] topics = {"Math","Physics","Marvel Super Heroes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.list_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,topics);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent topicOverview1 = new Intent(MainActivity.this,TopicOverviewActivity.class);
                        topicOverview1.putExtra("choice",position);
                        startActivity(topicOverview1);
                        break;

                    case 1:
                        Intent topicOverview2 = new Intent(MainActivity.this,TopicOverviewActivity.class);
                        topicOverview2.putExtra("choice",position);
                        startActivity(topicOverview2);
                        break;

                    case 2:
                        Intent topicOverview3 = new Intent(MainActivity.this,TopicOverviewActivity.class);
                        topicOverview3.putExtra("choice",position);
                        startActivity(topicOverview3);
                        break;
                }
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
