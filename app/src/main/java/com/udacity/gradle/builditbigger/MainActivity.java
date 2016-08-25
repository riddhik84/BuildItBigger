package com.udacity.gradle.builditbigger;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Main! This is Main app");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call GCE AsyncTask
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Android"));
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

//    public void tellJoke(View view) {
//        Log.d(LOG_TAG, "riddhik Method tellJoke()");

//        JokeTeller jt = new JokeTeller();
//        Toast.makeText(this, jt.getARandomJoke(), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, JokeActivity.class);
//        intent.putExtra(Intent.EXTRA_TEXT, jt.getARandomJoke());
//        startActivity(intent);

//    }

}
