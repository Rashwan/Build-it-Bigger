package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public abstract class MainActivityFragment extends Fragment {
    private EndPointsAsyncTask endPointsAsyncTask;
    private Button buttonTellJoke;
    private ProgressBar pbTellJoke;
    private View root;


    public MainActivityFragment() {}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        buttonTellJoke = (Button) root.findViewById(R.id.button_tell_joke);
        pbTellJoke = (ProgressBar) root.findViewById(R.id.progress_bar_tell_joke);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateScreen(root);
        buttonTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbTellJoke.setVisibility(View.VISIBLE);
                if (endPointsAsyncTask != null) {
                    endPointsAsyncTask.execute(getActivity());
                }else {
                    throw new NullPointerException("The FragmentImp should set the Async Task");
                }
            }
        });
    }

    public void setEndPointsAsyncTask(EndPointsAsyncTask endPointsAsyncTask) {
        this.endPointsAsyncTask = endPointsAsyncTask;
    }

    public abstract void populateScreen(View root);


}
