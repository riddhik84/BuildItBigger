/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.RKs.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.rkapps.JokeTeller;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.RKs.example.com",
                ownerName = "backend.myapplication.RKs.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     * Default template method
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    /**
     * A method to get Joke from javajokes java library
     */
    @ApiMethod(name = "sayJoke")
    public MyBean sayJoke(@Named("joke") String joke) {
        //Calling javajokes library to get a joke
        JokeTeller jk = new JokeTeller();
        MyBean response = new MyBean();

//        response.setData("This is a , " + joke);
        response.setData(jk.getAJoke(joke));
        return response;
    }

    /**
     * A method to get Joke from javajokes java library
     */
    @ApiMethod(name = "sayARandomJoke")
    public MyBean sayARandomJoke() {
        //Calling javajokes library to get a random joke
        JokeTeller jk = new JokeTeller();
        MyBean response = new MyBean();

        response.setData(jk.getARandomJoke());
        return response;
    }
}
