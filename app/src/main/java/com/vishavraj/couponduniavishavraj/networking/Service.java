package com.vishavraj.couponduniavishavraj.networking;


import com.vishavraj.couponduniavishavraj.models.CouponDuniaData;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback callback,int pageNo) {

        return networkService.getCityList( pageNo+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CouponDuniaData>>() {
                    @Override
                    public Observable<? extends CouponDuniaData> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CouponDuniaData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CouponDuniaData styfiListResponse) {
                        callback.onSuccess(styfiListResponse);

                    }
                });
    }

    public interface GetCityListCallback{
        void onSuccess(CouponDuniaData styfiListResponse);

        void onError(NetworkError networkError);
    }
}
