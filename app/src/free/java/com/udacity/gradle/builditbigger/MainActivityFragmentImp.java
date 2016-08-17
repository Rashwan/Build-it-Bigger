package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by rashwan on 8/16/16.
 */

public class MainActivityFragmentImp extends MainActivityFragment {
    private InterstitialAd interstitialAd;
    private Intent intent;
    private ProgressBar pbTellJoke;

    public MainActivityFragmentImp() {
    }


    @Override
    public void populateScreen(View root) {

        pbTellJoke = (ProgressBar) root.findViewById(R.id.progress_bar_tell_joke);

        setEndPointsAsyncTask(buildGCETask());

        buildBannerAd(root);

        buildInterstitialAd();

        requestNewInterstitial();
    }

    @NonNull
    private EndPointsAsyncTask buildGCETask() {
        return new EndPointsAsyncTask(){
            @Override
            public void prepareJokeDisplay(Intent jokeDisplayIntent) {
                pbTellJoke.setVisibility(View.GONE);
                intent = jokeDisplayIntent;
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }else {
                    getActivity().startActivity(jokeDisplayIntent);
                }
            }
        };
    }

    private void buildBannerAd(View root) {
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void buildInterstitialAd() {
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                getActivity().startActivity(intent);
            }
        });
    }


    private void requestNewInterstitial(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

}
