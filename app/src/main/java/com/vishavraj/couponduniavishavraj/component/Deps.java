package com.vishavraj.couponduniavishavraj.component;


import com.vishavraj.couponduniavishavraj.home.HomeActivity;
import com.vishavraj.couponduniavishavraj.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
