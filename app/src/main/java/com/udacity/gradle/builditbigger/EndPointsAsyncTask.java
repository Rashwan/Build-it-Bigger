package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.rashwan.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rashwan.jokedisplay.JokeDisplayActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by rashwan on 8/15/16.
 */

public abstract class  EndPointsAsyncTask extends AsyncTask<Context,Void,String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... contexts) {
        if (myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null)
                    .setRootUrl("http://192.168.1.77:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        context = contexts[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String joke = result;
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("joke")){
                joke = jsonObject.getString("joke");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = JokeDisplayActivity.getDisplayIntent(context,joke);

        prepareJokeDisplay(intent);
    }
    public abstract void prepareJokeDisplay(Intent jokeDisplayIntent);
}
