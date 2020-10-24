package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        //use the keys necessary for my particular backend
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Y4wTr4vo4nYfyuDxZtsPmMjQpMUJOasPUO4hgXIx")
                .clientKey("PdgKPFtdiVtmOunt6170v3AcPKssSmvlzuF9Yktk")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
