package com.rashwan.jokeGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JokeGenerator {
    public String getJoke() throws IOException {
        URL url = new URL("http://tambal.azurewebsites.net/joke/random");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();
        return json.toString();
    }
}
