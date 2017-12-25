package com.vishavraj.couponduniavishavraj.home;

import android.util.Log;

import com.vishavraj.couponduniavishavraj.models.CouponDuniaData;
import com.vishavraj.couponduniavishavraj.networking.NetworkError;
import com.vishavraj.couponduniavishavraj.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList(int pageNo) {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CouponDuniaData cityListResponse) {
                Log.e("cityListResponse",cityListResponse.getResponse()+"");

                view.removeWait();
                view.getityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                Log.e("networkError",
                        networkError.getAppErrorMessage());

                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        },pageNo);

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
