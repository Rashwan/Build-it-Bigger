package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;

/**
 * Created by rashwan on 8/16/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class EndPointsAsyncTaskInstTest {

    @Test
    public void ShouldReturnJokeOnGCEAsyncTask() throws Exception {
        EndPointsAsyncTask task = new EndPointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                //Just to override the the original implementation
            }

            @Override
            public void prepareJokeDisplay(Intent jokeDisplayIntent) {
                //Just to override the the original implementation
            }
        };
        String response = task.execute(InstrumentationRegistry.getTargetContext())
                .get(10, TimeUnit.SECONDS);
        String joke = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("joke")){
                joke = jsonObject.getString("joke");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertFalse(joke.isEmpty());
    }
}
