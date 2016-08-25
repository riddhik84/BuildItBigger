package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rks.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.myapps.rk.jokeactivity.JokeActivity;

import java.io.IOException;

/**
 * Created by RKs on 8/21/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Integer, String> {
    final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    private MyApi myApiService = null;
    private Context context;

    ProgressBar mProgressBar;

    private EndpointsAsyncTaskListener mListener = null;
    private Exception mError = null;

    String test = "";
    MainActivityFragment fragment;

    public EndpointsAsyncTask(String t, Context c, MainActivityFragment f) {
        context = c;
        fragment = f;
        test = t;
    }

    public EndpointsAsyncTask(ProgressBar p) {
        mProgressBar = p;
    }

    @Override
    protected void onPreExecute() {
        Log.d(LOG_TAG, "Public AsyncClass");
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
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
        for (int i = 0; i < 1; i++) {
            sleep();
            publishProgress(i * 50);
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
        if (mProgressBar != null) {
            mProgressBar.setProgress(values[0]);
        }
    }

    public EndpointsAsyncTask setListener(EndpointsAsyncTaskListener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(LOG_TAG, "riddhik In onPostExecute() method");

        if (this.mListener != null) {
            //Open a new activity with the message
            this.mListener.onComplete(result, mError);
        }

//       Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        if (test.length() < 1) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, result); //result from doInBackground
            context.startActivity(intent);
        }
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static interface EndpointsAsyncTaskListener {
        public void onComplete(String result, Exception e);
    }
}
