package com.vishavraj.couponduniavishavraj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vishavraj.couponduniavishavraj.component.DaggerDeps;
import com.vishavraj.couponduniavishavraj.component.Deps;
import com.vishavraj.couponduniavishavraj.networking.NetworkModule;

import java.io.File;

//import com.plaps.CouponDuniaVishavraj.component.DaggerDeps;

public class BaseApp  extends AppCompatActivity {
    Deps deps;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
