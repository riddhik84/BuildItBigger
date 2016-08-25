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
        //Log.d(LOG_TAG, "Paid! This is Paid app");

        mProgressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);

        Button showJokeButton = (Button) root.findViewById(R.id.joke_button);
        showJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(LOG_TAG, "riddhik Method tellJoke()");
                //Toast.makeText(getContext(), "This is Paid app toast", Toast.LENGTH_SHORT).show();

                //new EndpointsAsyncTask().execute(new Pair<Context, String>(getContext(), ""));
                new com.udacity.gradle.builditbigger.EndpointsAsyncTask(mProgressBar).execute(new Pair<Context, String>(getContext(), ""));
            }
        });

        return root;
    }
}
