/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.sheraz.myapplication.backend;

import com.example.sheraz.lib.JokeGenerator;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Sheraz.example.com",
                ownerName = "backend.myapplication.Sheraz.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        Joke response = new Joke();
        JokeGenerator generator = JokeGenerator.getInstance();
        response.setData(generator.getJoke());

        return response;
    }

}
