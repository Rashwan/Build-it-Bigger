package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by rashwan on 8/16/16.
 */

public class MainActivityFragmentImp extends MainActivityFragment{
    public MainActivityFragmentImp() {
    }

    @Override
    public void populateScreen(View root) {
        setEndPointsAsyncTask(buildGCETask());
    }
    @NonNull
    private EndPointsAsyncTask buildGCETask() {
        return new EndPointsAsyncTask(){
            @Override
            public void prepareJokeDisplay(Intent jokeDisplayIntent) {
                getActivity().startActivity(jokeDisplayIntent);
            }
        };
    }


}
