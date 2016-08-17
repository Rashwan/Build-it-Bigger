package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by rashwan on 8/16/16.
 */

public class MainActivityFragmentImp extends MainActivityFragment{
    private ProgressBar pbTellJoke;
    public MainActivityFragmentImp() {
    }

    @Override
    public void populateScreen(View root) {
        pbTellJoke = (ProgressBar) root.findViewById(R.id.progress_bar_tell_joke);
        setEndPointsAsyncTask(buildGCETask());
    }
    @NonNull
    private EndPointsAsyncTask buildGCETask() {
        return new EndPointsAsyncTask(){
            @Override
            public void prepareJokeDisplay(Intent jokeDisplayIntent) {
                pbTellJoke.setVisibility(View.GONE);
                getActivity().startActivity(jokeDisplayIntent);
            }
        };
    }


}
