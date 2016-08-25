package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rks.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.myapps.rk.jokeactivity.JokeActivity;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d(LOG_TAG, "Paid! This is Paid app");

        mProgressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);

        Button showJokeButton = (Button) root.findViewById(R.id.joke_button);
        showJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "riddhik Method tellJoke()");
                //Toast.makeText(getContext(), "This is Paid app toast", Toast.LENGTH_SHORT).show();

                //new EndpointsAsyncTask().execute(new Pair<Context, String>(getContext(), ""));
                new com.udacity.gradle.builditbigger.EndpointsAsyncTask(mProgressBar).execute(new Pair<Context, String>(getContext(), ""));
            }
        });

        return root;
    }

    private class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Integer, String> {
        final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

        private MyApi myApiService = null;
        private Context context;

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            Log.d(LOG_TAG, "riddhik In doInBackground() method");

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first; //application context
            String name = params[0].second; //input String value

            //To generate progress bar
            for (int i = 0; i < 10; i++) {
                sleep();
                publishProgress(i * 20);
            }

            try {
//            return myApiService.sayHi(name).execute().getData();
//            return myApiService.sayJoke(name).execute().getData();
                return myApiService.sayARandomJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(LOG_TAG, "riddhik In onPostExecute() method");

//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            mProgressBar.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(getContext(), JokeActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, result); //result from doInBackground
            startActivity(intent);
        }

        public void sleep() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
