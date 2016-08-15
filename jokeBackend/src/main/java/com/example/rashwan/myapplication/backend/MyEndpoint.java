/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.rashwan.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.rashwan.jokeGenerator.JokeGenerator;

import java.io.IOException;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.rashwan.example.com",
                ownerName = "backend.myapplication.rashwan.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public MyBean getJoke(){
        MyBean response = new MyBean();
        JokeGenerator jokeGenerator = new JokeGenerator();
        try {
            response.setData(jokeGenerator.getJoke());
        } catch (IOException e) {
            response.setData("Couldn't connect to Jokes server :(");
        }
        return response;
    }
}
