package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public abstract class MainActivityFragment extends Fragment {
    private EndPointsAsyncTask endPointsAsyncTask;
    public MainActivityFragment() {}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button buttonTellJoke = (Button) root.findViewById(R.id.button_tell_joke);
        populateScreen(root);
        buttonTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (endPointsAsyncTask != null) {
                    endPointsAsyncTask.execute(getActivity());
                }else {
                    throw new NullPointerException("The fragmentImp should set the Async Task");
                }
            }
        });
        return root;
    }

    public void setEndPointsAsyncTask(EndPointsAsyncTask endPointsAsyncTask) {
        this.endPointsAsyncTask = endPointsAsyncTask;
    }

    public abstract void populateScreen(View root);


}
